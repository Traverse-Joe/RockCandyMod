package traverse.rockcandy.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;
import traverse.rockcandy.RockCandy;
import traverse.rockcandy.registry.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
	public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, RockCandy.MODID);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider provider) {
		this.tag(ModTags.INCORRECT_FOR_CANDY).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
	}
}
