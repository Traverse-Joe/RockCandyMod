package sora.rockcandy.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import sora.rockcandy.RockCandy;

public class BaseItem extends Item {
    public BaseItem(String name, Properties properties) {
        super(properties);
        this.setRegistryName(new ResourceLocation(RockCandy.MODID, name));
    }
}


