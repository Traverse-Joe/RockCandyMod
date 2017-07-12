package kiba.rockcandy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHardenRockCandy extends BaseFoodItem {
    public ItemHardenRockCandy() {
        super("harden_rock_candy", 4, 0.8F, false);

    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.canEat(false ))
        {
            playerIn.setActiveHand(handIn);
            playerIn.attackEntityFrom(DamageSource.generic , 2.0f);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }
}

