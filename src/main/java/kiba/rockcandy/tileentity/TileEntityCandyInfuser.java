package kiba.rockcandy.tileentity;

import kiba.rockcandy.registry.InfuserRecipeHandler;
import kiba.rockcandy.registry.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityCandyInfuser extends TileEntity implements ITickable {
    public int cookTime;
    public int totalCookTime;
    public int burnTime;
    public int itemBurnTime;

    public ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (!this.canInsert(stack, slot)) {
                return stack;
            }
            return super.insertItem(slot, stack, simulate);
        }

        public boolean canInsert(ItemStack stack, int slot) {
            return TileEntityCandyInfuser.this.isItemValidForSlot(slot, stack);
        }

        @Override
        protected void onContentsChanged(int slot) {
            TileEntityCandyInfuser.this.markDirty();
        }
    };

    private boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot == 0 && stack.getItem() != ModItems.itemRawRockCandy && !InfuserRecipeHandler.instance().infuserList.containsKey(stack)) {
            return true;
        } else if (slot == 0 && stack.getItem() == ModItems.itemRawRockCandy && InfuserRecipeHandler.instance().infuserList.containsKey(stack)) {
            return false;
        }//dad is back ill bbl, try now

        if (slot == 1 && stack.getItem() == ModItems.itemRawRockCandy) {
            return true;
        } else if (slot == 1 && stack.getItem() != ModItems.itemRawRockCandy) {
            return false;
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            inventory.deserializeNBT((NBTTagCompound) compound.getTag("items"));

        }
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("TotalCookTime");
        this.burnTime = compound.getInteger("BurnTime");
        this.itemBurnTime = getItemBurnTime();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", inventory.serializeNBT());
        compound.setInteger("CookTime", (short) cookTime);
        compound.setInteger("TotalCookTime", (short) totalCookTime);
        compound.setInteger("BurnTime", (short) burnTime);

        return compound;

    }

    public int getItemBurnTime() {
        if (!inventory.getStackInSlot(1).isEmpty() && inventory.getStackInSlot(1).getItem() == ModItems.itemRawRockCandy) {
            return 164;
        }
        return 0;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
        }
        this.totalCookTime = getCookTime();

        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.inventory.getStackInSlot(1);

            if (this.isBurning() || !itemstack.isEmpty() && !this.inventory.getStackInSlot(0).isEmpty()) {
                if (!this.isBurning() && this.canInfuse() && this.inventory.getStackInSlot(1).getItem() == ModItems.itemRawRockCandy) {
                    this.burnTime = 164;
                    this.itemBurnTime = this.burnTime;
                    inventory.getStackInSlot(1).shrink(1);
                }


                if (this.isBurning() && this.canInfuse()) {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.infuseItem();
                    }
                } else {
                    this.cookTime = 0;
                }
            }
        }
    }

    public int getCookTime() {
        return 150;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public boolean canInfuse() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            ItemStack itemstack = InfuserRecipeHandler.instance().getInfuserResult(inventory.getStackInSlot(0));
            if (!itemstack.isEmpty()) {
                ItemStack itemstack1 = inventory.getStackInSlot(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= 64 && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        }
        return false;
    }

    public void infuseItem() {
        if (this.canInfuse()) {

            ItemStack itemstack = inventory.getStackInSlot(0);
            ItemStack itemstack1 = InfuserRecipeHandler.instance().getInfuserResult(itemstack);
            ItemStack itemstack2 = inventory.getStackInSlot(2);

            if (itemstack2.isEmpty()) {
                this.inventory.setStackInSlot(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }
            itemstack.shrink(1);
        }
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getCapability(capability, facing) != null;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) inventory;
        }
        return super.getCapability(capability, facing);
    }
}
