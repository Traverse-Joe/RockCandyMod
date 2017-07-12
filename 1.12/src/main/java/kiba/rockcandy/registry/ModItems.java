package kiba.rockcandy.registry;

import kiba.rockcandy.items.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static ItemRockCandy itemRockCandy;
    public static ItemRawRockCandy itemRawRockCandy;
    public static ItemHardenRockCandy itemHardenRockCandy;
    public static ItemCandyGem itemCandyGem;
    public static ItemCandyClub itemCandyClub;



    public static void init(){
        itemRockCandy = new ItemRockCandy();
        itemRawRockCandy = new ItemRawRockCandy();
        itemHardenRockCandy = new ItemHardenRockCandy();
        itemCandyGem = new ItemCandyGem();
        itemCandyClub = new ItemCandyClub();
    }


    @SideOnly(Side.CLIENT)
    public static void initModels(){
        itemRockCandy.initModel();
        itemRawRockCandy.initModel();
        itemHardenRockCandy.initModel();
        itemCandyGem.initModel();
        itemCandyClub.initModel();
    }
}
