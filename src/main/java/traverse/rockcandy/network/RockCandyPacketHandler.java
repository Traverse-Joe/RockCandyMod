package traverse.rockcandy.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import traverse.rockcandy.RockCandy;

public class RockCandyPacketHandler {

	public static void setupPackets(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(RockCandy.MODID);

		registrar.playToServer(AutoFeedPayload.ID, AutoFeedPayload.CODEC, AutoFeedPayload.Handler::handlePayload);
	}

}
