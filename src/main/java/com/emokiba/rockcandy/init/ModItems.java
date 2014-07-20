package com.emokiba.rockcandy.init;

import com.emokiba.rockcandy.item.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ModItems
{
    public static final ItemCandy rockCandy = new ItemRockCandy();
    public static final ItemCandy sweetrockCandy = new ItemSweetRockCandy();
    public static final Item unprocessedsugarCore = new ItemUnprocessedSugarCore();
    public static final Item sugarCore = new ItemSugarCore();
    public static final ItemFood plentifulCandy = new ItemPlentifulCandy();
    public static final ItemFood fatCandy = new ItemFatCandy();
    public static final ItemCandy healthyCandy = new ItemHealthyCandy();


    public static void init()
    {
        GameRegistry.registerItem(rockCandy,"rockcandy");
        GameRegistry.registerItem(sweetrockCandy,"sweetrockcandy");
        GameRegistry.registerItem(unprocessedsugarCore,"unprocessedsugarcore");
        GameRegistry.registerItem(sugarCore,"sugarcore");
        GameRegistry.registerItem(plentifulCandy,"plentifulcandy");
        GameRegistry.registerItem(fatCandy,"fatcandy");
        GameRegistry.registerItem(healthyCandy,"healthycandy");
    }

}
