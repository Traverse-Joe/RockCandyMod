package kiba.rockcandy.world;

import kiba.rockcandy.Globals;
import kiba.rockcandy.registry.ModBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGen implements IWorldGenerator {
    private WorldGenerator genCandyOre;

    public OreGen(){
        this.genCandyOre = new WorldGenMinable(ModBlocks.blockCandyOre.getDefaultState(), Globals.VEIN_ORE_SIZE, BlockMatcher.forBlock(Blocks.STONE));
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
       if (world.provider.getDimension() == 0){
           for (int i =0; i <5; i++){
               int x = chunkX * 16 + random.nextInt(16);
               int y = Globals.MIN_Y_LEVEL + random.nextInt(Globals.MAX_Y_LEVEL);
               int z = chunkZ * 16 + random.nextInt(16);
               this.genCandyOre.generate(world,random, new BlockPos(x ,y ,z));
           }

       }

    }
}
