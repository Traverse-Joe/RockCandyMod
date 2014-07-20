package com.emokiba.rockcandy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSugarCore extends ItemCore
{
    public ItemSugarCore(){
        super();
        this.setTextureName("rockcandy:sugarCore");
        this.setUnlocalizedName("sugarCore");
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setMaxDamage(200);

    }

}
