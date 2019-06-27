package sora.rockcandy.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import sora.rockcandy.RockCandyCreativeTab;

public class BlockItemBase extends BlockItem {
  public BlockItemBase(Block blockIn) {
    super(blockIn, new Properties().group(RockCandyCreativeTab.getInstance()));
    setRegistryName(blockIn.getRegistryName());
  }
}
