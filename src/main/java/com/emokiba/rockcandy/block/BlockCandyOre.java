package com.emokiba.rockcandy.block;

import com.emokiba.rockcandy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockCandyOre extends Block
{
    public BlockCandyOre()
    {
        super(Material.rock);
        setHarvestLevel("pickaxe",1);
        setStepSound(soundTypeStone);
        setHardness(1.5f);
        setLightLevel(0.5f);
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName("candyOre");
        setBlockTextureName("rockcandy:candyOre");
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return ModItems.rockCandy;
    }


    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1);
    }
    public int quantityDropped(Random p_149745_1_)
    {
        return 4 + p_149745_1_.nextInt(2);
    }
}




