package com.emokiba.rockcandy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemUnprocessedSugarCore extends Item
{
    public ItemUnprocessedSugarCore()
    {
        super();
        this.setMaxStackSize(1);
        this.setTextureName("rockcandy:unprocessedsugarCore");
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("unprocessedsugarCore");

    }
}
