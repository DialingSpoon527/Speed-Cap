package net.dialingspoon.speedcap.neoforge;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.neoforge.curios.CurioRenderer;
import net.dialingspoon.speedcap.neoforge.networking.PacketHandler;
import net.dialingspoon.speedcap.neoforge.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import top.theillusivec4.curios.api.client.ICurioRenderer;

@Mod(SpeedCap.MOD_ID)
public final class SpeedCapNeoForge {
    public static boolean curiosLoaded;
    public SpeedCapNeoForge(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModCreativeTabs.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        eventBus.addListener(SpeedCapNeoForge::registerNetworkPackets);
        ModDataComponents.register(eventBus);
        ModAttachments.register(eventBus);

        curiosLoaded = ModList.get().isLoaded("curios");
        if (curiosLoaded) {
            eventBus.addListener(this::clientSetup);
        }
        SpeedCap.init();
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        ICurioRenderer.register(ModItems.SPEEDCAP.get(), CurioRenderer::new);
    }

    public static void registerNetworkPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(SpeedCap.MOD_ID);
        PacketHandler.registerPackets(registrar);
    }
}
