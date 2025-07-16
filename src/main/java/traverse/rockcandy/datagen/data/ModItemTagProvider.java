package traverse.rockcandy.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModItems;
import traverse.rockcandy.registry.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
	public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
		super(output, lookupProvider, blockTags, RockCandy.MODID);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider provider) {
		this.tag(ModTags.CANDY_TOOL_MATERIALS).add(ModItems.HARDEN_CANDY.get());
	}
}
