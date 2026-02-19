package net.dialingspoon.speedcap.fabric.networking;

import io.netty.buffer.ByteBuf;
import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.fabric.registry.ModDataComponents;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record CapKeybindPacket(boolean move) implements CustomPacketPayload {

    public static final Type<CapKeybindPacket> TYPE = new Type<>(Identifier.tryBuild(SpeedCap.MOD_ID, "cap_key"));

    public static final StreamCodec<ByteBuf, CapKeybindPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            CapKeybindPacket::move,
            CapKeybindPacket::new);

    public static void handle(CapKeybindPacket packet, ServerPlayNetworking.Context context) {
        Player player = context.player();
        ItemStack cap = Util.getActiveCap(player);
        CapSettingsComponent old = cap.get(ModDataComponents.SPEEDCAP_DATA);
        cap.set(ModDataComponents.SPEEDCAP_DATA, new CapSettingsComponent(old.moveSpeed(), old.mineSpeed(), old.moveActive() != packet.move(), old.modifiable(), old.jump(), old.stoponadime(), old.mineActive() == packet.move(), old.creative()));
        ((EntityInterface)player).speedcap$setData(cap.get(ModDataComponents.SPEEDCAP_DATA));
    }

    @Override
    public Type<CapKeybindPacket> type() {
        return TYPE;
    }
}

