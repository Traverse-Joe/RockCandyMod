package sora.rockcandy.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.crafting.ShapedRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GemShapedRecipe extends ShapedRecipe {
    private  String group;

    public GemShapedRecipe(Identifier identifier_1, String string_1, int int_1, int int_2, DefaultedList<Ingredient> defaultedList_1, ItemStack itemStack_1) {
        super(identifier_1, string_1, int_1, int_2, defaultedList_1, itemStack_1);
        group = string_1;
    }

    @Override
    public net.minecraft.recipe.RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.RECIPE_SERIALIZER;
    }

    public static class RecipeSerializer  implements net.minecraft.recipe.RecipeSerializer <GemShapedRecipe> {

        public GemShapedRecipe read(Identifier identifier_1, JsonObject jsonObject_1) {
            String string_1 = JsonHelper.getString(jsonObject_1, "group", "");
            Map<String, Ingredient> map_1 = deserializeComponents(JsonHelper.getObject(jsonObject_1, "key"));
            String[] strings_1 = method_8146(deserializePattern(JsonHelper.getArray(jsonObject_1, "pattern")));
            int int_1 = strings_1[0].length();
            int int_2 = strings_1.length;
            DefaultedList<Ingredient> defaultedList_1 = method_8148(strings_1, map_1, int_1, int_2);
            ItemStack itemStack_1 = deserializeItemStack(JsonHelper.getObject(jsonObject_1, "result"));
            return new GemShapedRecipe(identifier_1, string_1, int_1, int_2, defaultedList_1, itemStack_1);
        }

        public  GemShapedRecipe read(Identifier identifier_1, PacketByteBuf packetByteBuf_1) {
            int int_1 = packetByteBuf_1.readVarInt();
            int int_2 = packetByteBuf_1.readVarInt();
            String string_1 = packetByteBuf_1.readString(32767);
            DefaultedList<Ingredient> defaultedList_1 = DefaultedList.create(int_1 * int_2, Ingredient.EMPTY);

            for(int int_3 = 0; int_3 < defaultedList_1.size(); ++int_3) {
                defaultedList_1.set(int_3, Ingredient.fromPacket(packetByteBuf_1));
            }

            ItemStack itemStack_1 = packetByteBuf_1.readItemStack();
            return new GemShapedRecipe(identifier_1, string_1, int_1, int_2, defaultedList_1, itemStack_1);
        }

        public void write(PacketByteBuf packetByteBuf_1, GemShapedRecipe shapedRecipe_1) {
            packetByteBuf_1.writeVarInt(shapedRecipe_1.getWidth());
            packetByteBuf_1.writeVarInt(shapedRecipe_1.getHeight());
            packetByteBuf_1.writeString(shapedRecipe_1.group);
            Iterator var3 = shapedRecipe_1.getPreviewInputs().iterator();

            while(var3.hasNext()) {
                Ingredient ingredient_1 = (Ingredient)var3.next();
                ingredient_1.write(packetByteBuf_1);
            }

            packetByteBuf_1.writeItemStack(shapedRecipe_1.getOutput());
        }

        private static Map<String, Ingredient> deserializeComponents(JsonObject jsonObject_1) {
            Map<String, Ingredient> map_1 = Maps.newHashMap();
            Iterator var2 = jsonObject_1.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, JsonElement> map$Entry_1 = (Map.Entry)var2.next();
                if (((String)map$Entry_1.getKey()).length() != 1) {
                    throw new JsonSyntaxException("Invalid key entry: '" + (String)map$Entry_1.getKey() + "' is an invalid symbol (must be 1 character only).");
                }

                if (" ".equals(map$Entry_1.getKey())) {
                    throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
                }

                map_1.put(map$Entry_1.getKey(), Ingredient.fromJson((JsonElement)map$Entry_1.getValue()));
            }

            map_1.put(" ", Ingredient.EMPTY);
            return map_1;
        }

        static String[] method_8146(String... strings_1) {
            int int_1 = 2147483647;
            int int_2 = 0;
            int int_3 = 0;
            int int_4 = 0;

            for(int int_5 = 0; int_5 < strings_1.length; ++int_5) {
                String string_1 = strings_1[int_5];
                int_1 = Math.min(int_1, method_8151(string_1));
                int int_6 = method_8153(string_1);
                int_2 = Math.max(int_2, int_6);
                if (int_6 < 0) {
                    if (int_3 == int_5) {
                        ++int_3;
                    }

                    ++int_4;
                } else {
                    int_4 = 0;
                }
            }

            if (strings_1.length == int_4) {
                return new String[0];
            } else {
                String[] strings_2 = new String[strings_1.length - int_4 - int_3];

                for(int int_7 = 0; int_7 < strings_2.length; ++int_7) {
                    strings_2[int_7] = strings_1[int_7 + int_3].substring(int_1, int_2 + 1);
                }

                return strings_2;
            }
        }
    }

    public static ItemStack deserializeItemStack(JsonObject jsonObject_1) {
        int Damage = 0;
        String string_1 = JsonHelper.getString(jsonObject_1, "item");
        Item item_1 = (Item) Registry.ITEM.getOrEmpty(new Identifier(string_1)).orElseThrow(() -> {
            return new JsonSyntaxException("Unknown item '" + string_1 + "'");
        });
        if (jsonObject_1.has("data")) {
            Damage = jsonObject_1.get("data").getAsInt();
        }

        int int_1 = JsonHelper.getInt(jsonObject_1, "count", 1);
        ItemStack stack = new ItemStack(item_1, int_1);
        stack.setDamage(Damage);
        return stack;
    }

    private static DefaultedList<Ingredient> method_8148(String[] strings_1, Map<String, Ingredient> map_1, int int_1, int int_2) {
        DefaultedList<Ingredient> defaultedList_1 = DefaultedList.create(int_1 * int_2, Ingredient.EMPTY);
        Set<String> set_1 = Sets.newHashSet(map_1.keySet());
        set_1.remove(" ");

        for(int int_3 = 0; int_3 < strings_1.length; ++int_3) {
            for(int int_4 = 0; int_4 < strings_1[int_3].length(); ++int_4) {
                String string_1 = strings_1[int_3].substring(int_4, int_4 + 1);
                Ingredient ingredient_1 = (Ingredient)map_1.get(string_1);
                if (ingredient_1 == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string_1 + "' but it's not defined in the key");
                }

                set_1.remove(string_1);
                defaultedList_1.set(int_4 + int_1 * int_3, ingredient_1);
            }
        }

        if (!set_1.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set_1);
        } else {
            return defaultedList_1;
        }
    }

    static String[] method_8146(String... strings_1) {
        int int_1 = 2147483647;
        int int_2 = 0;
        int int_3 = 0;
        int int_4 = 0;

        for(int int_5 = 0; int_5 < strings_1.length; ++int_5) {
            String string_1 = strings_1[int_5];
            int_1 = Math.min(int_1, method_8151(string_1));
            int int_6 = method_8153(string_1);
            int_2 = Math.max(int_2, int_6);
            if (int_6 < 0) {
                if (int_3 == int_5) {
                    ++int_3;
                }

                ++int_4;
            } else {
                int_4 = 0;
            }
        }

        if (strings_1.length == int_4) {
            return new String[0];
        } else {
            String[] strings_2 = new String[strings_1.length - int_4 - int_3];

            for(int int_7 = 0; int_7 < strings_2.length; ++int_7) {
                strings_2[int_7] = strings_1[int_7 + int_3].substring(int_1, int_2 + 1);
            }

            return strings_2;
        }
    }

    private static int method_8151(String string_1) {
        int int_1;
        for(int_1 = 0; int_1 < string_1.length() && string_1.charAt(int_1) == ' '; ++int_1) {
        }

        return int_1;
    }

    private static int method_8153(String string_1) {
        int int_1;
        for(int_1 = string_1.length() - 1; int_1 >= 0 && string_1.charAt(int_1) == ' '; --int_1) {
        }

        return int_1;
    }

    private static String[] deserializePattern(JsonArray jsonArray_1) {
        String[] strings_1 = new String[jsonArray_1.size()];
        if (strings_1.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        } else if (strings_1.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for(int int_1 = 0; int_1 < strings_1.length; ++int_1) {
                String string_1 = JsonHelper.asString(jsonArray_1.get(int_1), "pattern[" + int_1 + "]");
                if (string_1.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
                }

                if (int_1 > 0 && strings_1[0].length() != string_1.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                strings_1[int_1] = string_1;
            }

            return strings_1;
        }
    }

    private static Map<String, Ingredient> deserializeComponents(JsonObject jsonObject_1) {
        Map<String, Ingredient> map_1 = Maps.newHashMap();
        Iterator var2 = jsonObject_1.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, JsonElement> map$Entry_1 = (Map.Entry)var2.next();
            if (((String)map$Entry_1.getKey()).length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String)map$Entry_1.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(map$Entry_1.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map_1.put(map$Entry_1.getKey(), Ingredient.fromJson((JsonElement)map$Entry_1.getValue()));
        }

        map_1.put(" ", Ingredient.EMPTY);
        return map_1;
    }

}
