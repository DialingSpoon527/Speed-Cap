package net.dialingspoon.speedcap.neoforge.client;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.gui.SpeedCapScreen;
import net.dialingspoon.speedcap.models.CapModel;
import net.dialingspoon.speedcap.neoforge.networking.CapKeybindPacket;
import net.dialingspoon.speedcap.neoforge.networking.PacketHandler;
import net.dialingspoon.speedcap.neoforge.registry.ModItems;
import net.dialingspoon.speedcap.neoforge.registry.ModKeys;
import net.dialingspoon.speedcap.neoforge.registry.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = SpeedCap.MOD_ID, value = Dist.CLIENT)
public class SpeedCapNeoForgeClientEvents {
    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        if (ModKeys.TOGGLE_SPEED.consumeClick()) {
            ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
            if (!cap.isEmpty()) {
                PacketHandler.sendToServer(new CapKeybindPacket(true));
            }
        }
        if (ModKeys.TOGGLE_MINE.consumeClick()) {
            ItemStack cap = Util.getActiveCap(Minecraft.getInstance().player);
            if (!cap.isEmpty()) {
                PacketHandler.sendToServer(new CapKeybindPacket(false));
            }
        }
    }

    private static final ModelLayerLocation LAYER = new ModelLayerLocation(Identifier.tryBuild(SpeedCap.MOD_ID, "speedcap"), "main");

    public static CapModel<HumanoidRenderState> capModel = null;

    @SubscribeEvent
    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LAYER, () -> CapModel.createLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION));
    }

    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event) {
        capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
    }

    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(ItemExtension.INSTANCE, ModItems.SPEEDCAP);
    }

    @SubscribeEvent
    public static void initKeys(RegisterKeyMappingsEvent event) {
        event.register(ModKeys.TOGGLE_SPEED);
        event.register(ModKeys.TOGGLE_MINE);
    }

    @SubscribeEvent
    public static void registerMenuScreen(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.SPEEDCAP.get(), SpeedCapScreen::new);
    }
}
