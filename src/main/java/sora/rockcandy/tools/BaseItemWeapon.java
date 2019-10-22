package sora.rockcandy.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import sora.rockcandy.RockCandy;
import sora.rockcandy.RockCandyCreativeTab;

public class BaseItemWeapon extends SwordItem {
    public BaseItemWeapon(String name, IItemTier material, float speed) {
        super(material,3,speed,new Properties().group(RockCandyCreativeTab.getInstance()));
        this.setRegistryName(new ResourceLocation(RockCandy.MODID, name));

    }
}

