package traverse.rockcandy.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketAutoFeed {
	private boolean enableAutoFeed;
	private int slot;

	public PacketAutoFeed() {
	}

	public PacketAutoFeed(boolean enableAutoFeed, int slot) {
		this.enableAutoFeed = enableAutoFeed;
		this.slot = slot;

	}

	public PacketAutoFeed(FriendlyByteBuf buff) {
		this.enableAutoFeed = buff.readBoolean();
		this.slot = buff.readInt();

	}

	public void encode(FriendlyByteBuf buff) {
		buff.writeBoolean(enableAutoFeed);
		buff.writeInt(slot);

	}

	public static void handle(PacketAutoFeed autoFeed, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			updateAutoFeed(ctx.get().getSender().getInventory().getItem(autoFeed.slot), autoFeed.enableAutoFeed);
		});
		ctx.get().setPacketHandled(true);
	}

	public static void updateAutoFeed(ItemStack stack, boolean bool) {
		CompoundTag compound = stack.getTag();

		if (compound == null) {
			compound = new CompoundTag();

		}
		compound.putBoolean("autoFeed", bool);
		stack.setTag(compound);
	}
}
