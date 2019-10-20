package sora.rockcandy.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import sora.rockcandy.RockCandy;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = RockCandy.MODID, bus = MOD)
public class ConfigHandler {

  public static  class General{

    public final ForgeConfigSpec.ConfigValue<Integer> spawnRate;
    public final ForgeConfigSpec.ConfigValue<Integer> veinOreSize;
    public final ForgeConfigSpec.ConfigValue<Integer> maxYLevel;
    public final ForgeConfigSpec.ConfigValue<Integer> minYLevel;
    public final ForgeConfigSpec.ConfigValue<Integer> foodLevel;
    public final ForgeConfigSpec.ConfigValue<Float> satLevel;

    General(ForgeConfigSpec.Builder builder){
      builder.push("General");
      spawnRate = builder
          .comment("Spawn Rate")
          .define("spawnRate", 6);
      veinOreSize = builder
          .comment("Ore's Per Vein")
          .define("veinSize", 6);
      maxYLevel = builder
          .comment("Max Y-Level Ore's Spawn")
          .define("maxYLevel", 64);
      minYLevel = builder
          .comment("Min Y-Level Ore's Spawn")
          .define("minYLevel", 0);
      foodLevel = builder
          .comment("Max Food Value for Rock Candy")
          .define("foodValue",5);
      satLevel = builder
          .comment("Max Saturation for Rock Candy")
          .define("satValue",0.6F);
      builder.pop();
    }
  }
  private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  public static final General general = new General(BUILDER);
  public static final  ForgeConfigSpec configSpec = BUILDER.build();
}
