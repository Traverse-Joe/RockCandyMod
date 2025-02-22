package traverse.rockcandy.client;

import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

	public static void onClientSetup(final FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(KeyHandler.autoFeedKey);
	}
}
