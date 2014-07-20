package com.emokiba.rockcandy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemCandy extends ItemFood
{
    public ItemCandy(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_)
    {
        super(1, 0.1F, false);
       this. setCreativeTab(CreativeTabs.tabFood);
        this.setMaxStackSize(64);

    }
}
