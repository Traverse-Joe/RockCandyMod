package sora.rockcandy.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import sora.rockcandy.RockCandy;
import sora.rockcandy.init.ModBlocks;

public class RockCandyWorldGen {

    public static int veinsize = RockCandy.config.getInt("vein-size");
    public static int spawnRate = RockCandy.config.getInt("spawn-rate");
    public static int minY = RockCandy.config.getInt("min-y-level");
    public static int maxY = RockCandy.config.getInt("max-y-level");


    public static void init(){
        for (Biome biome: Biome.BIOMES){
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,Biome.configureFeature(Feature.ORE,new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.CANDY_ORE.getDefaultState(),veinsize), Decorator.COUNT_RANGE,new RangeDecoratorConfig(spawnRate,minY, 0, maxY)));
        }
    }
}
