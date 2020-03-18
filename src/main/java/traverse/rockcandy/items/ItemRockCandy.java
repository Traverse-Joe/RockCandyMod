package traverse.rockcandy.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import traverse.rockcandy.registry.ConfigHandler;

public class ItemRockCandy extends BaseFood {

    public static int hunger = ConfigHandler.general.foodLevel.get();
    public static float saturation = ConfigHandler.general.satLevel.get();

    public ItemRockCandy() {
        super("rock_candy",hunger,saturation);

    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return super.onItemUse(context);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(entityLiving instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entityLiving;
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.STICK));
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }
}
