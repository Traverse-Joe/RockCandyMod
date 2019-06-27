package sora.rockcandy.blocks;

import sora.rockcandy.RockCandy;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BaseBlock extends Block {


    public BaseBlock(String name, Properties properties) {
        super(properties);
        this.setRegistryName(new ResourceLocation(RockCandy.MODID,name));
    }
}




