package sora.rockcandy.registry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class WorldGenRegistry {

  private static int veinsize = ConfigHandler.general.veinOreSize.get();
  private static int spawnRate = ConfigHandler.general.spawnRate.get();
  private static int minY = ConfigHandler.general.minYLevel.get();
  private static int maxY = ConfigHandler.general.maxYLevel.get();

  public static void init() {
    for (Biome biome : Biome.BIOMES) {
      biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CANDY_ORE.getDefaultState(), spawnRate), Placement.COUNT_RANGE, new CountRangeConfig(veinsize, minY, 0, maxY)));
    }
  }
}
