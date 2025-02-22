package traverse.rockcandy.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter.Collector;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import traverse.rockcandy.registry.ModBlocks;
import traverse.rockcandy.registry.ModItems;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootProvider extends LootTableProvider {
	public ModLootProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
		super(packOutput, Set.of(), List.of(
				new SubProviderEntry(CandyBlocks::new, LootContextParamSets.BLOCK)
		), lookupProvider);
	}

	private static class CandyBlocks extends BlockLootSubProvider {

		protected CandyBlocks(HolderLookup.Provider provider) {
			super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		protected void generate() {
			this.add(ModBlocks.CANDY_ORE.get(), (block) -> {
				return createCandyOreDrops(block);
			});

			this.dropSelf(ModBlocks.CANDY_BLOCK.get());
		}

		protected LootTable.Builder createCandyOreDrops(Block block) {
			HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
			return createSilkTouchDispatchTable(block, applyExplosionDecay(block,
					LootItem.lootTableItem(ModItems.RAW_CANDY.get())
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
							.apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			return (Iterable<Block>) ModBlocks.BLOCKS.getEntries().stream().map(holder -> (Block)holder.value())::iterator;
		}
	}

	@Override
	protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, Collector problemreporter$collector) {
		super.validate(writableregistry, validationcontext, problemreporter$collector);
	}
}