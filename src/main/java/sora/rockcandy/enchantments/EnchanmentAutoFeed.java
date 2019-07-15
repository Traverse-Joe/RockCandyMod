package sora.rockcandy.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import sora.rockcandy.items.BaseUsableGem;

public class EnchanmentAutoFeed extends Enchantment {

    public EnchanmentAutoFeed() {
        super(Rarity.COMMON, EnchantmentTypes.AUTOFEED, EquipmentSlotType.values());
        this.setRegistryName("auto_feed");
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof BookItem || stack.getItem() instanceof BaseUsableGem;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof BaseUsableGem;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 15;
    }


    @Override
    public int getMaxLevel() {
        return this.getMinLevel()+15;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}

