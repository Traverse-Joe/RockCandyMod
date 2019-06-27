package sora.rockcandy;

import sora.rockcandy.registry.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RockCandyCreativeTab extends CreativeTabs {
    public RockCandyCreativeTab(){
        super(RockCandy.MODID);
    }
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.itemRockCandy);

    }
}
