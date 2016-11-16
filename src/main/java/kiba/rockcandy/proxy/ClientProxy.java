package kiba.rockcandy.proxy;

import kiba.rockcandy.RockCandyCreativeTab;
import kiba.rockcandy.registry.ModBlocks;
import kiba.rockcandy.registry.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {



    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        registerRenderers();

    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void registerRenderers() {
        ModItems.initModels();
        ModBlocks.initModels();
    }
}
