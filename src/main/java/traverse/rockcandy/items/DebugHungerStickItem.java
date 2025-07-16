package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class DebugHungerStickItem extends Item {
	public DebugHungerStickItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player playerIn, InteractionHand hand) {
		if (!level.isClientSide) {
			playerIn.getFoodData().setFoodLevel(0);
		}
		return InteractionResult.FAIL;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
		tooltipAdder.accept(Component.literal(ChatFormatting.DARK_RED + "DEBUG ITEM -- GIVES PLAYER ZERO HUNGER"));
		super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
	}
}
