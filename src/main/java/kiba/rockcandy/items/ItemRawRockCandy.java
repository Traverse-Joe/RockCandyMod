package kiba.rockcandy.items;

import net.minecraft.item.ItemStack;

public class ItemRawRockCandy extends BaseFoodItem {

    public ItemRawRockCandy() {
        super("raw_rock_candy", 1, 0.1F, false);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 8;
    }
}
