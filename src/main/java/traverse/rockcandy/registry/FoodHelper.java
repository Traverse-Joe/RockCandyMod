package traverse.rockcandy.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

/**
 * A helper class for creating food items with specific properties and consumable effects.
 * @param properties the food properties defining the nutritional value and other characteristics of the food item
 * @param consumable the consumable component that defines the effects applied when the food is consumed
 */
public record FoodHelper(FoodProperties properties, Consumable consumable) {

	public static class Builder {
		private FoodProperties properties;
		private Consumable.Builder consumable;

		public Builder properties(FoodProperties properties) {
			this.properties = properties;
			this.consumable = Consumables.defaultFood();
			return this;
		}

		public Builder effect(float probability, MobEffectInstance... effects) {
			this.consumable.onConsume(
					new ApplyStatusEffectsConsumeEffect(
							List.of(
									effects
							),
							probability
					)
			);
			return this;
		}

		public Builder effect(MobEffectInstance... effects) {
			this.consumable.onConsume(
					new ApplyStatusEffectsConsumeEffect(
							List.of(
									effects
							)
					)
			);
			return this;
		}

		public FoodHelper build() {
			return new FoodHelper(properties, consumable.build());
		}
	}
}
