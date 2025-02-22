package traverse.rockcandy.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import traverse.rockcandy.RockCandy;

import java.util.List;

public class ModTiers {
	public static final Tier CANDY = TierSortingRegistry.registerTier(
			new ForgeTier(3, 1041, 8.0F, 3.0F, 10,
					ModTags.NEEDS_CANDY_TOOL,
					() -> Ingredient.of(ModItems.HARDEN_CANDY.get())),
			new ResourceLocation(RockCandy.MODID, "candy"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
}
