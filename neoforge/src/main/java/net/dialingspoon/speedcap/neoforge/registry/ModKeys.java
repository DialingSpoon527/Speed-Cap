package net.dialingspoon.speedcap.neoforge.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    public static final KeyMapping.Category KEY_CATEGORY_SPEEDCAP =
            new KeyMapping.Category(ResourceLocation.fromNamespaceAndPath(SpeedCap.MOD_ID, "main"));
    public static KeyMapping TOGGLE_SPEED = new KeyMapping("key.speedcap.speed", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
    public static KeyMapping TOGGLE_MINE = new KeyMapping("key.speedcap.mine", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_SPEEDCAP);
}
