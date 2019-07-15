package sora.rockcandy.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.BookItem;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sora.rockcandy.RockCandy;
import sora.rockcandy.items.BaseUsableGem;

@Mod.EventBusSubscriber(modid = RockCandy.MODID)
public class EnchantmentTypes {
    public static final EnchantmentType AUTOFEED = EnchantmentType.create(RockCandy.MODID + "_autofeed",item -> item instanceof BaseUsableGem);
    public static EnchanmentAutoFeed enchanmentAutoFeed = new EnchanmentAutoFeed();

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event){
        event.getRegistry().register(enchanmentAutoFeed);

    }
}
