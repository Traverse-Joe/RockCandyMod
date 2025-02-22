package traverse.rockcandy.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.items.CandyGemItem;
import traverse.rockcandy.network.PacketAutoFeed;
import traverse.rockcandy.network.RockCandyPacketHandler;
import traverse.rockcandy.registry.ModItems;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;


@Mod.EventBusSubscriber(bus = FORGE, modid = RockCandy.MODID, value = Dist.CLIENT)
public class KeyHandler {
	public static final KeyMapping autoFeedKey = new KeyMapping("key.autofeed", GLFW.GLFW_KEY_Z, "key.categories.rockcandy");

	@SubscribeEvent
	public static void onKeyPressed(InputEvent.KeyInputEvent event) {
		Player player = Minecraft.getInstance().player;
		if (player == null) {
			return;
		}
		int slot = findItem(ModItems.CANDY_GEM.get(), player);
		ItemStack stack = player.getInventory().getItem(Math.max(slot, 0));
		if (autoFeedKey != null && autoFeedKey.consumeClick() && !stack.isEmpty()) {
			RockCandyPacketHandler.INSTANCE.sendToServer(new PacketAutoFeed(!CandyGemItem.isAutoFeeding(stack), slot));
			player.displayClientMessage(new TextComponent("Mode Changed"), true);
		}
	}

	public static int findItem(Item item, Player player) {
		for (int i = 0; i < player.getInventory().items.size(); ++i) {
			ItemStack itemstack = player.getInventory().items.get(i);
			if (!player.getInventory().items.get(i).isEmpty() && itemstack.is(item)) {
				return i;
			}
		}

		return -1;
	}
}
