package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DebugHungerStickItem extends Item {
	public DebugHungerStickItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		if (!level.isClientSide) {
			playerIn.getFoodData().setFoodLevel(0);
		}
		return new InteractionResultHolder<>(InteractionResult.FAIL, playerIn.getItemInHand(handIn));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(Component.literal(ChatFormatting.DARK_RED + "DEBUG ITEM -- GIVES PLAYER ZERO HUNGER"));
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
