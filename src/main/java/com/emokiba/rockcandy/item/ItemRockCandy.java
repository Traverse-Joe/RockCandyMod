package com.emokiba.rockcandy.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemRockCandy extends ItemCandy {
    public ItemRockCandy() {
        super(1, 0.2F, false);
        this.setMaxStackSize(64);
        this.setUnlocalizedName("rockCandy");
        this.setTextureName("rockcandy:rockCandy");
        //this.setAlwaysEdible();
    }
}



