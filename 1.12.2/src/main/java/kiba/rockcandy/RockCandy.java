package kiba.rockcandy;


import kiba.rockcandy.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RockCandy.MODID, version = RockCandy.VERSION, name= RockCandy.MODNAME , acceptedMinecraftVersions = "[1.12,1.13)")
public class RockCandy {
    public static final String MODID = "rockcandy";
    public static final String VERSION = "1.12.2-2.03";
    public static final String MODNAME = "Rock Candy";
    public static final String CLIENTPROXY = "kiba.rockcandy.proxy.ClientProxy";
    public static final String COMMONPROXY = "kiba.rockcandy.proxy.CommonProxy";


    @SidedProxy(clientSide = RockCandy.CLIENTPROXY, serverSide = RockCandy.COMMONPROXY)
    public static CommonProxy proxy;



    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        MinecraftForge.EVENT_BUS.register(this);
        proxy.preInit(event);

    }


    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event){
        proxy.init(event);

    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){

        proxy.postInit(event);
    }


}
