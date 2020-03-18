package traverse.rockcandy.items;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import traverse.rockcandy.registry.ConfigHandler;
import traverse.rockcandy.registry.ModItems;

import java.util.List;


public class ItemHardenRockCandy extends BaseFood {

    public static final int CHANGE_TIME = ConfigHandler.general.changeTime.get() * 20;
    public ItemHardenRockCandy() {
        super("harden_rock_candy", 6, 0.6F);

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

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        World world = entity.getEntityWorld();
        if(!world.isRemote() && entity.isInWater()){
            List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class,entity.getBoundingBox().expand(1,1,1));

            for(ItemEntity item: items){
                if(item.getItem().isItemEqual(new ItemStack(ModItems.HARDEN_CANDY))){
                    int  count = entity.getItem().getCount();
                    if(entity.getAge() >= CHANGE_TIME){
                        entity.remove();
                        item.remove();
                        world.playSound(entity.getPosition().getX(),entity.getPosition().getY(), entity.getPosition().getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS,1.0F,1.0F,false);
                        world.addEntity(new ItemEntity(world, entity.getPosition().getX(),entity.getPosition().getY(), entity.getPosition().getZ(), new ItemStack(ModItems.BLANK_CANDY,count)));
                    }
                }
            }
        }
        return  false;
    }
}

