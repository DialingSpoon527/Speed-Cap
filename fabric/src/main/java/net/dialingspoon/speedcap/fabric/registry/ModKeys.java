package net.dialingspoon.speedcap.fabric.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.fabric.networking.CapKeybindPacket;
import net.dialingspoon.speedcap.fabric.networking.Packets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    public static final KeyMapping.Category KEY_CATEGORY_SPEEDCAP = KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath(SpeedCap.MOD_ID,"main"));
    public static KeyMapping TOGGLE_SPEED = new KeyMapping("key.speedcap.speed", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
    public static KeyMapping TOGGLE_MINE = new KeyMapping("key.speedcap.mine", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY_SPEEDCAP);

    public static void register() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_SPEED);
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            while (ModKeys.TOGGLE_SPEED.consumeClick()) {
                ItemStack cap = Util.getActiveCap(minecraft.player);
                if (!cap.isEmpty()) {
                    Packets.sendToServer(new CapKeybindPacket(true));
                }
            }
        });
        KeyBindingHelper.registerKeyBinding(TOGGLE_MINE);
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            while (ModKeys.TOGGLE_MINE.consumeClick()) {
                ItemStack cap = Util.getActiveCap(minecraft.player);
                if (!cap.isEmpty()) {
                    Packets.sendToServer(new CapKeybindPacket(false));
                }
            }
        });
    }
}
