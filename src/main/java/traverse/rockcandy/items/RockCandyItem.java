package traverse.rockcandy.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class RockCandyItem extends Item {

	public RockCandyItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		if (livingEntity instanceof Player player) {
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.STICK));
		}
		return super.finishUsingItem(stack, level, livingEntity);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
		return 10;
	}
}
