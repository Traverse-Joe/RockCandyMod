package traverse.rockcandy.datagen.data;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import traverse.rockcandy.registry.worldgen.WorldGenRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, WorldGenRegistry::configuredBootstrap)
			.add(Registries.PLACED_FEATURE, WorldGenRegistry::placedBootstrap)
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WorldGenRegistry::modifierBootstrap);

	public ModDataPackProvider(PackOutput output, CompletableFuture<Provider> registries, Set<String> modIds) {
		super(output, registries, BUILDER, modIds);
	}
}
