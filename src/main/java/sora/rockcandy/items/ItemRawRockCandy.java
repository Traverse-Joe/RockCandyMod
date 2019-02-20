package sora.rockcandy.items;

import net.minecraft.item.ItemStack;

public class ItemRawRockCandy extends BaseFoodItem {

    public ItemRawRockCandy() {
        super(2, 0.3F);
    }

    @Override
    public int getMaxUseTime(ItemStack itemStack_1) {
        return 8;
    }
}
