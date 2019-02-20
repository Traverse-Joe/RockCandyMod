package sora.rockcandy.init;

import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import sora.rockcandy.RockCandy;
import sora.rockcandy.items.*;

public class ModItems {

    public static Item ROCK_CANDY = new ItemRockCandy();
    public static Item RAW_ROCK_CANDY = new ItemRawRockCandy();
    public static Item HARDENED_CANDY = new ItemHardenRockCandy();
    public static Item CANDY_GEM = new ItemCandyGem();
   // public static ItemCandyClub itemCandyClub;
    public static Item CANDY_DISPENSER = new ItemCandyDispenser();
    public static Item DEBUG_STICK = new ItemDebugHungerStick();
    //public static ItemCandyPick itemCandyPick;

    //public static BaseItem itemCandyCore;
   // public static BaseItem itemCandyRod;

    public static void registerItems(Registry<Item> registry) {
        Registry.register(registry, new Identifier(RockCandy.MODID, "rock_candy"), ROCK_CANDY);
        Registry.register(registry, new Identifier(RockCandy.MODID, "raw_rock_candy"), RAW_ROCK_CANDY);
        Registry.register(registry, new Identifier(RockCandy.MODID, "harden_rock_candy"), HARDENED_CANDY);
        Registry.register(registry, new Identifier(RockCandy.MODID, "candy_gem"), CANDY_GEM);
        Registry.register(registry, new Identifier(RockCandy.MODID, "candy_dispenser"), CANDY_DISPENSER);
        Registry.register(registry, new Identifier(RockCandy.MODID, "hunger_stick"), DEBUG_STICK);
        Registry.register(registry, new Identifier(RockCandy.MODID, "candy_ore"), new BlockItem(ModBlocks.CANDY_ORE,new Item.Settings().itemGroup(RockCandy.modItemGroup)));


    }
}