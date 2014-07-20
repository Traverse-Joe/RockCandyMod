package com.emokiba.rockcandy;


import com.emokiba.rockcandy.handler.ConfigurationHandler;
import com.emokiba.rockcandy.init.ModBlocks;
import com.emokiba.rockcandy.init.ModItems;
import com.emokiba.rockcandy.proxy.IProxy;
import com.emokiba.rockcandy.recipe.RecipeRegistry;
import com.emokiba.rockcandy.reference.Reference;
import com.emokiba.rockcandy.utility.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME,version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)

public class RockCandy
{
    @Mod.Instance("RockCandy")
    public static RockCandy instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
       ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        LogHelper.info("Candy Being Made (PreInit)");

        ModBlocks.init();
        ModItems.init();
        RecipeRegistry.init();
    }
    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        LogHelper.info("Candy Being Mined(Init) ");
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Candy Being Eaten(PostInit)");
    }

}
