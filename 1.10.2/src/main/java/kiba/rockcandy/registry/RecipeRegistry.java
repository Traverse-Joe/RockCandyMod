package kiba.rockcandy.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeRegistry {

  /*  public static void register(FMLPreInitializationEvent event) {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRockCandy), "X", "Y", 'X', ModItems.itemRawRockCandy, 'Y', "stickWood"));

        if (Loader.isModLoaded("tconstruct")) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRockCandy), "X", "Y", 'X', ModItems.itemRawRockCandy, 'Y', "rodStone"));
        }
    }*/
@SubscribeEvent

  public static void FurniceRegister(FMLPreInitializationEvent event){
    FurnaceRecipes.instance().addSmelting(ModItems.itemHardenRockCandy, new ItemStack(Items.SUGAR ), 0.0F);
  }
}
