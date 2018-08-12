package kiba.rockcandy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCandy extends BaseBlock {
    public BlockCandy() {
        super("candy_block", Material.ROCK);
        this.setHarvestLevel("pickaxe",1);
        setSoundType(blockSoundType);
        setHardness(1.5F);
        setLightLevel(0.5F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

}
