package com.emokiba.rockcandy.init;

import com.emokiba.rockcandy.item.ItemRockCandy;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;

public class ModItems
{
    public static final ItemFood rockCandy = new ItemRockCandy();

    public static void init()
    {
        GameRegistry.registerItem(rockCandy,"rockcandy");
    }

}
