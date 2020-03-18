package traverse.rockcandy.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import traverse.rockcandy.registry.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCandyDispenser extends BaseUsableGem {
    public ItemCandyDispenser() {
        super("candy_dispenser",50);


    }

  @Override
  public UseAction getUseAction(ItemStack p_77661_1_) {
    return UseAction.EAT;
  }

  @Override
  public int getUseDuration(ItemStack p_77626_1_) {
    return 8;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack stack = playerIn.getHeldItem(handIn);
    if(stack.getDamage() != stack.getMaxDamage()-1 && playerIn.getFoodStats().needFood()){
      playerIn.setActiveHand(handIn);
    }
    return super.onItemRightClick(worldIn, playerIn, handIn);
  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
    if(entityLiving instanceof PlayerEntity){
      PlayerEntity player = (PlayerEntity) entityLiving;
      player.getFoodStats().addStats(3, 0.3F);
      worldIn.playSound(null,player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS,0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
      stack.damageItem(1, player , (entity) -> {});
    }
    return stack;
  }


  @Override
  public void inventoryTick(ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    if (worldIn.isRemote()) {
      return;
    }
    PlayerEntity player = (PlayerEntity) entityIn;
    for (int i = 0; player.inventory.getSizeInventory() > i; ++i) {
      ItemStack stack = player.inventory.getStackInSlot(i);
      if (stack.getItem() != this) continue;
      if (stack.getItem() instanceof BaseUsableGem && isActive(stack)) {
        absorbCandy(stack, player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null));
      }
    }
   /* if (EnchantmentHelper.getEnchantmentLevel(EnchantmentTypes.enchanmentAutoFeed, itemStack) > 0) {
      if (player.canEat(false)) {
        if (itemStack.isDamageable() && itemStack.getMaxDamage() - itemStack.getDamage() > 0) {
          player.getFoodStats().addStats(5,0.6F);
          itemStack.damageItem(1, player, playerEntity -> {
          });
        }*/
      }

    public void absorbCandy(ItemStack rockStack, IItemHandler inventory) {
        int damage = rockStack.getDamage();
        if (damage != 0) {
            for (int i = 0; inventory.getSlots() > i; ++i) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (stack.getItem() == ModItems.RAW_CANDY) {
                    ItemStack candyStack = inventory.extractItem(i, 1, false);
                    this.setDamage(rockStack, damage - candyStack.getCount());
                    return;

                }
                else if(stack.getItem() == ModItems.HARDEN_CANDY){
                    ItemStack candyStack = inventory.extractItem(i,1,false);
                    this.setDamage(rockStack,damage - (candyStack.getCount())-3);
                }
            }
        }
    }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    tooltip.add(new StringTextComponent( stack.getMaxDamage() - stack.getItem().getDamage(stack) + "/"  + stack.getMaxDamage() + " Charges"));
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
