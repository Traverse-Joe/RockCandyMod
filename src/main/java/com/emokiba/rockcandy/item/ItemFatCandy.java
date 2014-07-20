package com.emokiba.rockcandy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

public class ItemFatCandy extends ItemFood
{
    public ItemFatCandy()
    {
        super(16, 2.0F, false);
        this.setAlwaysEdible();
        this.setMaxStackSize(64);
       this.setCreativeTab(CreativeTabs.tabFood);
        this.setTextureName("rockcandy:fatCandy");
        this.setUnlocalizedName("fatCandy");
        this.setPotionEffect(Potion.moveSlowdown.id,30,8,100F);
    }
}
