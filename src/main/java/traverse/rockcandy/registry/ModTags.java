package traverse.rockcandy.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import traverse.rockcandy.RockCandy;

public class ModTags {
	public static final TagKey<Block> NEEDS_CANDY_TOOL = BlockTags.create(new ResourceLocation(RockCandy.MODID, "needs_candy_tool"));
}
