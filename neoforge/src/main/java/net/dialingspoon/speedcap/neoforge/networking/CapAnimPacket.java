package net.dialingspoon.speedcap.neoforge.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CapAnimPacket(boolean active) implements CustomPacketPayload {

    public static final Type<CapAnimPacket> TYPE = new Type<>(ResourceLocation.tryBuild(SpeedCap.MOD_ID, "cap_anim"));

    public static final StreamCodec<ByteBuf, CapAnimPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            CapAnimPacket::active,
            CapAnimPacket::new);

    public void handle(IPayloadContext context) {
        Player player = context.player();
        PlatformSpecific.setSpeeding(player, active());
    }

    @Override
    public Type<CapAnimPacket> type() {
        return TYPE;
    }
}

