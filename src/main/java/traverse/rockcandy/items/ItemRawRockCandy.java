package traverse.rockcandy.items;

import net.minecraft.item.ItemStack;

public class ItemRawRockCandy extends BaseFood {

    public ItemRawRockCandy() {
        super("raw_rock_candy", 3, 0.3F);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 8;
    }
}
