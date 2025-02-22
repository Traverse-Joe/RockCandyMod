package traverse.rockcandy.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModDataComponents;

public record AutoFeedPayload(boolean enableAutoFeed, int slot) implements CustomPacketPayload {

	public static final StreamCodec<FriendlyByteBuf, AutoFeedPayload> CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			AutoFeedPayload::enableAutoFeed,
			ByteBufCodecs.INT,
			AutoFeedPayload::slot,
			AutoFeedPayload::new);
	public static final Type<AutoFeedPayload> ID = new Type<>(RockCandy.modLoc("auto_feed"));

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}

	public static class Handler {
		public static void handlePayload(AutoFeedPayload autoFeed, final IPayloadContext context) {
			context.enqueueWork(() -> {
				if(context.player() instanceof ServerPlayer serverPlayer) {
					ItemStack stack = serverPlayer.getInventory().getItem(autoFeed.slot);
					stack.set(ModDataComponents.AUTO_FEED, autoFeed.enableAutoFeed);
				}
			});
		}
	}
}
