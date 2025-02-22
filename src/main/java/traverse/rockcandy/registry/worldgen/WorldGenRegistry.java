package traverse.rockcandy.registry.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModBlocks;

public class WorldGenRegistry {

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_CANDY = FeatureUtils.register(
			new ResourceLocation(RockCandy.MODID, "ore_candy").toString(),
			Feature.ORE,
			new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.CANDY_ORE.get().defaultBlockState(),
					6));

	public static void init() {
		// Load the class
	}
}
