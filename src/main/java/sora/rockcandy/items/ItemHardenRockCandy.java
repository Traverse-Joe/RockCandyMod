package sora.rockcandy.items;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemHardenRockCandy extends BaseFoodItem {
    public ItemHardenRockCandy() {
        super(4, 0.3F);

    }

    @Override
    public int getMaxUseTime(ItemStack itemStack_1) {
        return 16;
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getStackInHand(handIn.MAIN);
        if (playerIn.canFoodHeal()) {
            playerIn.setCurrentHand(handIn);
            playerIn.damage(DamageSource.GENERIC, 2.0F);
            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStack);
        } else {
            return new TypedActionResult<ItemStack>(ActionResult.FAIL, itemStack);
        }
    }
}


