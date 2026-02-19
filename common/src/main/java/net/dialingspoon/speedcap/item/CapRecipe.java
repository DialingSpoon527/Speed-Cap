package net.dialingspoon.speedcap.item;

import com.mojang.serialization.MapCodec;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WoolCarpetBlock;

public class CapRecipe extends CustomRecipe {
    public static final MapCodec<CapRecipe> MAP_CODEC = MapCodec.unit(new CapRecipe());
    public static final StreamCodec<RegistryFriendlyByteBuf, CapRecipe> STREAM_CODEC = StreamCodec.unit(new CapRecipe());
    public static final RecipeSerializer<CapRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private static boolean matchesSlot(ItemStack stack, int x, int y) {
        return switch (x + y * 3) {
            case 0, 2 -> stack.is(Items.STICK);
            case 1 -> isColoredCarpet(stack);
            case 3, 5 -> stack.is(Items.REDSTONE);
            case 4 -> stack.is(Items.IRON_HELMET);
            default -> stack.isEmpty();
        };
    }

    @Override
    public boolean matches(CraftingInput recipeInput, Level level) {
        for (int i = 0; i <= recipeInput.width() - 3; ++i) {
            for (int j = 0; j <= recipeInput.height() - 2; ++j) {
                if (this.matches(recipeInput, i, j, true)) {
                    return true;
                }
                if (!this.matches(recipeInput, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean matches(CraftingInput recipeInput, int m, int n, boolean bl) {
        for (int i = 0; i < recipeInput.width(); ++i) {
            for (int j = 0; j < recipeInput.height(); ++j) {
                int k = i - m;
                int l = j - n;
                ItemStack stack = recipeInput.getItem(i + j * recipeInput.width());
                if (k < 0 || l < 0 || k >= 3 || l >= 2) {
                    if (!stack.isEmpty()) {
                        return false;
                    }
                    continue;
                }

                int recipeX = bl ? 2 - k : k;
                if (!matchesSlot(stack, recipeX, l)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack carpet = input.items().stream().filter(CapRecipe::isColoredCarpet).findFirst().orElse(ItemStack.EMPTY);
        if (carpet.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack cap = new ItemStack(PlatformSpecific.getItem());

        int color = ((WoolCarpetBlock)((BlockItem)carpet.getItem()).getBlock()).getColor().getTextureDiffuseColor();

        cap.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
        return cap;
    }

    @Override
    public RecipeSerializer<CapRecipe> getSerializer() {
        return SERIALIZER;
    }

    private static boolean isColoredCarpet(ItemStack stack) {
        return stack.is(ItemTags.WOOL_CARPETS) && !stack.is(Items.WHITE_CARPET);
    }
}
