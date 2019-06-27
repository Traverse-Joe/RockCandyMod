//package sora.rockcandy.tools;
//
//import sora.rockcandy.RockCandy;
//import sora.rockcandy.proxy.ClientProxy;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.item.ItemPickaxe;
//import net.minecraft.item.ItemTool;
//import net.minecraftforge.client.model.ModelLoader;
//import net.minecraftforge.fml.common.registry.ForgeRegistries;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class BaseItemPick extends ItemPickaxe {
//    protected BaseItemPick(String name, ToolMaterial materialIn) {
//        super(materialIn);
//        this.setRegistryName(name);
//        this.setUnlocalizedName(RockCandy.MODID + "." + name);
//        this.setCreativeTab(ClientProxy.CREATIVE_TAB);
//        ForgeRegistries.ITEMS.register(this);
//    }
//    @SideOnly(Side.CLIENT)
//    public void initModel() {
//        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//    }
//}
