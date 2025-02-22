package traverse.rockcandy.registry;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigHandler {
	public static class General {

		public final ModConfigSpec.ConfigValue<Integer> changeTime;

		General(ModConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("general");
			changeTime = builder
					.comment("Time to change Harden Candy to Blank Candy in seconds.")
					.define("changeTime", 2);
			builder.pop();
		}
	}

	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final General general = new General(BUILDER);
	public static final ModConfigSpec configSpec = BUILDER.build();
}
