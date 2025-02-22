package traverse.rockcandy.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import traverse.rockcandy.RockCandy;

public class ModBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RockCandy.MODID);

	public static final DeferredBlock<Block> CANDY_ORE = BLOCKS.register("candy_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F).lightLevel((state) -> 5)));
	public static final DeferredBlock<Block> CANDY_BLOCK = BLOCKS.register("candy_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F).lightLevel((state) -> 5)));

	//public static BlockCandyInfuser blockCandyInfuser;
}
