package com.emokiba.rockcandy.creativetab;

import com.emokiba.rockcandy.RockCandy;
import com.emokiba.rockcandy.init.ModItems;
import com.emokiba.rockcandy.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {
    public static final CreativeTabs ROCKCANDY_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public Item getTabIconItem() {
            return ModItems.rockCandy;
        }
    };

}



