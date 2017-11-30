package kiba.rockcandy.registry;

import kiba.rockcandy.tileentity.TileEntityCandyInfuser;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry {
    public static void TileEntityRegistry(){
        GameRegistry.registerTileEntity(TileEntityCandyInfuser.class,"tile_entity_candy_infuser");

    }

}
