package kiba.rockcandy.registry;

import net.minecraftforge.fml.common.Loader;

public class ModIntegration {
    public static  enum Mods{
        JEI("justenoughitems");

        String modid;

        Mods(String modid){
            this.modid = modid;
        }

        public boolean isLoaded(){
           return Loader.isModLoaded(modid);
        }
    }
}
