package traverse.rockcandy.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import traverse.rockcandy.RockCandy;

public class ModTags {
	public static final TagKey<Block> INCORRECT_FOR_CANDY = BlockTags.create(RockCandy.modLoc("needs_candy_tool"));
}
