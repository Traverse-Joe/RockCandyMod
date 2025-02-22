package traverse.rockcandy.registry.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ConfigHandler;
import traverse.rockcandy.registry.ModBlocks;

import java.util.List;

public class WorldGenRegistry {

	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, RockCandy.MODID);
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, RockCandy.MODID);

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_CANDY = CONFIGURED_FEATURES.register("ore_candy",
			() -> new ConfiguredFeature<>(Feature.ORE,
					new OreConfiguration(OreFeatures.NATURAL_STONE, ModBlocks.CANDY_ORE.get().defaultBlockState(),
							ConfigHandler.general.veinOreSize.get())));

	public static final RegistryObject<PlacedFeature> ORE_CANDY_PLACED = register("ore_candy",
			WorldGenRegistry.ORE_CANDY.getHolder().orElseThrow(), CountPlacement.of(ConfigHandler.general.spawnRate.get()),
			HeightRangePlacement.uniform(VerticalAnchor.absolute(ConfigHandler.general.minYLevel.get()), VerticalAnchor.absolute(ConfigHandler.general.maxYLevel.get())),
			InSquarePlacement.spread(), BiomeFilter.biome());

	private static RegistryObject<PlacedFeature> register(String registryName,
														  Holder<? extends ConfiguredFeature<?, ?>> configuredHolder,
														  PlacementModifier... placementModifiers) {
		return PLACED_FEATURES.register(registryName, () -> new PlacedFeature(Holder.hackyErase(configuredHolder), List.of(placementModifiers)));
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void biomeLoadingEvent(BiomeLoadingEvent event) {
		ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		BiomeGenerationSettingsBuilder builder = event.getGeneration();
		if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD)) {
			builder.getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WorldGenRegistry.ORE_CANDY_PLACED.getHolder().orElseThrow());
		}
	}
}
