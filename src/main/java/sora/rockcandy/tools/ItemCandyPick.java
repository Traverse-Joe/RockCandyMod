package sora.rockcandy.tools;

import sora.rockcandy.registry.Materials;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCandyPick extends BaseItemPick {
    public ItemCandyPick() {
        super("candy_cane_pickaxe", Materials.CANDY);
    }
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

        ItemStack itemstack = player.getHeldItem(hand);
        if (!world.isRemote && player.isSneaking()) {
            if (itemstack.getItemDamage() != itemstack.getMaxDamage()) {
                player.setActiveHand(hand);
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
    }


    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2*30 *20,1));
            worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            stack.damageItem(25, player);
        }

        return stack;
    }
@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(!GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.YELLOW + "Press Shift for more Info");
        }else{
            tooltip.add(TextFormatting.BLUE + "Shift-Right Click: " + TextFormatting.RED + "Haste Buff");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
