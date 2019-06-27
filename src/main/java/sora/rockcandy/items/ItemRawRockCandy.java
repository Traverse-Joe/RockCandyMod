package sora.rockcandy.items;

import net.minecraft.item.ItemStack;

public class ItemRawRockCandy extends BaseFood {

    public ItemRawRockCandy() {
        super("raw_rock_candy", 1, 1);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 8;
    }
}
