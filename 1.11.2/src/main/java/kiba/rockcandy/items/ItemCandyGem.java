package kiba.rockcandy.items;

import kiba.rockcandy.registry.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

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
        return 8;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer player, EnumHand hand) {
        ActionResult Success = super.onItemRightClick(itemstack, world, player, hand);


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
            player.getFoodStats().addStats(1, 0.5f);
            worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            stack.damageItem(1, player);
        }

        return stack;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
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
            if (stack.hasEffect()) {
                absorbSugar(stack, playerInventory);
            }
        }
    }

    private void absorbSugar(ItemStack rockGem, IItemHandler inventory) {
        int damage = rockGem.getItemDamage();
        for (int i = 0; inventory.getSlots() > i; ++i) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() == Items.SUGAR) {
                ItemStack sugarStack = inventory.extractItem(i, 1, false);
                this.setDamage(rockGem, damage - sugarStack.stackSize);
                return;

            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(stack.getMaxDamage() - stack.getItemDamage() + "/" + stack.getMaxDamage() + " Charges");
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
