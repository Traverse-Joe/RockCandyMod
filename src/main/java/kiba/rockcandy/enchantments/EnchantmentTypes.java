package kiba.rockcandy.enchantments;

import kiba.rockcandy.RockCandy;
import kiba.rockcandy.items.ItemCandyDispenser;
import kiba.rockcandy.items.ItemCandyGem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = RockCandy.MODID)
public class EnchantmentTypes {
    public static final EnumEnchantmentType AUTOFEED = EnumHelper.addEnchantmentType("AUTOFEED",(item) -> item instanceof ItemCandyGem || item instanceof ItemCandyDispenser);
    public static EnchanmentAutoFeed enchanmentAutoFeed = new EnchanmentAutoFeed();

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event){
        event.getRegistry().register(enchanmentAutoFeed);

    }
}
