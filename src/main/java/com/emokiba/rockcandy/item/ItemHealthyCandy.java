package com.emokiba.rockcandy.item;

import net.minecraft.potion.Potion;

public class ItemHealthyCandy extends ItemCandy
{
    public ItemHealthyCandy()
    {
        super(1, 0.1F, false);
       this.setAlwaysEdible();
       this.setUnlocalizedName("healthyCandy");
       this.setPotionEffect(Potion.regeneration.id,5,5,100F);
       this.setTextureName("rockcandy:healthyCandy");
    }
}
