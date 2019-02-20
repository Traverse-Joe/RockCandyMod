package sora.rockcandy.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class BaseBlock extends Block {


    public BaseBlock() {
        super(FabricBlockSettings.of(Material.STONE).hardness(1.0F).build());
    }

    public BaseBlock(FabricBlockSettings settings) {
        super(settings.build());
    }

}


