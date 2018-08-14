package kiba.rockcandy.enchantments;

import kiba.rockcandy.items.BaseUsableGem;
import kiba.rockcandy.items.ItemCandyDispenser;
import kiba.rockcandy.items.ItemCandyGem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;

public class EnchanmentAutoFeed extends Enchantment {

    public EnchanmentAutoFeed() {
        super(Rarity.COMMON, EnchantmentTypes.AUTOFEED, EntityEquipmentSlot.values());
        this.setRegistryName("auto_feed");
        this.setName("auto_feed");
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if(stack.getItem() instanceof ItemBook ){
            return true;
        }
        return false;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        if(stack.getItem() instanceof BaseUsableGem){
            return true;
        }
        return false;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 15;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel)+15;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}

