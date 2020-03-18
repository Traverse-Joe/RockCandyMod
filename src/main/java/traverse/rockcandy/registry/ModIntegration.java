package traverse.rockcandy.registry;

import net.minecraftforge.fml.ModList;

public class ModIntegration {
  public static enum Mods{
    JEI("jei");
    String modid;
    Mods(String modid){
      this.modid=modid;
    }
    public boolean isLoaded(){
      return ModList.get().isLoaded(modid);
    }
  }
}
