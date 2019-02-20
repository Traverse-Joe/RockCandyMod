package sora.rockcandy.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipOptions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import sora.rockcandy.init.ModItems;

import java.util.List;

public class ItemCandyDispenser extends BaseUsableGem {
    public ItemCandyDispenser() {
        super(50);

    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getMaxUseTime(ItemStack itemStack_1) {
        return 4;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        TypedActionResult<ItemStack> success = super.use(world, player, hand);
        ItemStack itemstack = player.getStackInHand(hand);
        if ((itemstack.getDamage() != itemstack.getDurability()-1) && (success.getResult() == ActionResult.PASS) && player.getHungerManager().isNotFull()) {
            player.setCurrentHand(hand);
            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemstack);
        }
        return new TypedActionResult<ItemStack>(ActionResult.FAIL, itemstack);
    }

    @Override
    public ItemStack onItemFinishedUsing(ItemStack stack, World world, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            player.getHungerManager().add(1, 0.5F);
            world.playSound(null, player.x, player.y, player.z, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYER, 0.5F, world.getRandom().nextFloat() * 0.1F + 0.9F);
            stack.applyDamage(1, player);
        }
        return stack;
    }

    @Override
    public void onEntityTick(ItemStack itemstack, World world, Entity entity, int int_1, boolean boolean_1) {
        if (world.isClient()) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        for (int i = 0; player.inventory.getInvSize() > i; ++i) {
            ItemStack stack = player.inventory.getInvStack(i);
            if (stack.getItem() != this) continue;
            if (stack.getItem() instanceof BaseUsableGem && isActive(stack)) {
                absorbCandy(stack, player.inventory);
            }
        }
    }
/*
    @EventSubscriber
    public void onLivingUpdateEvent(PlayerTickEvent event) {
        if (!event.getPlayer().world.isClient()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        for (int i = 0; player.inventory.getInvSize() > i; ++i) {
            ItemStack stack = player.inventory.getInvStack(i);
            if (stack.getItem() != this) continue;
            if (stack.getItem() instanceof BaseUsableGem && isActive(stack)) {
                absorbCandy(stack, player.inventory);
            }
        }
    }
    */

    private void absorbCandy(ItemStack rockStack, PlayerInventory inventory) {
        int damage = rockStack.getDamage();
        if (damage != 0) {
            for (int i = 0; inventory.getInvSize() > i; ++i) {
                ItemStack stack = inventory.getInvStack(i);
                if (stack.getItem() == ModItems.RAW_ROCK_CANDY) {
                    ItemStack candyStack = inventory.takeInvStack(i, 1);
                    rockStack.setDamage(damage - candyStack.getAmount());
                    return;
                }
                    else if(stack.getItem() == ModItems.HARDENED_CANDY){
                    ItemStack candyStack = inventory.takeInvStack(i, 1);
                    rockStack.setDamage(damage - (candyStack.getAmount())-3);

                }
            }
        }
    }
    @Override
    @Environment(EnvType.CLIENT)
    public void buildTooltip(ItemStack stack, World world, List<TextComponent> list, TooltipOptions options) {
        list.add(new StringTextComponent(stack.getDurability() - stack.getDamage() + "/" + stack.getDurability() +  " Charges"));
        super.buildTooltip(stack, world, list, options);
    }
}
