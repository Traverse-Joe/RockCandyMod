package traverse.rockcandy.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.items.CandyGemItem;
import traverse.rockcandy.network.AutoFeedPayload;
import traverse.rockcandy.registry.ModItems;


@EventBusSubscriber(bus = Bus.GAME, modid = RockCandy.MODID)
public class KeyHandler {
	public static final KeyMapping autoFeedKey = new KeyMapping("key.autofeed", GLFW.GLFW_KEY_Z, "key.categories.rockcandy");

	@SubscribeEvent
	public static void onKeyPressed(InputEvent.Key event) {
		Player player = Minecraft.getInstance().player;
		if (player == null) {
			return;
		}
		int slot = findItem(ModItems.CANDY_GEM.get(), player);
		ItemStack stack = player.getInventory().getItem(Math.max(slot, 0));
		if (autoFeedKey != null && autoFeedKey.consumeClick() && !stack.isEmpty()) {
			PacketDistributor.sendToServer(new AutoFeedPayload(!CandyGemItem.isAutoFeeding(stack), slot));
			player.displayClientMessage(Component.literal("Mode Changed"), true);
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
