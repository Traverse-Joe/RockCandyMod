package com.emokiba.rockcandy.item;

import com.emokiba.rockcandy.creativetab.CreativeTab;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCore extends Item
{
    public ItemCore()
    {
        super();
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTab.ROCKCANDY_TAB);


    }
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    public boolean hasContainerItem(ItemStack itemStack)
    {
        return true;
    }
    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();

        stack.setItemDamage(stack.getItemDamage() + 1);
        stack.stackSize = 1;

        return stack;
    }
}
