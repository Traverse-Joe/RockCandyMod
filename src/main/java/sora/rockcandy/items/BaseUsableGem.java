package sora.rockcandy.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class BaseUsableGem extends BaseItem {

    public BaseUsableGem(int durability) {
        super(1,durability);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand.MAIN);
        if(!world.isClient && player.isSneaking()){
            player.world.playSound(null,player.getPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYER, 0.1F, 0.5F * ((player.world.getRandom().nextFloat() - player.world.getRandom().nextFloat()) * 0.7F + 1.2F));
            this.toggleActive(stack);
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }

    @Override
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return isActive(stack);
    }

    public boolean isActive(ItemStack stack) {
        CompoundTag compound = stack.getTag();

       if(compound == null){
           return false;
        }
        return compound.getBoolean("isActive");
    }

    private void toggleActive(ItemStack stack){
        setActive(stack , !isActive(stack));

    }

    private void setActive ( ItemStack stack , boolean bool){
        CompoundTag compound = stack.getTag();


        if(compound == null){
            compound = new CompoundTag();
            stack.setTag(compound);

        }
        compound.putBoolean("isActive", bool);
    }

/*
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.getItemDamage()>0;
    }
    */

    @Override
    @Environment(EnvType.CLIENT)
    public void buildTooltip(ItemStack stack, @Nullable World world, List<TextComponent> list, TooltipOptions options) {
        if(isActive(stack)){
            list.add(new StringTextComponent(TextFormat.BLUE + "Is Active" + "" + TextFormat.GREEN + "True"));

        }
        else {
            list.add(new StringTextComponent(TextFormat.BLUE + "Is Active" + "" + TextFormat.RED + "False"));
        }
        super.buildTooltip(stack, world, list, options);
    }

}

