package kiba.rockcandy.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


public class BaseUsableGem extends BaseItem {
    public BaseUsableGem(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote && player.isSneaking()) {
            player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, 0.5F * ((player.world.rand.nextFloat() - player.world.rand.nextFloat()) * 0.7F + 1.2F));
            this.toggleActive(stack);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @Override
    public boolean hasEffect(@Nonnull ItemStack stack) {
        return isActive(stack);
    }

    private boolean isActive(@Nonnull ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();

       if(compound == null){
           return false;
        }
        return compound.getBoolean("isActive");
    }
    private void toggleActive(@Nonnull ItemStack stack){
        setActive(stack , !isActive(stack));

    }
    private void setActive (@Nonnull ItemStack stack , boolean bool){
        NBTTagCompound compound = stack.getTagCompound();


        if(compound == null){
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);

        }
        compound.setBoolean("isActive", bool);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(isActive(stack)) {
            tooltip.add(TextFormatting.BLUE + "Is Active: "+ TextFormatting.GREEN + "True");
        }
            else{
                tooltip.add(TextFormatting.BLUE + "Is Active: "+ TextFormatting.RED + "False");
            }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

