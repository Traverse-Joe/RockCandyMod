package sora.rockcandy.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemRockCandy extends BaseFoodItem {
    public ItemRockCandy() {
        super(6, 0.6F);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        return super.use(world_1, playerEntity_1, hand_1);
    }

    @Override
    public ItemStack onItemFinishedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(entityLiving instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entityLiving;
            player.inventory.insertStack(new ItemStack(Items.STICK));
        }
        return super.onItemFinishedUsing(stack, worldIn, entityLiving);
    }
}
