package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.fabric.networking.Packets;
import net.dialingspoon.speedcap.fabric.registry.ModDataComponents;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.dialingspoon.speedcap.fabric.registry.ModMenuTypes;
import net.dialingspoon.speedcap.fabric.registry.ModRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class SpeedCapFabric implements ModInitializer {
    public static boolean trinketsLoaded;
    @Override
    public void onInitialize() {
        ModItems.register();
        ModMenuTypes.register(false);
        ModRecipes.register();
        Packets.registerPackets();
        ModDataComponents.register();

        trinketsLoaded = FabricLoader.getInstance().isModLoaded("trinkets");
        SpeedCap.init();
    }
}
