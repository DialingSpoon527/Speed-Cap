package net.dialingspoon.speedcap.fabric.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class Packets {

    public static void registerPackets() {
        PayloadTypeRegistry.serverboundPlay().register(ServerboundCapSettingsPacket.TYPE, ServerboundCapSettingsPacket.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ServerboundCapSettingsPacket.TYPE, ServerboundCapSettingsPacket::handle);
        PayloadTypeRegistry.serverboundPlay().register(CapKeybindPacket.TYPE, CapKeybindPacket.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(CapKeybindPacket.TYPE, CapKeybindPacket::handle);
        PayloadTypeRegistry.serverboundPlay().register(CapAnimPacket.TYPE, CapAnimPacket.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(CapAnimPacket.TYPE, CapAnimPacket::handle);
    }

    public static void sendToServer(CustomPacketPayload packet) {
        ClientPlayNetworking.send(packet);
    }
}
