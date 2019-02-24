package sora.rockcandy.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class ItemCandyGem extends BaseUsableGem {
    public ItemCandyGem() {
        super(10000);

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
        ItemStack itemstack = player.getEquippedStack(EquipmentSlot.HAND_MAIN);
        if (itemstack.getDamage() != itemstack.getDurability()-1 && success.getResult() == ActionResult.PASS && player.getHungerManager().isNotFull()) {
            player.setCurrentHand(hand);
            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemstack);
        }
        return new TypedActionResult<ItemStack>(ActionResult.FAIL, itemstack);
    }

    @Override
    public ItemStack onItemFinishedUsing(ItemStack stack, World world, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            player.getHungerManager().add(2, 1.0F);
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
                absorbSugar(stack, player.inventory);
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
                absorbSugar(stack, player.inventory);
            }
        }
    }
    */


    private void absorbSugar(ItemStack rockGem, PlayerInventory inventory) {
        int damage = rockGem.getDamage();
        if (damage != 0) {
            for (int i = 0; inventory.getInvSize() > i; ++i) {
                ItemStack stack = inventory.getInvStack(i);
                if (stack.getItem() == Items.SUGAR) {
                    ItemStack sugarStack = inventory.takeInvStack(i, 1);
                    inventory.getInvStack(i).subtractAmount(1);
                    rockGem.setDamage(damage - sugarStack.getAmount());
                    return;
                }
            }
        }
    }


    @Override
    public boolean canDamage() {
        if (getDurability() > 1) {
            return true;
        }
        return false;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void buildTooltip(ItemStack stack, World world, List<TextComponent> list, TooltipContext options) {
        list.add(new StringTextComponent(stack.getDurability() - stack.getDamage() + "/" + stack.getDurability() + " Charges"));
        super.buildTooltip(stack, world, list, options);
    }
}

