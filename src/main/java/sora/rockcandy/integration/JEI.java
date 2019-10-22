package sora.rockcandy.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import sora.rockcandy.RockCandy;
import sora.rockcandy.registry.ModIntegration;
import sora.rockcandy.registry.ModItems;

@JeiPlugin
public  class JEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RockCandy.MODID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if(ModIntegration.Mods.JEI.isLoaded()){
            registration.addIngredientInfo(new ItemStack(ModItems.CANDY_GEM), VanillaTypes.ITEM,"jei.candy_gem.desc");
            registration.addIngredientInfo(new ItemStack(ModItems.CANDY_DISPENSER), VanillaTypes.ITEM,"jei.candy_dispenser.desc");
            registration.addIngredientInfo(new ItemStack(ModItems.BLANK_CANDY), VanillaTypes.ITEM,"jei.blank_candy.desc");
        }
    }
}
