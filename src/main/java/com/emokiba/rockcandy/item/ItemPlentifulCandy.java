package com.emokiba.rockcandy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemPlentifulCandy extends ItemFood
{
    public ItemPlentifulCandy()
    {
        super(4, 0.6F, false);
        setMaxStackSize(64);
        this.setTextureName("rockcandy:plentifulCandy");
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setAlwaysEdible();
        this.setUnlocalizedName("plentifulCandy");

    }
}
