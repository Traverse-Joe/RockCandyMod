package kiba.rockcandy.registry;

import kiba.rockcandy.items.*;
import kiba.rockcandy.tools.ItemCandyClub;
import kiba.rockcandy.tools.ItemCandyPick;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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



    public static void init(){
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
    }


    @SideOnly(Side.CLIENT)
    public static void initModels(){
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
    }
}
