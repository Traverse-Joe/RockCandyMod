/*package sora.rockcandy.init;

import sora.rockcandy.Globals;
import sora.rockcandy.RockCandy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler {

    public static Configuration CONFIG;
    private static String DEF_CAT = "Options";

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
        if (e.getModID().equals(RockCandy.MODID)) {
            init();
        }
    }

    public static void init() {
        if (CONFIG == null) {
            CONFIG = new Configuration(new File(Globals.CONFIG_FILE));
            MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        }

        Globals.VEIN_ORE_SIZE = CONFIG.getInt("VeinOreSize", DEF_CAT, 5, 0, 64, "How much Ore Appears in a Vein");
        Globals.MAX_Y_LEVEL = CONFIG.getInt("MaxYLevel", DEF_CAT, 64, 0, 255, "Maximum Y Level for Ore Spawn");
        Globals.MIN_Y_LEVEL = CONFIG.getInt("MinYLevel", DEF_CAT, 0, 0, 255, "Minimum Y Level for Ore Spawn");
        Globals.ORE_RARITY = CONFIG.getInt("Ore Rarity", DEF_CAT, 1, 1, 100, "The higher the number the more common they appear!");
        
        if (CONFIG.hasChanged()) {
            CONFIG.save();
        }
    }
}
*/