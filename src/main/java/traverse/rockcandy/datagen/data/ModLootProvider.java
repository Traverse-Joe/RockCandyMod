package traverse.rockcandy.datagen.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import traverse.rockcandy.registry.ModBlocks;
import traverse.rockcandy.registry.ModItems;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModLootProvider extends LootTableProvider {
	public ModLootProvider(PackOutput packOutput) {
		super(packOutput, Set.of(), List.of(
				new SubProviderEntry(CandyBlocks::new, LootContextParamSets.BLOCK)
		));
	}

	private static class CandyBlocks extends BlockLootSubProvider {

		protected CandyBlocks() {
			super(Set.of(), FeatureFlags.REGISTRY.allFlags());
		}

		@Override
		protected void generate() {
			this.add(ModBlocks.CANDY_ORE.get(), (block) -> {
				return createCandyOreDrops(block);
			});

			this.dropSelf(ModBlocks.CANDY_BLOCK.get());
		}

		protected LootTable.Builder createCandyOreDrops(Block block) {
			return createSilkTouchDispatchTable(block, applyExplosionDecay(block,
					LootItem.lootTableItem(ModItems.RAW_CANDY.get())
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
							.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			return (Iterable<Block>) ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
		}
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext validationtracker) {
		map.forEach((name, table) -> table.validate(validationtracker));
	}
}