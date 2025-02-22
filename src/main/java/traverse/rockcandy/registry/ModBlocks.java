package traverse.rockcandy.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import traverse.rockcandy.RockCandy;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RockCandy.MODID);

	public static final RegistryObject<Block> CANDY_ORE = BLOCKS.register("candy_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F).lightLevel((state) -> 5)));
	public static final RegistryObject<Block> CANDY_BLOCK = BLOCKS.register("candy_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F).lightLevel((state) -> 5)));

	//public static BlockCandyInfuser blockCandyInfuser;
}
