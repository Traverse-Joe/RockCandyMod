package traverse.rockcandy.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.lwjgl.glfw.GLFW;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.items.CandyGemItem;
import traverse.rockcandy.network.AutoFeedPayload;
import traverse.rockcandy.registry.ModItems;


@EventBusSubscriber(modid = RockCandy.MODID, value = Dist.CLIENT)
public class KeyHandler {
	private static KeyMapping.Category CATEGORY = new KeyMapping.Category(RockCandy.modLoc("category"));
	public static final KeyMapping autoFeedKey = new KeyMapping("key.autofeed", GLFW.GLFW_KEY_Z, CATEGORY);

	@SubscribeEvent
	public static void onKeyPressed(InputEvent.Key event) {
		Player player = Minecraft.getInstance().player;
		if (player == null || autoFeedKey == null) {
			return;
		}
		if (autoFeedKey.consumeClick()) {
			int slot = findItem(ModItems.CANDY_GEM.get(), player);
			if (slot != -1) {
				ItemStack stack = player.getInventory().getItem(slot);
				if (!stack.isEmpty()) {
					ClientPacketDistributor.sendToServer(new AutoFeedPayload(!CandyGemItem.isAutoFeeding(stack), slot));
					player.sendOverlayMessage(Component.literal("Mode Changed"));
				}
			}
		}
	}

	public static int findItem(Item item, Player player) {
		for (int i = 0; i < player.getInventory().getNonEquipmentItems().size(); ++i) {
			ItemStack itemstack = player.getInventory().getNonEquipmentItems().get(i);
			if (!player.getInventory().getNonEquipmentItems().get(i).isEmpty() && itemstack.is(item)) {
				return i;
			}
		}

		return -1;
	}
}
