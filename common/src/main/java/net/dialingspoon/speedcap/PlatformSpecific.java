package net.dialingspoon.speedcap;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.item.CapAnimComponent;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class PlatformSpecific {
    @ExpectPlatform
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendToServer(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable,
                                    boolean jump, boolean stoponadime, boolean mineActive, boolean creative) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendAnimToServer(boolean active) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static SpeedCapItem getItem() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static MenuType<SpeedCapMenu> getMenu() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RecipeSerializer<CapRecipe> getRecipeSerializer() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<CapSettingsComponent> getDataComponent() {
        throw new AssertionError();
    }
    @ExpectPlatform
    public static DataComponentType<CapAnimComponent> getAnimComponent() {
        throw new AssertionError();
    }
}
