package kiba.rockcandy.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeRegistry {

    public static void register(FMLPreInitializationEvent event) {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRockCandy), "X", "Y", 'X', ModItems.itemRawRockCandy, 'Y', "stickWood"));

        if (Loader.isModLoaded("tconstruct")) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRockCandy), "X", "Y", 'X', ModItems.itemRawRockCandy, 'Y', "rodStone"));
        }
    }
}
