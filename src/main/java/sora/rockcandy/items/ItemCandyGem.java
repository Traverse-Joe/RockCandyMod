package sora.rockcandy.items;

import sora.rockcandy.enchantments.EnchantmentTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCandyGem extends BaseUsableGem {
    public ItemCandyGem() {
        super("candy_gem");
        this.setMaxDamage(1000);

    }


    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 4;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ActionResult Success = super.onItemRightClick(world, player, hand);

        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItemDamage() != itemstack.getMaxDamage() && Success.getType() == EnumActionResult.PASS && player.getFoodStats().needFood()) {
            player.setActiveHand(hand);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            player.getFoodStats().addStats(2, 1.0f);
            worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            stack.damageItem(1, player);
        }

        return stack;
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(TickEvent.PlayerTickEvent event) {
        if (!event.side.isServer())
            return;
        EntityPlayer player = event.player;
        IItemHandler playerInventory = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        for (int i = 0; playerInventory.getSlots() > i; ++i) {
            ItemStack stack = playerInventory.getStackInSlot(i);
            if (stack.getItem() != this)
                continue;

            if (stack.getItem() instanceof BaseUsableGem && isActive(stack)) {
                absorbSugar(stack, playerInventory);
            }
        }
    }

    private void absorbSugar(ItemStack rockGem, IItemHandler inventory) {
        int damage = rockGem.getItemDamage();
        if (damage != 0) {
            for (int i = 0; inventory.getSlots() > i; ++i) {
                ItemStack stack = inventory.getStackInSlot(i);

                if (stack.getItem() == Items.SUGAR) {
                    ItemStack sugarStack = inventory.extractItem(i, 1, false);
                    this.setDamage(rockGem, damage - sugarStack.getCount());

                    return;

                }
            }
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        EntityPlayer player = (EntityPlayer) entityIn;
        if (EnchantmentHelper.getEnchantmentLevel(EnchantmentTypes.enchanmentAutoFeed, stack) > 0) {
            if (player.canEat(false)) {
                if (stack.isItemStackDamageable() && stack.getMaxDamage()-stack.getItemDamage() > 0) {
                    player.getFoodStats().addStats(1, 0.1f);
                    stack.damageItem(1, player);
                }
            }
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if(EnchantmentHelper.getEnchantments(book).containsKey(EnchantmentTypes.enchanmentAutoFeed)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isDamageable() {
     if(getDamage(new ItemStack(this))>1){
         return true;
     }
     return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(stack.getMaxDamage() - stack.getItemDamage() + "/" + stack.getMaxDamage() + " Charges");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
