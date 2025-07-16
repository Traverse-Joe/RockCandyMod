package traverse.rockcandy.items.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import traverse.rockcandy.registry.ModTiers;

import java.util.List;

public class CandyPickaxeItem extends PickaxeItem {
	public CandyPickaxeItem(Properties properties) {
		super(ModTiers.CANDY, 1, -2.6F, properties);
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 16;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide && player.isCrouching()) {
			if (stack.getDamageValue() != stack.getMaxDamage()) {
				player.startUsingItem(hand);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		if (livingEntity instanceof Player player) {
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2 * 30 * 20, 1));
			level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
			stack.hurtAndBreak(25, player, player.getEquipmentSlotForItem(stack));
		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (!Screen.hasShiftDown()) {
			tooltip.add(Component.literal(ChatFormatting.YELLOW + "Press Shift for more info"));
		} else {
			tooltip.add(Component.literal(ChatFormatting.BLUE + "Shift-Right Click: " + ChatFormatting.RED + "Haste Buff"));
		}
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
