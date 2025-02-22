package traverse.rockcandy.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
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
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RockCandy.MODID);

	public static final RegistryObject<Item> ROCK_CANDY = ITEMS.register("rock_candy", () -> new RockCandyItem(itemBuilder().food(ModFoods.ROCK_CANDY)));
	public static final RegistryObject<Item> RAW_CANDY = ITEMS.register("raw_rock_candy", () -> new RawRockCandyItem(itemBuilder().food(ModFoods.RAW_ROCK_CANDY)));
	public static final RegistryObject<Item> HARDEN_CANDY = ITEMS.register("harden_rock_candy", () -> new HardenRockCandyItem(itemBuilder().food(ModFoods.HARDEN_ROCK_CANDY)));
	public static final RegistryObject<Item> HUNGER_STICK = ITEMS.register("hunger_stick", () -> new DebugHungerStickItem(itemBuilder()));
	public static final RegistryObject<Item> CANDY_CORE = ITEMS.register("candy_core", () -> new Item(itemBuilder()));
	public static final RegistryObject<Item> CANDY_ROD = ITEMS.register("candy_rod", () -> new Item(itemBuilder()));
	public static final RegistryObject<Item> CANDY_ORE = ITEMS.register("candy_ore", () -> new BlockItem(ModBlocks.CANDY_ORE.get(), itemBuilder()));
	public static final RegistryObject<Item> CANDY_BLOCK = ITEMS.register("candy_block", () -> new BlockItem(ModBlocks.CANDY_BLOCK.get(), itemBuilder()));

	public static final RegistryObject<Item> CANDY_GEM = ITEMS.register("candy_gem", () -> new CandyGemItem(itemBuilder()));
	public static final RegistryObject<Item> CANDY_DISPENSER = ITEMS.register("candy_dispenser", () -> new CandyDispenserItem(itemBuilder()));

	//Special Candy
	public static final RegistryObject<Item> RED_HOT_CANDY = ITEMS.register("red_hot_candy", () -> new Item(itemBuilder().food(ModFoods.RED_HOT_CANDY)));
	public static final RegistryObject<Item> DEEP_BLUE_CANDY = ITEMS.register("deep_blue_candy", () -> new Item(itemBuilder().food(ModFoods.DEEP_BLUE_CANDY)));
	public static final RegistryObject<Item> GLOW_CANDY = ITEMS.register("glow_candy", () -> new Item(itemBuilder().food(ModFoods.GLOW_CANDY)));
	public static final RegistryObject<Item> CLEAR_CANDY = ITEMS.register("clear_candy", () -> new Item(itemBuilder().food(ModFoods.CLEAR_CANDY)));
	public static final RegistryObject<Item> FLOAT_CANDY = ITEMS.register("float_candy", () -> new Item(itemBuilder().food(ModFoods.FLOAT_CANDY)));
	public static final RegistryObject<Item> HEALTHY_CANDY = ITEMS.register("healthy_candy", () -> new Item(itemBuilder().food(ModFoods.HEALTHY_CANDY)));
	public static final RegistryObject<Item> BLANK_CANDY = ITEMS.register("blank_candy", () -> new Item(itemBuilder().food(ModFoods.BLANK_CANDY)));

	//Tools

	public static final RegistryObject<Item> CANDY_CLUB = ITEMS.register("candy_club", () -> new CandyClubItem(itemBuilder()));
	public static final RegistryObject<Item> CANDY_CANE_PICKAXE = ITEMS.register("candy_cane_pickaxe", () -> new CandyPickaxeItem(itemBuilder()));

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
