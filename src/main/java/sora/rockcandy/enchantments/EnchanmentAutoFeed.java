//package sora.rockcandy.enchantments;
//
//import sora.rockcandy.items.BaseUsableGem;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import net.minecraft.inventory.EquipmentSlotType;
//import net.minecraft.item.BookItem;
//import net.minecraft.item.ItemStack;
//
//public class EnchanmentAutoFeed extends Enchantment {
//
//    public EnchanmentAutoFeed() {
//        super(Rarity.COMMON, EnchantmentTypes.AUTOFEED, EquipmentSlotType.values());
//        this.setRegistryName("auto_feed");
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack) {
//        if(stack.getItem() instanceof BookItem){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean canApply(ItemStack stack) {
//        if(stack.getItem() instanceof BaseUsableGem){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public int getMinEnchantability(int enchantmentLevel) {
//        return 15;
//    }
//
//
//    @Override
//    public int getMaxLevel() {
//        return this.getMinLevel()+15;
//    }
//
//    @Override
//    public boolean isAllowedOnBooks() {
//        return true;
//    }
//}
//
