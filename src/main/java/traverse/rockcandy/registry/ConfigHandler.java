package traverse.rockcandy.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import traverse.rockcandy.RockCandy;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = RockCandy.MODID, bus = MOD)
public class ConfigHandler {

	public static class General {

		public final ForgeConfigSpec.ConfigValue<Integer> spawnRate;
		public final ForgeConfigSpec.ConfigValue<Integer> veinOreSize;
		public final ForgeConfigSpec.ConfigValue<Integer> maxYLevel;
		public final ForgeConfigSpec.ConfigValue<Integer> minYLevel;
		public final ForgeConfigSpec.ConfigValue<Integer> changeTime;

		General(ForgeConfigSpec.Builder builder) {
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
			changeTime = builder
					.comment("Time to change Harden Candy to Blank Candy in seconds.")
					.define("changeTime", 2);
			builder.pop();
		}
	}

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General general = new General(BUILDER);
	public static final ForgeConfigSpec configSpec = BUILDER.build();
}
