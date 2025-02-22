package traverse.rockcandy.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;
import traverse.rockcandy.RockCandy;

import java.util.function.Supplier;

public class ModDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, RockCandy.MODID);

	public static final Supplier<DataComponentType<Boolean>> AUTO_FEED = DATA_COMPONENT_TYPES.register("auto_feed", () ->
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build());

	public static final Supplier<DataComponentType<Boolean>> ACTIVE = DATA_COMPONENT_TYPES.register("active", () ->
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build());
}
