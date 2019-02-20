package sora.rockcandy.blocks;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.loot.context.LootContext;
import sora.rockcandy.init.ModItems;

import java.util.List;
import java.util.Random;

public class BlockCandyOre extends BaseBlock {
    public BlockCandyOre() {
        super(FabricBlockSettings.of(Material.STONE).hardness(1.0F).lightLevel(6));
    }


    @Override
    public List<ItemStack> getDroppedStacks(BlockState blockState_1, LootContext.Builder lootContext$Builder_1) {
        Random random = new Random();
        return Lists.newArrayList(new ItemStack(ModItems.RAW_ROCK_CANDY, 1 + random.nextInt(8)));
    }
}
