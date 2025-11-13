package traverse.rockcandy.items;

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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;
import traverse.rockcandy.registry.ModItems;

import java.util.function.Consumer;

public class CandyDispenserItem extends BaseUsableGem {
	public CandyDispenserItem(Properties properties) {
		super(properties, 50);
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
		return 8;
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
			player.getFoodData().eat(3, 0.3F);
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
					absorbCandy(stack, player.getCapability(Capabilities.Item.ENTITY));
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

	public void absorbCandy(ItemStack rockStack, ResourceHandler<ItemResource> inventory) {
		int damage = rockStack.getDamageValue();
		if (damage != 0) {
			for (int i = 0; inventory.size() > i; ++i) {
				ItemResource resource = inventory.getResource(i);
				if (resource.is(ModItems.RAW_CANDY.get())) {
					try (Transaction tx = Transaction.openRoot()) {
						if (inventory.extract(i, resource, 1, tx) != 1) {
							continue;
						}
						tx.commit();
						this.setDamage(rockStack, damage - 1);
						return;
					}

				} else if (resource.is(ModItems.HARDEN_CANDY.get())) {
					try (Transaction tx = Transaction.openRoot()) {
						if (inventory.extract(i, resource, 1, tx) != 1) {
							continue;
						}
						tx.commit();
						this.setDamage(rockStack, damage - 4);
						return;
					}
				}
			}

		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
		tooltipAdder.accept(Component.literal(stack.getMaxDamage() - stack.getItem().getDamage(stack) + "/" + stack.getMaxDamage() + " Charges"));
		super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
	}
}
