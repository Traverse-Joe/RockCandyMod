package traverse.rockcandy.client;

import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public class ClientHandler {

	public static void onRegisterKeyMappings(final RegisterKeyMappingsEvent event) {
		event.register(KeyHandler.autoFeedKey);
	}
}
