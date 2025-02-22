package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import traverse.rockcandy.registry.ModDataComponents;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BaseUsableGem extends Item {

	public BaseUsableGem(Properties properties, int maxDamage) {
		super(properties.durability(maxDamage));
	}


	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if (!level.isClientSide && playerIn.isCrouching()) {
			playerIn.level().playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, ((playerIn.level().random.nextFloat() - playerIn.level().random.nextFloat()) * 0.7F + 1.2F));
			this.toggleActive(stack);
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
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
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (isActive(stack)) {
			tooltip.add(Component.literal(ChatFormatting.BLUE + "Is Active: " + ChatFormatting.GREEN + "True"));
		} else {
			tooltip.add(Component.literal(ChatFormatting.BLUE + "Is Active: " + ChatFormatting.RED + "False"));
		}
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}

