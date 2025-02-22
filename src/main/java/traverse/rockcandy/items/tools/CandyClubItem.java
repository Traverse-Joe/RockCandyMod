package traverse.rockcandy.items.tools;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import traverse.rockcandy.registry.ModTiers;

import javax.annotation.Nullable;
import java.util.List;

public class CandyClubItem extends SwordItem {
	public CandyClubItem(Properties properties) {
		super(ModTiers.CANDY, 3, 3.0F, properties);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 16;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide && player.isCrouching()) {
			if (stack.getDamageValue() != stack.getMaxDamage() && player.getFoodData().needsFood()) {
				player.startUsingItem(hand);
			}
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
		}
		return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		if (livingEntity instanceof Player player) {
			if (!level.isClientSide) {
				player.getFoodData().eat(4, 1.0F);
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
				stack.hurtAndBreak(10, player, player1 -> {
					player1.broadcastBreakEvent(player1.getUsedItemHand());
				});
			}
		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		if (!Screen.hasShiftDown()) {
			tooltip.add(Component.literal(ChatFormatting.YELLOW + "Press Shift for more info"));
		} else {
			tooltip.add(Component.literal(ChatFormatting.BLUE + "Shift-Right Click: " + ChatFormatting.RED + "Feed's Player"));
		}
		super.appendHoverText(stack, level, tooltip, flagIn);
	}
}
