package traverse.rockcandy.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoods {
	public static final FoodHelper ROCK_CANDY = new FoodHelper.Builder().properties((new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).build()).build();
	public static final FoodHelper RAW_ROCK_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build()).build();
	public static final FoodHelper HARDEN_ROCK_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build()).build();
	public static final FoodHelper RED_HOT_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4F).build())
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 120, 0, false, false)).build();
	public static final FoodHelper DEEP_BLUE_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4F).build())
			.effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 120, 0, false, false)).build();
	public static final FoodHelper GLOW_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4F).build())
			.effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 120, 0, false, false)).build();
	public static final FoodHelper CLEAR_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4F).build())
			.effect(new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 120, 0, false, false)).build();
	public static final FoodHelper FLOAT_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4F).build())
			.effect(new MobEffectInstance(MobEffects.LEVITATION, 20 * 10, 0, false, false)).build();
	public static final FoodHelper HEALTHY_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4F).build())
			.effect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 120, 1, false, false)).build();
	public static final FoodHelper BLANK_CANDY = new FoodHelper.Builder().properties(
			(new FoodProperties.Builder()).nutrition(1).saturationModifier(0.25F).build())
			.effect(0.25F, new MobEffectInstance(MobEffects.CONFUSION, 20 * 10, 0, false, false)).build();



}
