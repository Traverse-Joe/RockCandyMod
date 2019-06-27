//package sora.rockcandy.integration;
//
//import sora.rockcandy.registry.ModBlocks;
//import sora.rockcandy.registry.ModIntegration;
//import sora.rockcandy.registry.ModItems;
//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.IModRegistry;
//import mezz.jei.api.JEIPlugin;
//import net.minecraft.item.ItemStack;
//
//import javax.annotation.Nonnull;
//
//@JEIPlugin
//public class JEI implements IModPlugin {
//
//    @Override
//    public void register(@Nonnull IModRegistry registry){
//        if(ModIntegration.Mods.JEI.isLoaded()){
//            registry.addIngredientInfo(new ItemStack(ModItems.itemCandyGem),ItemStack.class,"jei.candy_gem.desc");
//            registry.addIngredientInfo(new ItemStack(ModItems.itemCandyDispenser),ItemStack.class,"jei.candy_dispenser.desc");
//            registry.addIngredientInfo(new ItemStack(ModBlocks.blockCandyInfuser),ItemStack.class,"jei.candy_infuser.desc");
//        }
//
//    }
//}
