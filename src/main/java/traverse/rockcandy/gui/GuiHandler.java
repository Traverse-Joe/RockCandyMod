//package sora.rockcandy.gui;
//
//import sora.rockcandy.gui.container.ContainerCandyInfuser;
//import sora.rockcandy.tileentity.TileEntityCandyInfuser;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.network.IGuiHandler;
//
//import javax.annotation.Nullable;
//
//public class GuiHandler implements IGuiHandler {
//
//    public static int GUI_CANDY_INFUSER = 0;
//
//    @Nullable
//    @Override
//    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//        BlockPos pos = new BlockPos(x, y, z);
//        TileEntity te = world.getTileEntity(pos);
//
//        if(ID == GUI_CANDY_INFUSER){
//            if(te instanceof TileEntityCandyInfuser){
//                return new ContainerCandyInfuser(player.inventory , (TileEntityCandyInfuser) te);
//            }
//        }
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//        BlockPos pos = new BlockPos(x, y, z);
//        TileEntity te = world.getTileEntity(pos);
//        if(ID == GUI_CANDY_INFUSER){
//            if(te instanceof TileEntityCandyInfuser){
//                TileEntityCandyInfuser containerTile = (TileEntityCandyInfuser)te;
//                return new GuiCandyInfuser(new ContainerCandyInfuser(player.inventory , containerTile ),containerTile);
//            }
//        }
//        return null;
//    }
//}
