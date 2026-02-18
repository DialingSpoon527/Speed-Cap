package net.dialingspoon.speedcap.fabric.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record CapAnimPacket(boolean active) implements CustomPacketPayload {

    public static final Type<CapAnimPacket> TYPE = new Type<>(ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_anim"));

    public static final StreamCodec<ByteBuf, CapAnimPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            CapAnimPacket::active,
            CapAnimPacket::new);

    public static void handle(CapAnimPacket packet, ServerPlayNetworking.Context context) {
        Player player = context.player();
        PlatformSpecific.setSpeeding(player, packet.active());
    }

    @Override
    public Type<CapAnimPacket> type() {
        return TYPE;
    }
}

