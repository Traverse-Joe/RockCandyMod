package sora.rockcandy.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import sora.rockcandy.RockCandyCreativeTab;

public class BaseFood extends BaseItem {

  public BaseFood(String name, int hunger, float saturation) {
    super(name, new Properties().group(RockCandyCreativeTab.getInstance()).food(new Food.Builder().saturation(saturation).hunger(hunger).build()));
  }

  public BaseFood(String name, int hunger, float saturation,  EffectInstance effect, float chance){
    super(name, new Properties().group(RockCandyCreativeTab.getInstance()).food(new Food.Builder().saturation(saturation).hunger(hunger).effect(effect, chance).build()));
  }
}
