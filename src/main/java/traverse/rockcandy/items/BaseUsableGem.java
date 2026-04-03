package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import traverse.rockcandy.registry.ModDataComponents;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BaseUsableGem extends Item {

	public BaseUsableGem(Properties properties, int maxDamage) {
		super(properties.durability(maxDamage));
	}


	@Override
	public InteractionResult use(Level level, Player playerIn, InteractionHand hand) {
		ItemStack stack = playerIn.getItemInHand(hand);
		if (!level.isClientSide() && playerIn.isCrouching()) {
			playerIn.level().playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F,
					((playerIn.level().getRandom().nextFloat() - playerIn.level().getRandom().nextFloat()) * 0.7F + 1.2F));
			this.toggleActive(stack);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean isFoil(@Nonnull ItemStack stack) {
		return isActive(stack);
	}

	public boolean isActive(@Nonnull ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.ACTIVE, false);
	}

	private void toggleActive(@Nonnull ItemStack stack) {
		setActive(stack, !isActive(stack));

	}

	private void setActive(@Nonnull ItemStack stack, boolean bool) {
		stack.set(ModDataComponents.ACTIVE, bool);
	}
/*
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
            ItemStack emptystack = new ItemStack(this);
            emptystack.damageItem();
            items.add(emptystack);
        }
    }*/

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return stack.getDamageValue() > 0;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
		if (isActive(stack)) {
			tooltipAdder.accept(Component.literal(ChatFormatting.BLUE + "Is Active: " + ChatFormatting.GREEN + "True"));
		} else {
			tooltipAdder.accept(Component.literal(ChatFormatting.BLUE + "Is Active: " + ChatFormatting.RED + "False"));
		}
		super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
	}
}

