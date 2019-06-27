package sora.rockcandy.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockCandy extends BaseBlock {
    public BlockCandy() {
        super("candy_block", Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F).lightValue(5));
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 1;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.PICKAXE;
    }
}
