package kiba.rockcandy.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeRegistry {

    public static void register(FMLPreInitializationEvent event) {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRockCandy), "X", "Y", 'X', ModItems.itemRawRockCandy, 'Y', "stickWood"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemHardenRockCandy,2),"RR","RR",'R',ModItems.itemRawRockCandy));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemCandyGem,1,1000),"RBR","HDH","RBR", 'R',ModItems.itemRawRockCandy, 'B', Items.DRAGON_BREATH,'H',ModItems.itemHardenRockCandy,'D',Items.DIAMOND));

      if (Loader.isModLoaded("tconstruct")) {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRockCandy), "X", "Y", 'X', ModItems.itemRawRockCandy, 'Y', "rodStone"));
      }
    }

  public static void FurniceRegister(FMLPreInitializationEvent event){
    FurnaceRecipes.instance().addSmelting(ModItems.itemHardenRockCandy, new ItemStack(Items.SUGAR ), 0.0F);
  }
}
