package net.dialingspoon.speedcap.fabric.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.gui.SpeedCapMenu;
import net.dialingspoon.speedcap.gui.SpeedCapScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<SpeedCapMenu> SPEEDCAP = Registry.register(BuiltInRegistries.MENU, ResourceLocation.tryBuild(SpeedCap.MOD_ID, "speed_cap"), new MenuType<>(SpeedCapMenu::new, FeatureFlags.VANILLA_SET));
    public static void register(boolean client) {
        if(client) {
            MenuScreens.register(SPEEDCAP, SpeedCapScreen::new);
        }
    }
}
