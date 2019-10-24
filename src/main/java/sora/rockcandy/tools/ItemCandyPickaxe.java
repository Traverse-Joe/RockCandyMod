package sora.rockcandy.tools;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sora.rockcandy.registry.Materials;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCandyPickaxe extends BaseItemPickaxe {
    public ItemCandyPickaxe() {
        super("candy_cane_pickaxe",Materials.CANDY, -2.6F);

    }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.EAT;
  }

  @Override
  public int getUseDuration(ItemStack stack) {
    return 16;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if(!world.isRemote && player.isSneaking()){
      if(stack.getDamage() != stack.getMaxDamage()){
        player.setActiveHand(hand);
      }
      return new ActionResult<>(ActionResultType.SUCCESS,stack);
    }
    return new ActionResult<>(ActionResultType.FAIL,stack);
  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
    if(entityLiving instanceof PlayerEntity){
      PlayerEntity player = (PlayerEntity) entityLiving;
      player.addPotionEffect(new EffectInstance(Effects.HASTE, 2*30 * 20,1));
      worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F );
      stack.damageItem(25, player, playerEntity -> {});
    }
    return stack;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    if(!Screen.hasShiftDown()){
      tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Press Shift for more info"));
    }
    else{
      tooltip.add(new StringTextComponent(TextFormatting.BLUE  + "Shift-Right Click: " + TextFormatting.RED + "Haste Buff"));
    }
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
