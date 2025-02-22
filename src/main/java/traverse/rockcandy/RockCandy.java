package traverse.rockcandy;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import traverse.rockcandy.client.ClientHandler;
import traverse.rockcandy.network.RockCandyPacketHandler;
import traverse.rockcandy.registry.ConfigHandler;
import traverse.rockcandy.registry.ModBlocks;
import traverse.rockcandy.registry.ModItems;
import traverse.rockcandy.registry.worldgen.WorldGenRegistry;

@Mod(RockCandy.MODID)
public class RockCandy {
	public static final String MODID = "rockcandy";
	public static final Logger LOGGER = LogManager.getLogger();

	public RockCandy() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		eventBus.addListener(this::setup);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.configSpec);
		MinecraftForge.EVENT_BUS.register(this);

		ModBlocks.BLOCKS.register(eventBus);
		ModItems.ITEMS.register(eventBus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::onRegisterKeyMappings);
		});
	}

	private void setup(final FMLCommonSetupEvent event) {
		RockCandyPacketHandler.registerMessage();
		WorldGenRegistry.init();
	}


}

