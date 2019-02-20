package sora.rockcandy.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipOptions;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class ItemDebugHungerStick extends BaseItem {
    public ItemDebugHungerStick() {
        super(1);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(!world.isClient()){
            player.getHungerManager().setFoodLevel(0);
        }
        return new TypedActionResult<ItemStack>(ActionResult.FAIL,player.getEquippedStack(EquipmentSlot.HAND_MAIN));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void buildTooltip(ItemStack stack, World world, List<TextComponent> list, TooltipOptions options) {
        list.add(new StringTextComponent(TextFormat.DARK_RED + "DEBUG ITEM -- GIVE PLAYER ZERO HUNGER"));
        super.buildTooltip(stack, world, list, options);
    }
}
