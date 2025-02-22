package traverse.rockcandy.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RawRockCandyItem extends Item {

	public RawRockCandyItem(Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 8;
	}
}
