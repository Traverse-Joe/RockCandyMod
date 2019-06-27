//package sora.rockcandy.gui.container;
//
//import sora.rockcandy.registry.InfuserRecipeHandler;
//import sora.rockcandy.registry.ModItems;
//import sora.rockcandy.tileentity.TileEntityCandyInfuser;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.inventory.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.items.SlotItemHandler;
//
//import javax.annotation.Nonnull;
//
//public class ContainerCandyInfuser extends Container {
//
//    public TileEntityCandyInfuser infuser;
//    public ContainerCandyInfuser(IInventory playerInv , TileEntityCandyInfuser te){
//        super();
//        infuser = te;
//
//        addSlotToContainer(new SlotItemHandler(this.infuser.inventory, 0, 56, 17) {
//            @Override
//            public boolean isItemValid(@Nonnull ItemStack stack) {
//                return stack.getItem() != ModItems.itemRawRockCandy;
//            }
//        });
//        addSlotToContainer(new SlotItemHandler(this.infuser.inventory, 1, 56, 53));
//        addSlotToContainer(new SlotOutput(this.infuser.inventory, 2, 115, 34));
//
//        int i;
//        for (i = 0; i < 3; ++i) {
//            for (int j = 0; j < 9; ++j) {
//                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
//            }
//        }
//
//        for (i = 0; i < 9; ++i) {
//            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
//        }
//    }
//
//    @Override
//    public boolean canInteractWith(EntityPlayer playerIn) {
//        return true;
//    }
//
//    @Override
//    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
//    {
//        ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = this.inventorySlots.get(index);
//
//        if (slot != null && slot.getHasStack())
//        {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//
//            if (index == 2)
//            {
//                if (!this.mergeItemStack(itemstack1, 3, 39, true))
//                {
//                    return ItemStack.EMPTY;
//                }
//
//                slot.onSlotChange(itemstack1, itemstack);
//            }
//            else if (index != 1 && index != 0)
//            {
//                if (!InfuserRecipeHandler.instance().getInfuserResult(itemstack1).isEmpty())
//                {
//                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (itemstack1.getItem() == ModItems.itemRawRockCandy)
//                {
//                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (index >= 3 && index < 30)
//                {
//                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
//                {
//                    return ItemStack.EMPTY;
//                }
//            }
//            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
//            {
//                return ItemStack.EMPTY;
//            }
//
//            if (itemstack1.isEmpty())
//            {
//                slot.putStack(ItemStack.EMPTY);
//            }
//            else
//            {
//                slot.onSlotChanged();
//            }
//
//            if (itemstack1.getCount() == itemstack.getCount())
//            {
//                return ItemStack.EMPTY;
//            }
//
//            slot.onTake(playerIn, itemstack1);
//        }
//
//        return itemstack;
//    }
//}
