package sora.rockcandy.blocks;

import net.minecraft.block.material.Material;

public class BlockCandyOre extends BaseBlock {
    public BlockCandyOre() {
        super("candy_ore", Properties.create(Material.ROCK).hardnessAndResistance(1.5F).lightValue(5));

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
