package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;
import traverse.rockcandy.registry.ModDataComponents;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class CandyGemItem extends BaseUsableGem {
	public CandyGemItem(Properties properties) {
		super(properties, 1000);
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
		return 4;
	}

	@Override
	public InteractionResult use(Level level, Player playerIn, InteractionHand hand) {
		ItemStack stack = playerIn.getItemInHand(hand);
		if (stack.getDamageValue() != stack.getMaxDamage() - 1 && playerIn.getFoodData().needsFood()) {
			playerIn.startUsingItem(hand);
		}
		return super.use(level, playerIn, hand);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
		if (entityLiving instanceof Player player) {
			player.getFoodData().eat(5, 0.6F);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
			stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
		}
		return stack;
	}

	@Override
	public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
		if (level.isClientSide()) {
			return;
		}
		if (entity instanceof Player player) {
			for (int i = 0; player.getInventory().getContainerSize() > i; ++i) {
				ItemStack stack = player.getInventory().getItem(i);
				if (stack.getItem() != this) continue;
				if (isActive(stack)) {
					absorbSugar(stack, player.getCapability(Capabilities.Item.ENTITY));
				}
			}
			if (isAutoFeeding(itemStack)) {
				if (player.canEat(false)) {
					if (itemStack.isDamageableItem() && itemStack.getMaxDamage() - itemStack.getDamageValue() > 1) {
						player.getFoodData().eat(5, 0.6F);
						itemStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(itemStack));
					}
				}
			}
		}
	}


	private void absorbSugar(ItemStack rockGem, ResourceHandler<ItemResource> inventory) {
		int damage = rockGem.getDamageValue();
		if (damage != 0) {
			for (int i = 0; inventory.size() > i; ++i) {
				ItemResource resource = inventory.getResource(i);

				if (resource.is(Items.SUGAR)) {
					try (Transaction tx = Transaction.openRoot()) {
						if (inventory.extract(i, resource, 1, tx) != 1) {
							continue;
						}
						tx.commit();
						this.setDamage(rockGem, damage - 1);
					}
					return;
				}
			}
		}
	}

	public static boolean isAutoFeeding(@Nonnull ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.AUTO_FEED, false);
	}

 /* @Override
  public boolean isDamageable() {
    if (getDamage(new ItemStack(ModItems.CANDY_GEM)) > 1) {
      return true;
    }
    return false;
  } */

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
		tooltipAdder.accept(Component.literal(stack.getMaxDamage() - stack.getItem().getDamage(stack) + "/" + stack.getMaxDamage() + " Charges"));
		if (isAutoFeeding(stack)) {
			tooltipAdder.accept(Component.literal(ChatFormatting.YELLOW + "Auto Feed: " + ChatFormatting.GREEN + "Enabled"));
		} else {
			tooltipAdder.accept(Component.literal(ChatFormatting.YELLOW + "Auto Feed: " + ChatFormatting.RED + "Disabled"));
		}
		super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
	}
}
