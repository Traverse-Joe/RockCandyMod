package kiba.rockcandy.registry;

import kiba.rockcandy.items.ItemRawRockCandy;
import kiba.rockcandy.items.ItemRockCandy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static ItemRockCandy itemRockCandy;
    public static ItemRawRockCandy itemRawRockCandy;



    public static void init(){
        itemRockCandy = new ItemRockCandy();
        itemRawRockCandy = new ItemRawRockCandy();
    }


    @SideOnly(Side.CLIENT)
    public static void initModels(){
        itemRockCandy.initModel();
        itemRawRockCandy.initModel();
    }
}
