package sora.rockcandy.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import sora.rockcandy.RockCandyCreativeTab;
import sora.rockcandy.items.*;
import sora.rockcandy.tools.ItemCandyClub;
import sora.rockcandy.tools.ItemCandyPickaxe;

public class ModItems {

    public static final Item ROCK_CANDY = new ItemRockCandy();
    public static final Item RAW_CANDY = new ItemRawRockCandy();
    public static final Item HARDEN_CANDY = new ItemHardenRockCandy();
    public static final Item HUNGER_STICK = new ItemDebugHungerStick();
    public static final Item CANDY_CORE = new BaseItem("candy_core",new Item.Properties().group(RockCandyCreativeTab.getInstance()));
    public static final Item CANDY_ROD = new BaseItem("candy_rod", new Item.Properties().group(RockCandyCreativeTab.getInstance()));
    public static final BlockItem CANDY_ORE = new BlockItemBase(ModBlocks.CANDY_ORE);
    public static final BlockItem CANDY_BLOCK = new BlockItemBase(ModBlocks.CANDY_BLOCK);
    public static  final Item CANDY_GEM = new ItemCandyGem();
    public static final Item CANDY_DISPENSER = new ItemCandyDispenser();
    public static final Item BLANK_CANDY = new BaseItem("blank_candy",new Item.Properties().group(RockCandyCreativeTab.getInstance()));

    //Special Candy
    public static final Item RED_HOT_CANDY = new BaseFood("red_hot_candy",5, 0.4F, new EffectInstance(Effects.FIRE_RESISTANCE,20 * 120, 0, false, false), 1.0F);
    public static final Item DEEP_BLUE_CANDY = new BaseFood("deep_blue_candy",5, 0.4F, new EffectInstance(Effects.WATER_BREATHING,20 * 120, 0, false, false), 1.0F);
    public static final Item GLOW_CANDY = new BaseFood("glow_candy",5, 0.4F, new EffectInstance(Effects.NIGHT_VISION,20 * 120, 0, false, false), 1.0F);
    public static final Item CLEAR_CANDY = new BaseFood("clear_candy",5, 0.4F, new EffectInstance(Effects.INVISIBILITY,20 * 120, 0, false, false), 1.0F);
    public static final Item FLOAT_CANDY = new BaseFood("float_candy",5, 0.4F, new EffectInstance(Effects.LEVITATION,20 * 10, 0, false, false), 1.0F);
    public static final Item HEALTHY_CANDY = new BaseFood("healthy_candy",5, 0.4F, new EffectInstance(Effects.REGENERATION,20 * 120, 1, false, false), 1.0F);

    //Tools

    public static final Item CANDY_CLUB = new ItemCandyClub();
   public static final Item CANDY_CANE_PICKAXE = new ItemCandyPickaxe();

}
