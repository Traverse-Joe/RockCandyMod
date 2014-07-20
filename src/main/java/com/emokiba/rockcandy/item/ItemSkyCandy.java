package com.emokiba.rockcandy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.potion.Potion;

public class ItemSkyCandy extends ItemCandy
{
    public ItemSkyCandy()
    {
        super(1, 0.2F, false);
        this.setUnlocalizedName("skyCandy");
        this.setAlwaysEdible();
        this.setTextureName("rockcandy:skyCandy");
        this.setPotionEffect(Potion.jump.id, 30,4,100F);
    }
}
