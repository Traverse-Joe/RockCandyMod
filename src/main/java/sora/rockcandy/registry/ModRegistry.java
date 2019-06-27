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
  public static void onBlockRegistry(RegistryEvent.Register<Block> event){
    event.getRegistry().register(ModBlocks.CANDY_ORE);
    event.getRegistry().register(ModBlocks.CANDY_BLOCK);
  }

  @SubscribeEvent
  public static void onItemRegistry(RegistryEvent.Register<Item> event){
    event.getRegistry().register(ModItems.ROCK_CANDY);
    event.getRegistry().register(ModItems.RAW_CANDY);
    event.getRegistry().register(ModItems.HARDEN_CANDY);
    event.getRegistry().register(ModItems.HUNGER_STICK);
    event.getRegistry().register(ModItems.CANDY_CORE);
    event.getRegistry().register(ModItems.CANDY_ROD);
    event.getRegistry().register(ModItems.CANDY_ORE);
    event.getRegistry().register(ModItems.CANDY_BLOCK);
    event.getRegistry().register(ModItems.RED_HOT_CANDY);
    event.getRegistry().register(ModItems.DEEP_BLUE_CANDY);
    event.getRegistry().register(ModItems.GLOW_CANDY);
    event.getRegistry().register(ModItems.CLEAR_CANDY);
    event.getRegistry().register(ModItems.FLOAT_CANDY);
    event.getRegistry().register(ModItems.HEALTHY_CANDY);
  }
}
