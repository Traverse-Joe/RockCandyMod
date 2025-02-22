package traverse.rockcandy;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import traverse.rockcandy.registry.ModItems;

public class RockCandyCreativeTab extends CreativeModeTab {

	private static final RockCandyCreativeTab INSTANCE = new RockCandyCreativeTab();

	public RockCandyCreativeTab() {
		super(RockCandy.MODID);
	}

	public static RockCandyCreativeTab getInstance() {
		return INSTANCE;
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ModItems.ROCK_CANDY.get());
	}
}
