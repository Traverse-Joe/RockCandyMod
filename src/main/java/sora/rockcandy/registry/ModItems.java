package sora.rockcandy.registry;

import kiba.rockcandy.items.*;
import sora.rockcandy.items.special_candy.BaseSpecialCandy;
import sora.rockcandy.tools.ItemCandyClub;
import sora.rockcandy.tools.ItemCandyPick;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sora.rockcandy.items.*;

public class ModItems {

    public static ItemRockCandy itemRockCandy;
    public static ItemRawRockCandy itemRawRockCandy;
    public static ItemHardenRockCandy itemHardenRockCandy;
    public static ItemCandyGem itemCandyGem;
    public static ItemCandyClub itemCandyClub;
    public static ItemCandyDispenser itemCandyDispenser;
    public static ItemDebugHungerStick itemDebugHungerStick;
    public static ItemCandyPick itemCandyPick;

    public static BaseItem itemCandyCore;
    public static BaseItem itemCandyRod;
    public static BaseSpecialCandy itemRedHotCandy;
    public static BaseSpecialCandy itemDeepBlueCandy;
    public static BaseSpecialCandy itemGlowCandy;
    public static BaseSpecialCandy itemClearCandy;
    public static BaseSpecialCandy itemFloatCandy;
    public static BaseSpecialCandy itemHealthyCandy;


    public static void init() {
        itemRockCandy = new ItemRockCandy();
        itemRawRockCandy = new ItemRawRockCandy();
        itemHardenRockCandy = new ItemHardenRockCandy();
        itemCandyGem = new ItemCandyGem();
        itemCandyClub = new ItemCandyClub();
        itemCandyDispenser = new ItemCandyDispenser();
        itemDebugHungerStick = new ItemDebugHungerStick();
        itemCandyPick = new ItemCandyPick();

        itemCandyCore = new BaseItem("candy_core");
        itemCandyRod = new BaseItem("candy_rod");

        itemRedHotCandy = (BaseSpecialCandy) new BaseSpecialCandy("red_hot_candy", 3, 0.5F, false).setPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 20 * 120, 0, false, false), 100);
        itemDeepBlueCandy = (BaseSpecialCandy) new BaseSpecialCandy("deep_blue_candy", 3, 0.5F, false).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20 * 120, 0, false, false), 100);
        itemGlowCandy = (BaseSpecialCandy) new BaseSpecialCandy("glow_candy", 3, 0.5F, false).setPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20 * 120, 0, false, false), 100);
        itemClearCandy = (BaseSpecialCandy) new BaseSpecialCandy("clear_candy", 3,0.5F,false).setPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 20 * 120, 0, false, false), 100);
        itemFloatCandy = (BaseSpecialCandy) new BaseSpecialCandy("float_candy", 3,0.5F,false).setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 20 * 120, 0, false, false), 100);
        itemHealthyCandy = (BaseSpecialCandy) new BaseSpecialCandy("healthy_candy", 3,0.5F,false).setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 * 120, 1, false, false), 100);

    }


    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemRockCandy.initModel();
        itemRawRockCandy.initModel();
        itemHardenRockCandy.initModel();
        itemCandyGem.initModel();
        itemCandyClub.initModel();
        itemCandyDispenser.initModel();
        itemDebugHungerStick.initModel();
        itemCandyPick.initModel();
        itemCandyCore.initModel();
        itemCandyRod.initModel();
        itemRedHotCandy.initModel();
        itemDeepBlueCandy.initModel();
        itemGlowCandy.initModel();
        itemClearCandy.initModel();
        itemFloatCandy.initModel();
        itemHealthyCandy.initModel();
    }
}
