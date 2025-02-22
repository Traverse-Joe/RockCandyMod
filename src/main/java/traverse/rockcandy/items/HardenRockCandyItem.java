package traverse.rockcandy.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import traverse.rockcandy.registry.ConfigHandler;
import traverse.rockcandy.registry.ModItems;

import java.util.List;

public class HardenRockCandyItem extends Item {

	public static final int CHANGE_TIME = ConfigHandler.general.changeTime.get() * 20;

	public HardenRockCandyItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if (playerIn.canEat(false)) {
			playerIn.startUsingItem(handIn);
			playerIn.hurt(DamageSource.GENERIC, 2);
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
		} else {
			return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
		}
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		Level level = entity.getLevel();
		if (!level.isClientSide() && entity.isInWater()) {
			List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().expandTowards(1, 1, 1));

			for (ItemEntity item : items) {
				if (item.getItem().is(ModItems.HARDEN_CANDY.get())) {
					int count = entity.getItem().getCount();
					if (entity.getAge() >= CHANGE_TIME) {
						entity.discard();
						item.discard();
						level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F, false);
						level.addFreshEntity(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.BLANK_CANDY.get(), count)));
					}
				}
			}
		}
		return false;
	}
}

