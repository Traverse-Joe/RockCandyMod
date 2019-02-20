package sora.rockcandy.items;

import net.minecraft.item.FoodItem;
import sora.rockcandy.RockCandy;

public class BaseFoodItem extends FoodItem {

    public BaseFoodItem(int hungerRestored , float saturation) {
        super(hungerRestored,saturation,false, new Settings().itemGroup(RockCandy.modItemGroup));
    }
}
