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

	public static final DeferredItem<RockCandyItem> ROCK_CANDY = ITEMS.registerItem("rock_candy", RockCandyItem::new, () -> itemBuilder().food(ModFoods.ROCK_CANDY.properties(), ModFoods.ROCK_CANDY.consumable()));
	public static final DeferredItem<RawRockCandyItem> RAW_CANDY = ITEMS.registerItem("raw_rock_candy", RawRockCandyItem::new, () -> itemBuilder().food(ModFoods.RAW_ROCK_CANDY.properties(), ModFoods.RAW_ROCK_CANDY.consumable()));
	public static final DeferredItem<HardenRockCandyItem> HARDEN_CANDY = ITEMS.registerItem("harden_rock_candy", HardenRockCandyItem::new, () -> itemBuilder().food(ModFoods.HARDEN_ROCK_CANDY.properties(), ModFoods.HARDEN_ROCK_CANDY.consumable()));
	public static final DeferredItem<DebugHungerStickItem> HUNGER_STICK = ITEMS.registerItem("hunger_stick", DebugHungerStickItem::new);
	public static final DeferredItem<Item> CANDY_CORE = ITEMS.registerSimpleItem("candy_core");
	public static final DeferredItem<Item> CANDY_ROD = ITEMS.registerSimpleItem("candy_rod");
	public static final DeferredItem<BlockItem> CANDY_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.CANDY_ORE);
	public static final DeferredItem<BlockItem> CANDY_BLOCK = ITEMS.registerSimpleBlockItem(ModBlocks.CANDY_BLOCK);

	public static final DeferredItem<Item> CANDY_GEM = ITEMS.registerItem("candy_gem", CandyGemItem::new);
	public static final DeferredItem<Item> CANDY_DISPENSER = ITEMS.registerItem("candy_dispenser", CandyDispenserItem::new);

	//Special Candy
	public static final DeferredItem<Item> RED_HOT_CANDY = ITEMS.registerItem("red_hot_candy", Item::new, () -> itemBuilder().food(ModFoods.RED_HOT_CANDY.properties(), ModFoods.RED_HOT_CANDY.consumable()));
	public static final DeferredItem<Item> DEEP_BLUE_CANDY = ITEMS.registerItem("deep_blue_candy", Item::new, () -> itemBuilder().food(ModFoods.DEEP_BLUE_CANDY.properties(), ModFoods.DEEP_BLUE_CANDY.consumable()));
	public static final DeferredItem<Item> GLOW_CANDY = ITEMS.registerItem("glow_candy", Item::new, () -> itemBuilder().food(ModFoods.GLOW_CANDY.properties(), ModFoods.GLOW_CANDY.consumable()));
	public static final DeferredItem<Item> CLEAR_CANDY = ITEMS.registerItem("clear_candy", Item::new, () -> itemBuilder().food(ModFoods.CLEAR_CANDY.properties(), ModFoods.CLEAR_CANDY.consumable()));
	public static final DeferredItem<Item> FLOAT_CANDY = ITEMS.registerItem("float_candy", Item::new, () -> itemBuilder().food(ModFoods.FLOAT_CANDY.properties(), ModFoods.FLOAT_CANDY.consumable()));
	public static final DeferredItem<Item> HEALTHY_CANDY = ITEMS.registerItem("healthy_candy", Item::new, () -> itemBuilder().food(ModFoods.HEALTHY_CANDY.properties(), ModFoods.HEALTHY_CANDY.consumable()));
	public static final DeferredItem<Item> BLANK_CANDY = ITEMS.registerItem("blank_candy", Item::new, () -> itemBuilder().food(ModFoods.BLANK_CANDY.properties(), ModFoods.BLANK_CANDY.consumable()));

	//Tools

	public static final DeferredItem<CandyClubItem> CANDY_CLUB = ITEMS.registerItem("candy_club", CandyClubItem::new);
	public static final DeferredItem<CandyPickaxeItem> CANDY_CANE_PICKAXE = ITEMS.registerItem("candy_cane_pickaxe", CandyPickaxeItem::new);

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
