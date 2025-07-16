package traverse.rockcandy.datagen.data;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.items.CandyGemItem;
import traverse.rockcandy.items.tools.CandyClubItem;
import traverse.rockcandy.items.tools.CandyPickaxeItem;
import traverse.rockcandy.registry.ModBlocks;
import traverse.rockcandy.registry.ModItems;

public class ModModelProvider extends ModelProvider {

	public ModModelProvider(PackOutput output) {
		super(output, RockCandy.MODID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		for (DeferredHolder<Block, ? extends Block> registryObject : ModBlocks.BLOCKS.getEntries()) {
			TextureMapping texturemapping = TextureMapping.cube(registryObject.get());
			ResourceLocation resourcelocation = ModelTemplates.CUBE_ALL.create(registryObject.get(), texturemapping, blockModels.modelOutput);

			blockModels.blockStateOutput
					.accept(
							BlockModelGenerators.createSimpleBlock(
									registryObject.get(),
									BlockModelGenerators.plainVariant(resourcelocation)
							)
					);
		}

		for (DeferredHolder<Item, ? extends Item> registryObject : ModItems.ITEMS.getEntries()) {
			if (registryObject.get() instanceof BlockItem blockItem) {
				itemModels.declareCustomModelItem(blockItem);
			} else if (registryObject.get() instanceof CandyGemItem candyGem) {
				itemModels.declareCustomModelItem(candyGem);
			} else if (registryObject.get() instanceof CandyClubItem ||
					registryObject.get() instanceof CandyPickaxeItem ||
					registryObject.is(ModItems.CANDY_ROD.getId()) ||
					registryObject.is(ModItems.HUNGER_STICK.getId())
			) {
				itemModels.generateFlatItem(registryObject.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
			} else {
				itemModels.generateFlatItem(registryObject.get(), ModelTemplates.FLAT_ITEM);
			}
		}
	}
}
