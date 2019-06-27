//package sora.rockcandy.enchantments;
//
//import sora.rockcandy.RockCandy;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.enchantment.EnumEnchantmentType;
//import net.minecraft.item.BookItem;
//import net.minecraftforge.common.util.EnumHelper;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//@Mod.EventBusSubscriber(modid = RockCandy.MODID)
//public class EnchantmentTypes {
//    public static final EnchantmentTypes AUTOFEED = EnchantmentHelper.setEnchantments("AUTOFEED",(item) -> item instanceof BookItem);
//    public static EnchanmentAutoFeed enchanmentAutoFeed = new EnchanmentAutoFeed();
//
//    @SubscribeEvent
//    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event){
//        event.getRegistry().register(enchanmentAutoFeed);
//
//    }
//}
