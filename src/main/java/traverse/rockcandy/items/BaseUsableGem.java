package traverse.rockcandy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
			playerIn.level.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, ((playerIn.level.random.nextFloat() - playerIn.level.random.nextFloat()) * 0.7F + 1.2F));
			this.toggleActive(stack);
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}

	@Override
	public boolean isFoil(@Nonnull ItemStack stack) {
		return isActive(stack);
	}

	public boolean isActive(@Nonnull ItemStack stack) {
		CompoundTag compound = stack.getTag();

		if (compound == null) {
			return false;
		}
		return compound.getBoolean("isActive");
	}

	private void toggleActive(@Nonnull ItemStack stack) {
		setActive(stack, !isActive(stack));

	}

	private void setActive(@Nonnull ItemStack stack, boolean bool) {
		CompoundTag compound = stack.getTag();


		if (compound == null) {
			compound = new CompoundTag();
			stack.setTag(compound);

		}
		compound.putBoolean("isActive", bool);
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
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		if (isActive(stack)) {
			tooltip.add(new TextComponent(ChatFormatting.BLUE + "Is Active: " + ChatFormatting.GREEN + "True"));
		} else {
			tooltip.add(new TextComponent(ChatFormatting.BLUE + "Is Active: " + ChatFormatting.RED + "False"));
		}
		super.appendHoverText(stack, level, tooltip, flagIn);
	}
}

