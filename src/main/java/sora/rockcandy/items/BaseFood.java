package sora.rockcandy.items;

import net.minecraft.item.Food;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import sora.rockcandy.RockCandy;

public class BaseFood extends BaseItem {

  public BaseFood(String name, int saturation, int hunger) {
    super(name, new Properties().group(ItemGroup.FOOD).food(new Food.Builder().saturation(saturation).hunger(hunger).build()));
    this.setRegistryName(new ResourceLocation(RockCandy.MODID,name));
  }

  public BaseFood(String name, int saturation, int hunger, EffectInstance effect, float chance){
    super(name, new Properties().group(ItemGroup.FOOD).food(new Food.Builder().saturation(saturation).hunger(hunger).effect(effect, chance).build()));
    this.setRegistryName(new ResourceLocation(RockCandy.MODID,name));
  }
}
