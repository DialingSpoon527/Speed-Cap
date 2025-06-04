package net.dialingspoon.speedcap.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CapAnimComponent(boolean sailDirection, long sailTick) {
    public static final Codec<CapAnimComponent> CODEC;
    public static final StreamCodec<ByteBuf, CapAnimComponent> STREAM_CODEC;
    static {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.BOOL.fieldOf("sailDirection").forGetter(component -> component.sailDirection),
                Codec.LONG.fieldOf("sailTick").forGetter(component -> component.sailTick)
        ).apply(instance, CapAnimComponent::new));
        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL,
                CapAnimComponent::sailDirection,
                ByteBufCodecs.LONG,
                CapAnimComponent::sailTick,
                CapAnimComponent::new);
    }
}
