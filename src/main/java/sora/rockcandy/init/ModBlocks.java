package sora.rockcandy.init;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import sora.rockcandy.RockCandy;
import sora.rockcandy.blocks.BaseBlock;
import sora.rockcandy.blocks.BlockCandyOre;

public class ModBlocks {

    public static Block CANDY_ORE = new BlockCandyOre();
    public static Block CANDY_BLOCK = new BaseBlock();

    public static void registerBlocks(Registry<Block> registry){
        Registry.register(registry, new Identifier(RockCandy.MODID, "candy_block"), CANDY_BLOCK);
        Registry.register(registry, new Identifier(RockCandy.MODID, "candy_ore"), CANDY_ORE);
    }
}
