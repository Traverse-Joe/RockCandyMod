package com.emokiba.rockcandy.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemRockCandy extends ItemFood {
    public ItemRockCandy() {
        super(1, 0.2F, false);
        setMaxStackSize(64);
        setCreativeTab(CreativeTabs.tabFood);
        setUnlocalizedName("rockCandy");
        setTextureName("rockcandy:rockCandy");
    }
}



