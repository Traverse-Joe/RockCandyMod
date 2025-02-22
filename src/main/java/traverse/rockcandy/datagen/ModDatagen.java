package traverse.rockcandy.datagen;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.datagen.data.ModLootProvider;
import traverse.rockcandy.datagen.data.ModRecipeProvider;
import traverse.rockcandy.registry.worldgen.WorldGenRegistry;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(true, new ModLootProvider(generator));
			generator.addProvider(true, new ModRecipeProvider(generator));

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
					generator, helper, RockCandy.MODID, ops, Registry.PLACED_FEATURE_REGISTRY,
					getPlacedFeatures(ops)
			));

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
					generator, helper, RockCandy.MODID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS,
					getBiomeModifiers(ops)
			));
		}
		if (event.includeClient()) {

		}
	}

	public static Map<ResourceLocation, PlacedFeature> getPlacedFeatures(RegistryOps<JsonElement> ops) {
		Map<ResourceLocation, PlacedFeature> map = Maps.newHashMap();

		final ResourceKey<ConfiguredFeature<?, ?>> oreCandyFeatureKey = WorldGenRegistry.ORE_CANDY.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get();
		final Holder<ConfiguredFeature<?, ?>> oreCandyHolder = ops.registry(Registry.CONFIGURED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(oreCandyFeatureKey);
		final PlacedFeature oreCandyPlacedFeature = new PlacedFeature(
				oreCandyHolder,
				List.of(CountPlacement.of(6),
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
						InSquarePlacement.spread(), BiomeFilter.biome()));
		map.put(new ResourceLocation(RockCandy.MODID, "ore_candy"), oreCandyPlacedFeature);

		return map;
	}

	public static Map<ResourceLocation, BiomeModifier> getBiomeModifiers(RegistryOps<JsonElement> ops) {
		Map<ResourceLocation, BiomeModifier> map = Maps.newHashMap();

		final HolderSet.Named<Biome> overworldTag = new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD);
		final BiomeModifier addOreCandy = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
				overworldTag,
				HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
						new ResourceLocation(RockCandy.MODID, "ore_candy")))),
				GenerationStep.Decoration.UNDERGROUND_ORES);

		map.put(new ResourceLocation(RockCandy.MODID, "add_ore_candy"), addOreCandy);

		return map;
	}
}
