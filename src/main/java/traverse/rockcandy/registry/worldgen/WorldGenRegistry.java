package traverse.rockcandy.registry.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModBlocks;

public class WorldGenRegistry {

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CANDY = createConfiguredKey("ore_candy");

	public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, RockCandy.modLoc(name));
	}

	public static void configuredBootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest ruletest = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
		FeatureUtils.register(context, ORE_CANDY, Feature.ORE,
				new OreConfiguration(ruletest, ModBlocks.CANDY_ORE.get().defaultBlockState(),
						6));
	}

	public static final ResourceKey<PlacedFeature> ORE_CANDY_PLACED = createPlacedKey("ore_candy");

	public static ResourceKey<PlacedFeature> createPlacedKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, RockCandy.modLoc(name));
	}

	public static void placedBootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(context, ORE_CANDY_PLACED, holdergetter.getOrThrow(ORE_CANDY), CountPlacement.of(6),
				HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());
	}

	protected static final ResourceKey<BiomeModifier> ADD_ORE_CANDY = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
			RockCandy.modLoc("add_ore_candy"));

	public static void modifierBootstrap(BootstrapContext<BiomeModifier> context) {
		HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
		HolderGetter<PlacedFeature> placedGetter = context.lookup(Registries.PLACED_FEATURE);

		HolderSet.Named<Biome> overworldHolder = biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD);

		context.register(ADD_ORE_CANDY, new BiomeModifiers.AddFeaturesBiomeModifier(
				overworldHolder,
				HolderSet.direct(placedGetter.getOrThrow(ORE_CANDY_PLACED)),
				GenerationStep.Decoration.UNDERGROUND_ORES
		));
	}
}
