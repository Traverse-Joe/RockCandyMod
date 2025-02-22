package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CandyGemItem extends BaseUsableGem {
	public CandyGemItem(Properties properties) {
		super(properties, 1000);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 4;
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
			player.getFoodData().eat(5, 0.6F);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
			stack.hurtAndBreak(1, player, (entity) -> {
				entity.broadcastBreakEvent(entity.getUsedItemHand());
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
					absorbSugar(stack, player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null));
				}
			}
			if (isAutoFeeding(itemStack)) {
				if (player.canEat(false)) {
					if (itemStack.isDamageableItem() && itemStack.getMaxDamage() - itemStack.getDamageValue() > 1) {
						player.getFoodData().eat(5, 0.6F);
						itemStack.hurtAndBreak(1, player, playerEntity -> {
							playerEntity.broadcastBreakEvent(playerEntity.getUsedItemHand());
						});
					}
				}
			}
		}
	}


	private void absorbSugar(ItemStack rockGem, IItemHandler inventory) {
		int damage = rockGem.getDamageValue();
		if (damage != 0) {
			for (int i = 0; inventory.getSlots() > i; ++i) {
				ItemStack stack = inventory.getStackInSlot(i);

				if (stack.getItem() == Items.SUGAR) {
					ItemStack sugarStack = inventory.extractItem(i, 1, false);
					this.setDamage(rockGem, damage - sugarStack.getCount());

					return;

				}
			}
		}
	}

	public static boolean isAutoFeeding(@Nonnull ItemStack stack) {
		CompoundTag compound = stack.getTag();

		if (compound == null) {
			return false;
		}
		return compound.getBoolean("autoFeed");
	}

 /* @Override
  public boolean isDamageable() {
    if (getDamage(new ItemStack(ModItems.CANDY_GEM)) > 1) {
      return true;
    }
    return false;
  } */

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.literal(stack.getMaxDamage() - stack.getItem().getDamage(stack) + "/" + stack.getMaxDamage() + " Charges"));
		if (isAutoFeeding(stack)) {
			tooltip.add(Component.literal(ChatFormatting.YELLOW + "Auto Feed: " + ChatFormatting.GREEN + "Enabled"));
		} else {
			tooltip.add(Component.literal(ChatFormatting.YELLOW + "Auto Feed: " + ChatFormatting.RED + "Disabled"));
		}
		super.appendHoverText(stack, level, tooltip, flagIn);
	}
}
