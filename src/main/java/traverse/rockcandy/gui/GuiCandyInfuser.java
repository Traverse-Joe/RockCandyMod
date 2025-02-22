//package sora.rockcandy.gui;
//
//import RockCandy;
//import sora.rockcandy.gui.container.ContainerCandyInfuser;
//import sora.rockcandy.tileentity.TileEntityCandyInfuser;
//import net.minecraft.client.gui.inventory.GuiContainer;
//import net.minecraft.util.ResourceLocation;
//
//public class GuiCandyInfuser extends GuiContainer {
//    public static ResourceLocation infuser_res = RockCandy.modLoc("textures/gui/candy_infuser.png");
//    public int Width = 176;
//    public int Height = 166;
//    TileEntityCandyInfuser infuser;
//
//    public GuiCandyInfuser(ContainerCandyInfuser container, TileEntityCandyInfuser te) {
//        super(container);
//        xSize = Width;
//        ySize = Height;
//        infuser = (TileEntityCandyInfuser) te;
//    }
//
//    @Override
//    public void initGui() {
//        super.initGui();
//        this.guiLeft = (this.width - this.xSize) / 2;
//        this.guiTop = (this.height - this.ySize) / 2;
//    }
//
//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        this.drawDefaultBackground();
//        super.drawScreen(mouseX, mouseY, partialTicks);
//        this.renderHoveredToolTip(mouseX, mouseY);
//    }
//
//    @Override
//    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        mc.getTextureManager().bindTexture(infuser_res);
//        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
//
//        if (infuser.isBurning()) {
//            int k = this.getBurnLeftScaled(13);
//            this.drawTexturedModalRect(guiLeft + 57, guiTop + 37 + 12 - k, 176, 12 - k, 14, k + 1);
//        }
//
//        if (infuser.cookTime > 0) {
//            renderProgress();
//        }
//    }
//
//    public int getCookProgressScaled(int i) {
//        return infuser.cookTime * i / infuser.totalCookTime;
//    }
//
//    public int getBurnLeftScaled(int pixels) {
//        int i = this.infuser.itemBurnTime;
//
//        if (i == 0) {
//            i = 200;
//        }
//
//        return this.infuser.burnTime * pixels / i;
//    }
//
//    public void renderProgress() {
//        int l = this.getCookProgressScaled(24);
//        this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, l + 1, 16);
//    }
//}
