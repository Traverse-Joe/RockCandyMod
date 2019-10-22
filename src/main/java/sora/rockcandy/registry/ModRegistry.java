package sora.rockcandy.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sora.rockcandy.RockCandy;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(bus = MOD, modid = RockCandy.MODID)
public class ModRegistry {

  @SubscribeEvent
  public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
    event.getRegistry().registerAll(
        ModBlocks.CANDY_ORE,
        ModBlocks.CANDY_BLOCK
    );
  }

  @SubscribeEvent
  public static void onItemRegistry(RegistryEvent.Register<Item> event) {
    event.getRegistry().registerAll(
        ModItems.ROCK_CANDY,
        ModItems.RAW_CANDY,
        ModItems.HARDEN_CANDY,
        ModItems.HUNGER_STICK,
        ModItems.CANDY_CORE,
        ModItems.CANDY_ROD,
        ModItems.CANDY_ORE,
        ModItems.CANDY_BLOCK,
        ModItems.RED_HOT_CANDY,
        ModItems.DEEP_BLUE_CANDY,
        ModItems.GLOW_CANDY,
        ModItems.CLEAR_CANDY,
        ModItems.FLOAT_CANDY,
        ModItems.HEALTHY_CANDY,
        ModItems.CANDY_GEM,
        ModItems.CANDY_DISPENSER,
        ModItems.BLANK_CANDY,
        ModItems.CANDY_CANE_PICKAXE,
        ModItems.CANDY_CLUB
    );
  }
}
