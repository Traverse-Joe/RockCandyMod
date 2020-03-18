package traverse.rockcandy;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import traverse.rockcandy.registry.ModItems;

public class RockCandyCreativeTab extends ItemGroup {

    private static final RockCandyCreativeTab INSTANCE = new RockCandyCreativeTab();

    public RockCandyCreativeTab(){
        super(RockCandy.MODID);
    }

    public static RockCandyCreativeTab getInstance(){
        return INSTANCE;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.ROCK_CANDY);
    }
}
