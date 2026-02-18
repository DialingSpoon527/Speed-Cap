package net.dialingspoon.speedcap.neoforge.networking;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler {
    public static void registerPackets(PayloadRegistrar registrar) {
        registrar.playToServer(
                ServerboundCapSettingsPacket.TYPE,
                ServerboundCapSettingsPacket.STREAM_CODEC,
                ServerboundCapSettingsPacket::handle
                );
        registrar.playToServer(
                CapKeybindPacket.TYPE,
                CapKeybindPacket.STREAM_CODEC,
                CapKeybindPacket::handle
        );
        registrar.playToServer(
                CapAnimPacket.TYPE,
                CapAnimPacket.STREAM_CODEC,
                CapAnimPacket::handle
        );
    }

    public static void sendToServer(CustomPacketPayload message) {
        ClientPacketDistributor.sendToServer(message);
    }
}
