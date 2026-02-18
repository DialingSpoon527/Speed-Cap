package net.dialingspoon.speedcap.fabric;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;

import net.dialingspoon.speedcap.fabric.networking.CapAnimPacket;
import net.dialingspoon.speedcap.fabric.networking.Packets;
import net.dialingspoon.speedcap.fabric.networking.ServerboundCapSettingsPacket;
import net.dialingspoon.speedcap.fabric.registry.ModDataComponents;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.dialingspoon.speedcap.fabric.registry.ModMenuTypes;
import net.dialingspoon.speedcap.fabric.registry.ModRecipes;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.item.CapAnimComponent;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.List;

public class PlatformSpecificImpl {
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity) {
        if (SpeedCapFabric.trinketsLoaded) {
            if (TrinketsApi.getTrinketComponent(livingEntity).isPresent()) {
                List<Tuple<SlotReference, ItemStack>> stacks = TrinketsApi.getTrinketComponent(livingEntity).get().getEquipped(ModItems.SPEEDCAP);
                if (!stacks.isEmpty()) return stacks.get(0).getB();
            }
        }
        return ItemStack.EMPTY;
    }

    public static void sendToServer(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable,
                                    boolean jump, boolean stoponadime, boolean mineActive, boolean creative) {
        Packets.sendToServer(new ServerboundCapSettingsPacket(moveSpeed, mineSpeed, moveActive, modifiable, jump, stoponadime, mineActive, creative));
    }

    public static void sendAnimToServer(boolean active) {
        Packets.sendToServer(new CapAnimPacket(active));
    }

    public static SpeedCapItem getItem() {
        return ModItems.SPEEDCAP;
    }

    public static MenuType<SpeedCapMenu> getMenu() {
        return ModMenuTypes.SPEEDCAP;
    }

    public static RecipeSerializer<CapRecipe> getRecipeSerializer() {
        return ModRecipes.CAP_RECIPE;
    }

    public static DataComponentType<CapSettingsComponent> getDataComponent() {
        return ModDataComponents.SPEEDCAP_DATA;
    }

    public static DataComponentType<CapAnimComponent> getAnimComponent() {
        return ModDataComponents.SPEEDCAP_ANIM;
    }
}
