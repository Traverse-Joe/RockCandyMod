package kiba.rockcandy.registry;

import kiba.rockcandy.blocks.BlockCandyOre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    public static BlockCandyOre blockCandyOre;


    public static void init(){
        blockCandyOre = new BlockCandyOre();
    }


    @SideOnly(Side.CLIENT)
    public static void initModels(){
        blockCandyOre.initModel();
    }
}
