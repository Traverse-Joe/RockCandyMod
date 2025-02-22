package traverse.rockcandy.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
	public static final FoodProperties ROCK_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
	public static final FoodProperties RAW_ROCK_CANDY = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
	public static final FoodProperties HARDEN_ROCK_CANDY = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
	public static final FoodProperties RED_HOT_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 120, 0, false, false), 1.0F).build();
	public static final FoodProperties DEEP_BLUE_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 120, 0, false, false), 1.0F).build();
	public static final FoodProperties GLOW_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 120, 0, false, false), 1.0F).build();
	public static final FoodProperties CLEAR_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 120, 0, false, false), 1.0F).build();
	public static final FoodProperties FLOAT_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 20 * 10, 0, false, false), 1.0F).build();
	public static final FoodProperties HEALTHY_CANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 120, 1, false, false), 1.0F).build();
	public static final FoodProperties BLANK_CANDY = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.25F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 10, 0, false, false), 0.25F).build();
}
