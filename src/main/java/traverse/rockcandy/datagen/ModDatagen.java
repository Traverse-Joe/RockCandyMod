package traverse.rockcandy.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.datagen.data.ModBlockTagProvider;
import traverse.rockcandy.datagen.data.ModDataPackProvider;
import traverse.rockcandy.datagen.data.ModItemTagProvider;
import traverse.rockcandy.datagen.data.ModLootProvider;
import traverse.rockcandy.datagen.data.ModModelProvider;
import traverse.rockcandy.datagen.data.ModRecipeProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new ModLootProvider(output, lookupProvider));
		generator.addProvider(true, new ModRecipeProvider.Runner(output, lookupProvider));
		ModBlockTagProvider blockTags = new ModBlockTagProvider(output, lookupProvider);
		generator.addProvider(true, blockTags);
		generator.addProvider(true, new ModItemTagProvider(output, lookupProvider, blockTags.contentsGetter()));

		generator.addProvider(true, new ModDataPackProvider(
				output, lookupProvider, Set.of(RockCandy.MODID)));

		generator.addProvider(true, new ModModelProvider(output));
	}
}
