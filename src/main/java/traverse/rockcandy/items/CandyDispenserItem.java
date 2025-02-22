package traverse.rockcandy.items;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import traverse.rockcandy.registry.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public class CandyDispenserItem extends BaseUsableGem {
	public CandyDispenserItem(Properties properties) {
		super(properties, 50);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_77661_1_) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 8;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if (stack.getDamageValue() != stack.getMaxDamage() - 1 && playerIn.getFoodData().needsFood()) {
			playerIn.startUsingItem(handIn);
		}
		return super.use(level, playerIn, handIn);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
		if (entityLiving instanceof Player player) {
			player.getFoodData().eat(3, 0.3F);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
			stack.hurtAndBreak(1, player, (entity) -> {
				player.broadcastBreakEvent(entity.getUsedItemHand());
			});
		}
		return stack;
	}


	@Override
	public void inventoryTick(ItemStack itemStack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
		if (level.isClientSide()) {
			return;
		}
		if (entityIn instanceof Player player) {
			for (int i = 0; player.getInventory().getContainerSize() > i; ++i) {
				ItemStack stack = player.getInventory().getItem(i);
				if (stack.getItem() != this) continue;
				if (stack.getItem() instanceof BaseUsableGem && isActive(stack)) {
					absorbCandy(stack, player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null));
				}
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
		int damage = rockStack.getDamageValue();
		if (damage != 0) {
			for (int i = 0; inventory.getSlots() > i; ++i) {
				ItemStack stack = inventory.getStackInSlot(i);
				if (stack.is(ModItems.RAW_CANDY.get())) {
					ItemStack candyStack = inventory.extractItem(i, 1, false);
					this.setDamage(rockStack, damage - candyStack.getCount());
					return;

				} else if (stack.is(ModItems.HARDEN_CANDY.get())) {
					ItemStack candyStack = inventory.extractItem(i, 1, false);
					this.setDamage(rockStack, damage - (candyStack.getCount()) - 3);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.literal(stack.getMaxDamage() - stack.getItem().getDamage(stack) + "/" + stack.getMaxDamage() + " Charges"));
		super.appendHoverText(stack, level, tooltip, flagIn);
	}
}
