package sora.rockcandy.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import sora.rockcandy.init.ModBlocks;

public class RockCandyWorldGen {

    public static void init(){
        for (Biome biome: Biome.BIOMES){
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,Biome.configureFeature(Feature.ORE,new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.CANDY_ORE.getDefaultState(),6), Decorator.COUNT_RANGE,new RangeDecoratorConfig(10,0,0,64)));
        }
    }
}
