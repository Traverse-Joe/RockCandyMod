package com.emokiba.rockcandy.item;

import net.minecraft.potion.Potion;

public class ItemSweetRockCandy extends ItemCandy
{
    public ItemSweetRockCandy()
    {
        super(1, 0.2F, false);
        this.setTextureName("rockcandy:sweetrockCandy");
        this.setUnlocalizedName("sweetrockCandy");
        this.setPotionEffect(Potion.moveSpeed.id, 30, 5, 100.0F);
        this.setAlwaysEdible();
    }
}
