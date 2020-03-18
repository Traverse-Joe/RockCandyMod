package traverse.rockcandy.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public class Materials {

  public static final IItemTier CANDY = new ToolMaterial(3, 1041, 8.0F, 3.0F, 10, () -> Ingredient.fromItems(ModItems.HARDEN_CANDY));

  public static class ToolMaterial implements IItemTier {

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchatablility;
    private final LazyValue<Ingredient> repair;

    public ToolMaterial(int havestLevel, int maxUses, float efficiency, float attackDamage, int enchatablility, Supplier<Ingredient> supplier) {
      this.harvestLevel = havestLevel;
      this.maxUses = maxUses;
      this.efficiency = efficiency;
      this.attackDamage = attackDamage;
      this.enchatablility = enchatablility;
      this.repair = new LazyValue<Ingredient>(supplier);
    }

    @Override
    public int getMaxUses() {
      return maxUses;
    }

    @Override
    public float getEfficiency() {
      return efficiency;
    }

    @Override
    public float getAttackDamage() {
      return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
      return harvestLevel;
    }

    @Override
    public int getEnchantability() {
      return enchatablility;
    }

    @Override
    public Ingredient getRepairMaterial() {
      return repair.getValue();
    }
  }
}
