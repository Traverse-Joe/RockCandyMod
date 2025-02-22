package traverse.rockcandy.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTiers {
	public static final Tier CANDY = new SimpleTier(ModTags.INCORRECT_FOR_CANDY, 1041, 8.0F, 3.0F, 10,
			() -> Ingredient.of(ModItems.HARDEN_CANDY.get()));
}
