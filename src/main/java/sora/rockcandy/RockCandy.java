package sora.rockcandy;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import sora.rockcandy.init.ModBlocks;
import sora.rockcandy.init.ModItems;
import sora.rockcandy.recipe.RecipeRegistry;
import sora.rockcandy.utils.Config;
import sora.rockcandy.world.RockCandyWorldGen;


public class RockCandy implements ModInitializer {
    public static ItemGroup modItemGroup = FabricItemGroupBuilder.build(new Identifier(RockCandy.MODID, "rockcandy"), () -> new ItemStack(ModItems.ROCK_CANDY));
    public static final String MODID = "rockcandy";
    public static final String VERSION = "1.14-1.00";
    public static Config config = new Config(MODID, RockCandy.class);


    @Override
    public void onInitialize() {
        ModItems.registerItems(Registry.ITEM);
        ModBlocks.registerBlocks(Registry.BLOCK);
        RockCandyWorldGen.init();
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID,"gem_shaped"), RecipeRegistry.RECIPE_SERIALIZER);
    }


}
