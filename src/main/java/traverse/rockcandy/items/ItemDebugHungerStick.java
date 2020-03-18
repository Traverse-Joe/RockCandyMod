package traverse.rockcandy.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import traverse.rockcandy.RockCandyCreativeTab;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDebugHungerStick extends BaseItem {
    public ItemDebugHungerStick() {
        super("hunger_stick", new Properties().group(RockCandyCreativeTab.getInstance()));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            playerIn.getFoodStats().setFoodLevel(0);
        }
        return new ActionResult<>(ActionResultType.FAIL,playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.DARK_RED + "DEBUG ITEM -- GIVES PLAYER ZERO HUNGER"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
