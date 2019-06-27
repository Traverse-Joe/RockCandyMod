//package sora.rockcandy.world;
//
//import java.util.Random;
//
//import sora.rockcandy.Globals;
//import sora.rockcandy.registry.ModBlocks;
//import net.minecraft.block.pattern.BlockStateMatcher;
//import net.minecraft.block.state.pattern.BlockMatcher;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.chunk.AbstractChunkProvider;
//import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.IChunkGenerator;
//import net.minecraft.world.gen.WorldGenRegion;
//import net.minecraft.world.gen.feature.WorldGenMinable;
//import net.minecraft.world.gen.feature.WorldGenerator;
//import net.minecraftforge.fml.common.IWorldGenerator;
//
//public class OreGen implements IWorldGenerator {
//    private WorldGenerator genCandyOre;
//
//    public OreGen(){
//        this.genCandyOre = new WorldGenRegion(ModBlocks.blockCandyOre.getDefaultState(), Globals.VEIN_ORE_SIZE, BlockStateMatcher.forBlock(Blocks.STONE));
//    }
//@Override
//    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
//       if (world.provider.getDimension() == 0){
//           for (int i =0; i <Globals.ORE_RARITY; i++){
//               int x = chunkX * 16 + random.nextInt(16);
//               int y = Globals.MIN_Y_LEVEL + random.nextInt(Globals.MAX_Y_LEVEL);
//               int z = chunkZ * 16 + random.nextInt(16);
//               this.genCandyOre.generate(world,random, new BlockPos(x ,y ,z));
//           }
//
//       }
//
//    }
//
//
//    @Override
//    public void generate(Random random, int chunkX, int chunkZ, World world, ChunkGenerator chunkGenerator, AbstractChunkProvider chunkProvider) {
//
//    }
//}
