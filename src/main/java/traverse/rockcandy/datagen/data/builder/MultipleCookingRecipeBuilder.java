package traverse.rockcandy.datagen.data.builder;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MultipleCookingRecipeBuilder implements RecipeBuilder {
   private final Item result;
   private final Ingredient ingredient;
   private final float experience;
   private final int cookingTime;
   private final Advancement.Builder advancement = Advancement.Builder.advancement();
   private int count;
   @Nullable
   private String group;
   private final SimpleCookingSerializer<?> serializer;

   private MultipleCookingRecipeBuilder(ItemLike result, Ingredient ingredient, float xp, int cookingTime, SimpleCookingSerializer<?> serializer) {
      this.result = result.asItem();
      this.count = 1;
      this.ingredient = ingredient;
      this.experience = xp;
      this.cookingTime = cookingTime;
      this.serializer = serializer;
   }

   public static MultipleCookingRecipeBuilder cooking(Ingredient p_126249_, ItemLike p_126250_, float p_126251_, int p_126252_, SimpleCookingSerializer<?> p_126253_) {
      return new MultipleCookingRecipeBuilder(p_126250_, p_126249_, p_126251_, p_126252_, p_126253_);
   }

   public static MultipleCookingRecipeBuilder campfireCooking(Ingredient p_176785_, ItemLike p_176786_, float p_176787_, int p_176788_) {
      return cooking(p_176785_, p_176786_, p_176787_, p_176788_, RecipeSerializer.CAMPFIRE_COOKING_RECIPE);
   }

   public static MultipleCookingRecipeBuilder blasting(Ingredient p_126268_, ItemLike p_126269_, float p_126270_, int p_126271_) {
      return cooking(p_126268_, p_126269_, p_126270_, p_126271_, RecipeSerializer.BLASTING_RECIPE);
   }

   public static MultipleCookingRecipeBuilder smelting(Ingredient p_126273_, ItemLike p_126274_, float p_126275_, int p_126276_) {
      return cooking(p_126273_, p_126274_, p_126275_, p_126276_, RecipeSerializer.SMELTING_RECIPE);
   }

   public static MultipleCookingRecipeBuilder smoking(Ingredient p_176797_, ItemLike p_176798_, float p_176799_, int p_176800_) {
      return cooking(p_176797_, p_176798_, p_176799_, p_176800_, RecipeSerializer.SMOKING_RECIPE);
   }

   public MultipleCookingRecipeBuilder withCount(int count) {
      this.count = count;
      return this;
   }

   public MultipleCookingRecipeBuilder unlockedBy(String p_126255_, CriterionTriggerInstance p_126256_) {
      this.advancement.addCriterion(p_126255_, p_126256_);
      return this;
   }

   public MultipleCookingRecipeBuilder group(@Nullable String p_176795_) {
      this.group = p_176795_;
      return this;
   }

   public Item getResult() {
      return this.result;
   }

   public void save(Consumer<FinishedRecipe> p_126263_, ResourceLocation p_126264_) {
      this.ensureValid(p_126264_);
      this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126264_)).rewards(AdvancementRewards.Builder.recipe(p_126264_)).requirements(RequirementsStrategy.OR);
      p_126263_.accept(new MultipleCookingRecipeBuilder.Result(p_126264_, this.group == null ? "" : this.group, this.ingredient, this.result, this.count, this.experience, this.cookingTime, this.advancement, new ResourceLocation(p_126264_.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + p_126264_.getPath()), this.serializer));
   }

   private void ensureValid(ResourceLocation p_126266_) {
      if (this.advancement.getCriteria().isEmpty()) {
         throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
      }
   }

   public static class Result implements FinishedRecipe {
      private final ResourceLocation id;
      private final String group;
      private final Ingredient ingredient;
      private final Item result;
      private final int count;
      private final float experience;
      private final int cookingTime;
      private final Advancement.Builder advancement;
      private final ResourceLocation advancementId;
      private final RecipeSerializer<? extends AbstractCookingRecipe> serializer;

      public Result(ResourceLocation p_126287_, String p_126288_, Ingredient p_126289_, Item result, int count, float p_126291_, int p_126292_, Advancement.Builder p_126293_, ResourceLocation p_126294_, RecipeSerializer<? extends AbstractCookingRecipe> p_126295_) {
         this.id = p_126287_;
         this.group = p_126288_;
         this.ingredient = p_126289_;
         this.result = result;
         this.count = count;
         this.experience = p_126291_;
         this.cookingTime = p_126292_;
         this.advancement = p_126293_;
         this.advancementId = p_126294_;
         this.serializer = p_126295_;
      }

      public void serializeRecipeData(JsonObject jsonObject) {
         if (!this.group.isEmpty()) {
            jsonObject.addProperty("group", this.group);
         }

         jsonObject.add("ingredient", this.ingredient.toJson());

         JsonObject resultObject = new JsonObject();
         resultObject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
         if (this.count > 1) {
            resultObject.addProperty("count", this.count);
         }
         jsonObject.add("result", resultObject);

         jsonObject.addProperty("experience", this.experience);
         jsonObject.addProperty("cookingtime", this.cookingTime);
      }

      public RecipeSerializer<?> getType() {
         return this.serializer;
      }

      public ResourceLocation getId() {
         return this.id;
      }

      @Nullable
      public JsonObject serializeAdvancement() {
         return this.advancement.serializeToJson();
      }

      @Nullable
      public ResourceLocation getAdvancementId() {
         return this.advancementId;
      }
   }
}