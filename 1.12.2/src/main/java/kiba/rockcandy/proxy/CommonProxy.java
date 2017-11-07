package kiba.rockcandy.proxy;

import kiba.rockcandy.RockCandy;
import kiba.rockcandy.RockCandyCreativeTab;
import kiba.rockcandy.gui.GuiHandler;
import kiba.rockcandy.items.ItemCandyGem;
import kiba.rockcandy.registry.*;
import kiba.rockcandy.world.OreGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public static CreativeTabs CREATIVE_TAB;


    public void preInit(FMLPreInitializationEvent event){
        CREATIVE_TAB = new RockCandyCreativeTab();
        ModItems.init();
        ModBlocks.init();
        RecipeRegistry.FurnaceRegister(event);
        ConfigHandler.init();


    }

    public void init(FMLInitializationEvent event){

        GameRegistry.registerWorldGenerator(new OreGen(),0);
        MinecraftForge.EVENT_BUS.register(ModItems.itemCandyGem);
        MinecraftForge.EVENT_BUS.register(ModItems.itemCandyDispenser);
        NetworkRegistry.INSTANCE.registerGuiHandler(RockCandy.instance, new GuiHandler());
        TileEntityRegistry.TileEntityRegistry();

    }

    public void postInit(FMLPostInitializationEvent event){

    }
    public void registerRenderers(){

    }

}
