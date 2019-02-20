package sora.rockcandy.items;

import net.minecraft.item.Item;
import sora.rockcandy.RockCandy;

public class BaseItem extends Item {
    public BaseItem(int stackSize) {
        super(new Settings().itemGroup(RockCandy.modItemGroup).stackSize(stackSize));
    }

    public BaseItem(int stackSize, int durability){
        super(new Settings().itemGroup(RockCandy.modItemGroup).stackSize(stackSize).durability(durability));
    }
}


