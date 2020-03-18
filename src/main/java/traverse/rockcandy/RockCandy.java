package traverse.rockcandy;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import traverse.rockcandy.network.RockCandyPacketHandler;
import traverse.rockcandy.proxy.ClientProxy;
import traverse.rockcandy.proxy.CommonProxy;
import traverse.rockcandy.proxy.IProxy;
import traverse.rockcandy.registry.ConfigHandler;
import traverse.rockcandy.registry.WorldGenRegistry;

import java.io.File;

@Mod(RockCandy.MODID)
public class RockCandy {
  private static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new CommonProxy());
  public static final String MODID = "rockcandy";
  public static final Logger LOGGER = LogManager.getLogger();
  public static File CONFIG_DIR = new File(FMLPaths.CONFIGDIR.get().toFile(), MODID);

  public RockCandy() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.configSpec);
    MinecraftForge.EVENT_BUS.register(this);
  }

  public static IProxy getProxy() {
    return proxy;
  }

  private void setup(final FMLCommonSetupEvent event){
    if (!CONFIG_DIR.exists() && !CONFIG_DIR.mkdir()) {
      LOGGER.warn("Impossible to create the config folder");
    }
    WorldGenRegistry.init();
    RockCandyPacketHandler.registerMessage();
    proxy.CommonSetup();
  }


}

