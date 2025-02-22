package traverse.rockcandy.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.datagen.data.ModLootProvider;
import traverse.rockcandy.datagen.data.ModRecipeProvider;
import traverse.rockcandy.registry.worldgen.WorldGenRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(true, new ModLootProvider(output));
			generator.addProvider(true, new ModRecipeProvider(output));

			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					output, CompletableFuture.supplyAsync(ModDatagen::getProvider), Set.of(RockCandy.MODID)));
		}
		if (event.includeClient()) {

		}
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.CONFIGURED_FEATURE, WorldGenRegistry::configuredBootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, WorldGenRegistry::placedBootstrap);
		registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, WorldGenRegistry::modifierBootstrap);
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, context -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}
}
