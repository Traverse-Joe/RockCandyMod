package sora.rockcandy.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import sora.rockcandy.RockCandyCreativeTab;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


public class BaseUsableGem extends BaseItem {

    public BaseUsableGem(String name, int maxDamage) {
    super(name, new Properties().group(RockCandyCreativeTab.getInstance()).maxStackSize(1).maxDamage(maxDamage));
  }



  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack stack = playerIn.getHeldItem(handIn);
    if(!worldIn.isRemote && playerIn.isSneaking()){
      playerIn.world.playSound(null,playerIn.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0F, 1.0F * ((playerIn.world.rand.nextFloat() - playerIn.world.rand.nextFloat()) * 0.7F + 1.2F));
      this.toggleActive(stack);
    }
    return new ActionResult<>(ActionResultType.SUCCESS, stack);
  }

  @Override
    public boolean hasEffect(@Nonnull ItemStack stack) {
        return isActive(stack);
    }

    public boolean isActive(@Nonnull ItemStack stack) {
        CompoundNBT compound = stack.getTag();

       if(compound == null){
           return false;
        }
        return compound.getBoolean("isActive");
    }

    private void toggleActive(@Nonnull ItemStack stack){
        setActive(stack , !isActive(stack));

    }

    private void setActive (@Nonnull ItemStack stack , boolean bool){
        CompoundNBT compound = stack.getTag();


        if(compound == null){
            compound = new CompoundNBT();
            stack.setTag(compound);

        }
        compound.putBoolean("isActive", bool);
    }
/*
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
            ItemStack emptystack = new ItemStack(this);
            emptystack.damageItem();
            items.add(emptystack);
        }
    }*/

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.getDamage()>0;
    }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    if(isActive(stack)){
      tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Is Active: " + TextFormatting.GREEN + "True"));
    }
    else {
      tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Is Active: " + TextFormatting.RED + "False"));
    }
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}

