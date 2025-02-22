package traverse.rockcandy.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.items.CandyDispenserItem;
import traverse.rockcandy.items.CandyGemItem;
import traverse.rockcandy.items.DebugHungerStickItem;
import traverse.rockcandy.items.HardenRockCandyItem;
import traverse.rockcandy.items.RawRockCandyItem;
import traverse.rockcandy.items.RockCandyItem;
import traverse.rockcandy.items.tools.CandyClubItem;
import traverse.rockcandy.items.tools.CandyPickaxeItem;

public class ModItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RockCandy.MODID);

	public static final DeferredItem<RockCandyItem> ROCK_CANDY = ITEMS.register("rock_candy", () -> new RockCandyItem(itemBuilder().food(ModFoods.ROCK_CANDY)));
	public static final DeferredItem<RawRockCandyItem> RAW_CANDY = ITEMS.register("raw_rock_candy", () -> new RawRockCandyItem(itemBuilder().food(ModFoods.RAW_ROCK_CANDY)));
	public static final DeferredItem<HardenRockCandyItem> HARDEN_CANDY = ITEMS.register("harden_rock_candy", () -> new HardenRockCandyItem(itemBuilder().food(ModFoods.HARDEN_ROCK_CANDY)));
	public static final DeferredItem<DebugHungerStickItem> HUNGER_STICK = ITEMS.register("hunger_stick", () -> new DebugHungerStickItem(itemBuilder()));
	public static final DeferredItem<Item> CANDY_CORE = ITEMS.registerSimpleItem("candy_core");
	public static final DeferredItem<Item> CANDY_ROD = ITEMS.registerSimpleItem("candy_rod");
	public static final DeferredItem<BlockItem> CANDY_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.CANDY_ORE);
	public static final DeferredItem<BlockItem> CANDY_BLOCK = ITEMS.registerSimpleBlockItem(ModBlocks.CANDY_BLOCK);

	public static final DeferredItem<Item> CANDY_GEM = ITEMS.register("candy_gem", () -> new CandyGemItem(itemBuilder()));
	public static final DeferredItem<Item> CANDY_DISPENSER = ITEMS.register("candy_dispenser", () -> new CandyDispenserItem(itemBuilder()));

	//Special Candy
	public static final DeferredItem<Item> RED_HOT_CANDY = ITEMS.register("red_hot_candy", () -> new Item(itemBuilder().food(ModFoods.RED_HOT_CANDY)));
	public static final DeferredItem<Item> DEEP_BLUE_CANDY = ITEMS.register("deep_blue_candy", () -> new Item(itemBuilder().food(ModFoods.DEEP_BLUE_CANDY)));
	public static final DeferredItem<Item> GLOW_CANDY = ITEMS.register("glow_candy", () -> new Item(itemBuilder().food(ModFoods.GLOW_CANDY)));
	public static final DeferredItem<Item> CLEAR_CANDY = ITEMS.register("clear_candy", () -> new Item(itemBuilder().food(ModFoods.CLEAR_CANDY)));
	public static final DeferredItem<Item> FLOAT_CANDY = ITEMS.register("float_candy", () -> new Item(itemBuilder().food(ModFoods.FLOAT_CANDY)));
	public static final DeferredItem<Item> HEALTHY_CANDY = ITEMS.register("healthy_candy", () -> new Item(itemBuilder().food(ModFoods.HEALTHY_CANDY)));
	public static final DeferredItem<Item> BLANK_CANDY = ITEMS.register("blank_candy", () -> new Item(itemBuilder().food(ModFoods.BLANK_CANDY)));

	//Tools

	public static final DeferredItem<CandyClubItem> CANDY_CLUB = ITEMS.register("candy_club", () -> new CandyClubItem(itemBuilder()));
	public static final DeferredItem<CandyPickaxeItem> CANDY_CANE_PICKAXE = ITEMS.register("candy_cane_pickaxe", () -> new CandyPickaxeItem(itemBuilder()));

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
