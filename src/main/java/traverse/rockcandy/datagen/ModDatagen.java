package traverse.rockcandy.datagen;

import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.datagen.data.ModBlockTagProvider;
import traverse.rockcandy.datagen.data.ModLootProvider;
import traverse.rockcandy.datagen.data.ModRecipeProvider;
import traverse.rockcandy.registry.worldgen.WorldGenRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		if (event.includeServer()) {
			generator.addProvider(true, new ModLootProvider(output, lookupProvider));
			generator.addProvider(true, new ModRecipeProvider(output, lookupProvider));
			ModBlockTagProvider blockTags = new ModBlockTagProvider(output, lookupProvider, helper);
			generator.addProvider(event.includeServer(), blockTags);

			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					output, CompletableFuture.supplyAsync(ModDatagen::getProvider), Set.of(RockCandy.MODID)));
		}
		if (event.includeClient()) {

		}
	}

	private static RegistrySetBuilder.PatchedRegistries getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.CONFIGURED_FEATURE, WorldGenRegistry::configuredBootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, WorldGenRegistry::placedBootstrap);
		registryBuilder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WorldGenRegistry::modifierBootstrap);
		// We need the BIOME registry to be present, so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, $ -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		Cloner.Factory cloner$factory = new Cloner.Factory();
		net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions().forEach(data -> data.runWithArguments(cloner$factory::addCodec));
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup(), cloner$factory);
	}
}
