package net.dialingspoon.speedcap.neoforge;

import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.item.CapRecipe;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.dialingspoon.speedcap.neoforge.client.ItemExtension;
import net.dialingspoon.speedcap.neoforge.networking.CapAnimPacket;
import net.dialingspoon.speedcap.neoforge.networking.Packets;
import net.dialingspoon.speedcap.neoforge.networking.ServerboundCapSettingsPacket;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.dialingspoon.speedcap.neoforge.registry.ModMenuTypes;
import net.dialingspoon.speedcap.neoforge.registry.ModRecipes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class PlatformSpecificImpl {
    public static ItemStack getItemFromModdedSlots(LivingEntity livingEntity) {
        if (SpeedCapNeoForge.curiosLoaded) {
            if (CuriosApi.getCuriosInventory(livingEntity).isPresent()) {
                List<SlotResult> stacks = CuriosApi.getCuriosInventory(livingEntity).get().findCurios(ModItems.SPEEDCAP.get());
                if (!stacks.isEmpty()) return stacks.get(0).stack();
            }
        }
        return ItemStack.EMPTY;
    }

    public static Object itemExtension() {
        return new ItemExtension();
    }

    public static void sendToServer(FriendlyByteBuf buf) {
        Packets.sendToServer(new ServerboundCapSettingsPacket(buf));
    }

    public static void sendAnimToServer(boolean active) {
        Packets.sendToServer(new CapAnimPacket(active));
    }

    public static SpeedCapItem getItem() {
        return ModItems.SPEEDCAP.get();
    }

    public static MenuType<SpeedCapMenu> getMenu() {
        return ModMenuTypes.SPEEDCAP.get();
    }

    public static RecipeSerializer<CapRecipe> getRecipeSerializer() {
        return ModRecipes.CAP_RECIPE.get();
    }
}
