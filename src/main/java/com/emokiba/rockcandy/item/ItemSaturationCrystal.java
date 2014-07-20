package com.emokiba.rockcandy.item;

import com.emokiba.rockcandy.creativetab.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSaturationCrystal extends Item
{
    public ItemSaturationCrystal(){
        super();
        this.setTextureName("rockcandy:saturationCrystal");
        this.setCreativeTab(CreativeTab.ROCKCANDY_TAB);
        this.setUnlocalizedName("saturationCrystal");

    }
}
