package traverse.rockcandy.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void buildRecipes(RecipeOutput output, HolderLookup.Provider provider) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.CANDY_BLOCK.get())
				.pattern("HHH")
				.pattern("HHH")
				.pattern("HHH")
				.define('H', ModItems.HARDEN_CANDY.get())
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get())).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CANDY_CANE_PICKAXE.get())
				.pattern("HHH")
				.pattern(" R ")
				.pattern(" R ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('R', ModItems.CANDY_ROD.get())
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_candy_rod", has(ModItems.CANDY_ROD.get())).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CANDY_CLUB.get())
				.pattern(" H ")
				.pattern(" H ")
				.pattern(" R ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('R', ModItems.CANDY_ROD.get())
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_candy_rod", has(ModItems.CANDY_ROD.get())).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CANDY_CORE.get())
				.pattern("HSH")
				.pattern("SDS")
				.pattern("HSH")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('S', Items.SUGAR)
				.define('D', Tags.Items.GEMS_DIAMOND)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(output);

		ItemStack dispenserStack = new ItemStack(ModItems.CANDY_DISPENSER.get());
		dispenserStack.setDamageValue(50);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, dispenserStack)
				.pattern("IHI")
				.pattern("ICI")
				.pattern("IHI")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('I', Tags.Items.INGOTS_IRON)
				.define('C', Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
				.unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN)).save(output);

		ItemStack gemStack = new ItemStack(ModItems.CANDY_GEM.get());
		gemStack.setDamageValue(1000);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, gemStack)
				.pattern("RBR")
				.pattern("HDH")
				.pattern("RBR")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('R', ModItems.RAW_CANDY.get())
				.define('D', Tags.Items.GEMS_DIAMOND)
				.define('B', Items.DRAGON_BREATH)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_raw_candy", has(ModItems.RAW_CANDY.get()))
				.unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
				.unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH)).save(output);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CANDY_CORE.get()), RecipeCategory.MISC,
						new ItemStack(ModItems.CANDY_ROD.get(), 2), 0.6F, 200)
				.unlockedBy("has_candy_core", has(ModItems.CANDY_CORE.get()))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CLEAR_CANDY.get(), 4)
				.pattern(" H ")
				.pattern("NBN")
				.pattern(" S ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('B', ModItems.BLANK_CANDY.get())
				.define('N', Items.NETHER_WART)
				.define('S', Items.FERMENTED_SPIDER_EYE)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_blank_candy", has(ModItems.BLANK_CANDY.get()))
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.unlockedBy("has_fermented_spider_eye", has(Items.FERMENTED_SPIDER_EYE)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DEEP_BLUE_CANDY.get(), 4)
				.pattern(" H ")
				.pattern("NBN")
				.pattern(" S ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('B', ModItems.BLANK_CANDY.get())
				.define('N', Items.NETHER_WART)
				.define('S', Items.PRISMARINE_SHARD)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_blank_candy", has(ModItems.BLANK_CANDY.get()))
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.unlockedBy("has_prismarine_shard", has(Items.PRISMARINE_SHARD)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.FLOAT_CANDY.get(), 4)
				.pattern(" H ")
				.pattern("NBN")
				.pattern(" S ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('B', ModItems.BLANK_CANDY.get())
				.define('N', Items.NETHER_WART)
				.define('S', Items.SHULKER_SHELL)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_blank_candy", has(ModItems.BLANK_CANDY.get()))
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.unlockedBy("has_shulker_shell", has(Items.SHULKER_SHELL)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GLOW_CANDY.get(), 4)
				.pattern(" H ")
				.pattern("NBN")
				.pattern(" S ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('B', ModItems.BLANK_CANDY.get())
				.define('N', Items.NETHER_WART)
				.define('S', Items.GOLDEN_CARROT)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_blank_candy", has(ModItems.BLANK_CANDY.get()))
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.unlockedBy("has_golden_carrot", has(Items.GOLDEN_CARROT)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.HEALTHY_CANDY.get(), 4)
				.pattern(" H ")
				.pattern("NBN")
				.pattern(" S ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('B', ModItems.BLANK_CANDY.get())
				.define('N', Items.NETHER_WART)
				.define('S', Items.GHAST_TEAR)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_blank_candy", has(ModItems.BLANK_CANDY.get()))
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.unlockedBy("has_ghast_tear", has(Items.GHAST_TEAR)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.RED_HOT_CANDY.get(), 4)
				.pattern(" H ")
				.pattern("NBN")
				.pattern(" S ")
				.define('H', ModItems.HARDEN_CANDY.get())
				.define('B', ModItems.BLANK_CANDY.get())
				.define('N', Items.NETHER_WART)
				.define('S', Items.MAGMA_CREAM)
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.unlockedBy("has_blank_candy", has(ModItems.BLANK_CANDY.get()))
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.unlockedBy("has_magma_cream", has(Items.MAGMA_CREAM)).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.HARDEN_CANDY.get(), 2)
				.pattern("RR")
				.pattern("RR")
				.define('R', ModItems.RAW_CANDY.get())
				.unlockedBy("has_raw_candy", has(ModItems.RAW_CANDY.get())).save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RAW_CANDY.get(), 2)
				.requires(ModItems.HARDEN_CANDY.get())
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get())).save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RAW_CANDY.get(), 9)
				.requires(ModItems.CANDY_BLOCK.get())
				.unlockedBy("has_harden_candy", has(ModItems.HARDEN_CANDY.get()))
				.save(output, RockCandy.modLoc("raw_rock_candy2"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ROCK_CANDY.get())
				.requires(ModItems.RAW_CANDY.get())
				.requires(Tags.Items.RODS_WOODEN)
				.unlockedBy("has_raw_candy", has(ModItems.RAW_CANDY.get()))
				.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN)).save(output);

	}
}