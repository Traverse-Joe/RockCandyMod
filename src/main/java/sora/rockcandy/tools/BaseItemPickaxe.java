package sora.rockcandy.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import sora.rockcandy.RockCandy;
import sora.rockcandy.RockCandyCreativeTab;

public class BaseItemPickaxe extends PickaxeItem {

  public BaseItemPickaxe(String name , IItemTier material, float speed) {
    super(material,1, speed, new Properties().group(RockCandyCreativeTab.getInstance()).addToolType(ToolType.PICKAXE, material.getHarvestLevel()));
    this.setRegistryName(new ResourceLocation(RockCandy.MODID, name));
  }
}
