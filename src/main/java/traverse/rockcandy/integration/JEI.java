package traverse.rockcandy.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModItems;

@JeiPlugin
public class JEI implements IModPlugin {
	public static final ResourceLocation PLUGIN_UID = RockCandy.modLoc("jei_plugin");

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_UID;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(new ItemStack(ModItems.CANDY_GEM.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.candy_gem.desc"));
		registration.addIngredientInfo(new ItemStack(ModItems.CANDY_DISPENSER.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.candy_dispenser.desc"));
		registration.addIngredientInfo(new ItemStack(ModItems.BLANK_CANDY.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.blank_candy.desc"));
	}
}

