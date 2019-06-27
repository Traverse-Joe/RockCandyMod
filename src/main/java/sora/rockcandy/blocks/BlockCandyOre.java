package sora.rockcandy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockCandyOre extends BaseBlock {
    public BlockCandyOre() {
        super("candy_ore", Material.ROCK);
        this.setHarvestLevel("pickaxe",1);
        setSoundType(blockSoundType);
        setHardness(1.5F);
        setLightLevel(0.5F);

    }


//    @Override
//    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
//        return ModItems.itemRawRockCandy;
//    }
//    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
//    {
//        return this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1);
//    }
//    public int quantityDropped(Random p_149745_1_)
//    {
//        return 4 + p_149745_1_.nextInt(2);
//    }
}
