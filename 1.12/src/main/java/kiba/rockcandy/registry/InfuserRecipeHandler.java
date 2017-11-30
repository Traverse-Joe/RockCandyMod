package kiba.rockcandy.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class InfuserRecipeHandler {
    public static final InfuserRecipeHandler INFUSER_RECIPES = new InfuserRecipeHandler();

    public final Map<ItemStack, ItemStack> infuserList = Maps.<ItemStack, ItemStack>newHashMap();

    public static InfuserRecipeHandler instance() {
        return INFUSER_RECIPES;
    }

    public InfuserRecipeHandler() {
        this.addInfuserForItems(Items.DIAMOND, new ItemStack(ModItems.itemCandyCore));
        this.addInfuserForItems(Items.MAGMA_CREAM , new ItemStack(ModItems.itemRedHotCandy));
        this.addInfuserForItems(Items.PRISMARINE_SHARD, new ItemStack(ModItems.itemDeepBlueCandy));
        this.addInfuserForItems(Items.GOLDEN_CARROT , new ItemStack(ModItems.itemGlowCandy));
        this.addInfuserForItems(Items.GHAST_TEAR, new ItemStack(ModItems.itemClearCandy));
        this.addInfuserForItems(Items.SHULKER_SHELL , new ItemStack(ModItems.itemFloatCandy));

    }


    public void addInfuserRecipeForBlock(Block input, ItemStack stack) {
        this.addInfuserForItems(Item.getItemFromBlock(input), stack);
    }

    public void addInfuserForItems(Item input, ItemStack stack) {
        this.addInfuserRecipe(new ItemStack(input, 1, 32767), stack);
    }


    public void addInfuserRecipe(ItemStack input, ItemStack stack) {
        if (getInfuserResult(input) != ItemStack.EMPTY) {
            net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack);
            return;
        }
        this.infuserList.put(input, stack);

    }


    public ItemStack getInfuserResult(ItemStack stack) {
        for (Map.Entry<ItemStack, ItemStack> entry : this.infuserList.entrySet()) {
            if (this.compareItemStacks(stack, entry.getKey())) {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }


    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList() {
        return this.infuserList;
    }


}
