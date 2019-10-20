package sora.rockcandy.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import sora.rockcandy.RockCandy;
import sora.rockcandy.network.PacketAutoFeed;
import sora.rockcandy.network.RockCandyPacketHandler;
import sora.rockcandy.proxy.ClientProxy;
import sora.rockcandy.registry.ModItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;

@EventBusSubscriber(bus = FORGE, modid = RockCandy.MODID)
public class ItemCandyGem extends BaseUsableGem {
  public ItemCandyGem() {
    super("candy_gem", 1000);
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.EAT;
  }

  @Override
  public int getUseDuration(ItemStack stack) {
    return 4;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack stack = playerIn.getHeldItem(handIn);
    if (stack.getDamage() != stack.getMaxDamage() - 1 && playerIn.getFoodStats().needFood()) {
      playerIn.setActiveHand(handIn);
    }
    return super.onItemRightClick(worldIn, playerIn, handIn);
  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
    if (entityLiving instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) entityLiving;
      player.getFoodStats().addStats(5, 0.6F);
      worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
      stack.damageItem(1, player, (entity) -> {
      });
    }
    return stack;
  }

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onKeyPressed(InputEvent.KeyInputEvent event) {
    PlayerEntity player = RockCandy.getProxy().getPlayer();
    int slot = findStack(new ItemStack(ModItems.CANDY_GEM),player);
    ItemStack stack = player.inventory.getStackInSlot(slot >= 0 ? slot : 0);
    if (ClientProxy.autoFeedKey != null && ClientProxy.autoFeedKey.isPressed() && !stack.isEmpty()) {
      PacketAutoFeed.updateAutoFeed(stack,!isAutoFeeding(stack));
      RockCandyPacketHandler.INSTANCE.sendToServer(new PacketAutoFeed(!isAutoFeeding(stack), slot));
      player.sendStatusMessage(new StringTextComponent("Mode Changed"),true);
    }
  }

  public static int findStack(ItemStack stack, PlayerEntity player) {
    for(int i = 0; i < player.inventory.mainInventory.size(); ++i) {
      ItemStack itemstack = player.inventory.mainInventory.get(i);
      if (!player.inventory.mainInventory.get(i).isEmpty() && stack.isItemEqual(itemstack)) {
        return i;
      }
    }

    return -1;
  }

  @Override
  public void inventoryTick(ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    if (worldIn.isRemote()) {
      return;
    }
    PlayerEntity player = (PlayerEntity) entityIn;
    for (int i = 0; player.inventory.getSizeInventory() > i; ++i) {
      ItemStack stack = player.inventory.getStackInSlot(i);
      if (stack.getItem() != this) continue;
      if (stack.getItem() instanceof BaseUsableGem && isActive(stack)) {
        absorbSugar(stack, player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null));
      }
    }
    if (isAutoFeeding(itemStack)) {
      if (player.canEat(false)) {
        if (itemStack.isDamageable() && itemStack.getMaxDamage() - itemStack.getDamage() > 1) {
          player.getFoodStats().addStats(5, 0.6F);
          itemStack.damageItem(1, player, playerEntity -> {
          });
        }
      }
    }
  }


  private void absorbSugar(ItemStack rockGem, IItemHandler inventory) {
    int damage = rockGem.getDamage();
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

  public static boolean isAutoFeeding(@Nonnull ItemStack stack) {
    CompoundNBT compound = stack.getTag();

    if (compound == null) {
      return false;
    }
    return compound.getBoolean("autoFeed");
  }

  @Override
  public boolean isDamageable() {
    if (getDamage(new ItemStack(this)) > 1) {
      return true;
    }
    return false;
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    tooltip.add(new StringTextComponent(stack.getMaxDamage() - stack.getItem().getDamage(stack)-1 + "/" + stack.getMaxDamage() + " Charges"));
    if (isAutoFeeding(stack)) {
      tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Auto Feed: " + TextFormatting.GREEN + "Enabled"));
    } else {
      tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Auto Feed: " + TextFormatting.RED + "Disabled"));
    }
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
