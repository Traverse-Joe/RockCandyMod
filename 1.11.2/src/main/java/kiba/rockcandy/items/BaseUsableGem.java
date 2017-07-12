package kiba.rockcandy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;


public class BaseUsableGem extends BaseItem {
    public BaseUsableGem(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote && player.isSneaking()) {
            player.worldObj.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, 0.5F * ((player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.7F + 1.2F));
            this.toggleActive(stack);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @Override
    public boolean hasEffect(@Nonnull ItemStack stack) {
        return isActive(stack);
    }

    private boolean isActive(@Nonnull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();

       if(compound == null){
           return false;
        }
        return compound.getBoolean("isActive");
    }
    private void toggleActive(@Nonnull ItemStack stack){
        setActive(stack , !isActive(stack));

    }
    private void setActive (@Nonnull ItemStack stack , boolean bool){
        NBTTagCompound compound = stack.getTagCompound();


        if(compound == null){
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);

        }
        compound.setBoolean("isActive", bool);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (isActive(stack)) {
            tooltip.add(TextFormatting.BLUE + "Is Active: " + TextFormatting.GREEN + "True");
        } else {
            tooltip.add(TextFormatting.BLUE + "Is Active: " + TextFormatting.RED + "False");
            super.addInformation(stack, playerIn, tooltip, advanced);
        }
    }
}

