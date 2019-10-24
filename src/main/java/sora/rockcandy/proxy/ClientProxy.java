package sora.rockcandy.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class ClientProxy extends CommonProxy {

    public static KeyBinding autoFeedKey;

    @Override
    public PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static void registerKeys(){
        autoFeedKey = new KeyBinding("key.autofeed", GLFW.GLFW_KEY_Z, "key.categories.rockcandy");
        ClientRegistry.registerKeyBinding(autoFeedKey);
    }
    @Override
    public World getWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public void CommonSetup() {
        registerKeys();
    }
}

