package sora.rockcandy.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemHardenRockCandy extends BaseFood {
    public ItemHardenRockCandy() {
        super("harden_rock_candy", 4, 1);

    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 16;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.canEat(false)) {
            playerIn.setActiveHand(handIn);
            playerIn.attackEntityFrom(DamageSource.GENERIC, 2);
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }else{
            return new ActionResult<>(ActionResultType.FAIL, stack);
        }
    }
}

