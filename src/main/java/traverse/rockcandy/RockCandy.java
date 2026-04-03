package traverse.rockcandy;


import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import traverse.rockcandy.client.ClientHandler;
import traverse.rockcandy.network.RockCandyPacketHandler;
import traverse.rockcandy.registry.ConfigHandler;
import traverse.rockcandy.registry.ModBlocks;
import traverse.rockcandy.registry.ModDataComponents;
import traverse.rockcandy.registry.ModItems;
import traverse.rockcandy.registry.ModTabs;

@Mod(RockCandy.MODID)
public class RockCandy {
	public static final String MODID = "rockcandy";
	public static final Logger LOGGER = LogManager.getLogger();

	public RockCandy(IEventBus eventBus, Dist dist, ModContainer container) {
		container.registerConfig(ModConfig.Type.COMMON, ConfigHandler.configSpec);

		eventBus.addListener(RockCandyPacketHandler::setupPackets);

		ModDataComponents.DATA_COMPONENT_TYPES.register(eventBus);
		ModBlocks.BLOCKS.register(eventBus);
		ModItems.ITEMS.register(eventBus);
		ModTabs.CREATIVE_MODE_TABS.register(eventBus);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
			eventBus.addListener(ClientHandler::onRegisterKeyMappings);
		}
	}

	public static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MODID, path);
	}
}

