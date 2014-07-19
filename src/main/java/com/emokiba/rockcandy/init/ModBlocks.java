package com.emokiba.rockcandy.init;

import com.emokiba.rockcandy.block.BlockCandyOre;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static final Block candyOre = new BlockCandyOre();
    public static void init()
    {
        GameRegistry.registerBlock(candyOre,"candyOre");
    }
}
