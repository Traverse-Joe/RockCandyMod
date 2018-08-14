package kiba.rockcandy.integration;

import kiba.rockcandy.registry.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class JEI implements IModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry){
        registry.addIngredientInfo(new ItemStack(ModItems.itemCandyGem),ItemStack.class,"jei.candy_gem.desc");
    }
}
