package sora.rockcandy.network;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketAutoFeed {
  private boolean enableAutoFeed;
  private int slot;

  public PacketAutoFeed() {
  }

  public PacketAutoFeed(boolean enableAutoFeed,int slot) {
    this.enableAutoFeed = enableAutoFeed;
    this.slot = slot;

  }

  public PacketAutoFeed(PacketBuffer buff) {
    this.enableAutoFeed = buff.readBoolean();
    this.slot = buff.readInt();

  }

  public void encode(PacketBuffer buff) {
    buff.writeBoolean(enableAutoFeed);
    buff.writeInt(slot);

  }

  public void handle(Supplier<NetworkEvent.Context> ctx) {
    ctx.get().enqueueWork(() -> {
      updateAutoFeed(ctx.get().getSender().inventory.getStackInSlot(slot), enableAutoFeed);
    });
    ctx.get().setPacketHandled(true);
  }

  public static void updateAutoFeed(ItemStack stack, boolean bool) {
    CompoundNBT compound = stack.getTag();

    if (compound == null) {
      compound = new CompoundNBT();

    }
    compound.putBoolean("autoFeed", bool);
    stack.setTag(compound);
  }
}
