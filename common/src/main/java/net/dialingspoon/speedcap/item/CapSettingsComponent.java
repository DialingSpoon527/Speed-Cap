package net.dialingspoon.speedcap.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CapSettingsComponent(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable, boolean jump, boolean stoponadime, boolean mineActive, boolean creative) {
    public static final Codec<CapSettingsComponent> CODEC;
    public static final StreamCodec<ByteBuf, CapSettingsComponent> STREAM_CODEC;
    static {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.FLOAT.fieldOf("moveSpeed").forGetter(component -> component.moveSpeed),
                Codec.FLOAT.fieldOf("mineSpeed").forGetter(component -> component.mineSpeed),
                Codec.BOOL.fieldOf("moveActive").forGetter(component -> component.moveActive),
                Codec.BOOL.fieldOf("modifiable").forGetter(component -> component.modifiable),
                Codec.BOOL.fieldOf("jump").forGetter(component -> component.jump),
                Codec.BOOL.fieldOf("stoponadime").forGetter(component -> component.stoponadime),
                Codec.BOOL.fieldOf("mineActive").forGetter(component -> component.mineActive),
                Codec.BOOL.fieldOf("creative").forGetter(component -> component.creative)
        ).apply(instance, CapSettingsComponent::new));
        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.FLOAT,
                CapSettingsComponent::moveSpeed,
                ByteBufCodecs.FLOAT,
                CapSettingsComponent::mineSpeed,
                ByteBufCodecs.BYTE,
                CapSettingsComponent::packBooleans,
                (movespeed, minespeed, bytes) -> {
                    boolean[] bools = unpackBooleans(bytes);
                    return new CapSettingsComponent(movespeed, minespeed, bools[0], bools[1], bools[2], bools[3], bools[4], bools[5]);
                });
    }

    public CapSettingsComponent(float moveSpeed, float mineSpeed, boolean moveActive, boolean modifiable, boolean jump, boolean stoponadime, boolean mineActive, boolean creative) {
        this.moveSpeed = Math.max(moveSpeed, 0.1f);
        this.mineSpeed = Math.max(mineSpeed, 0.1f);
        this.moveActive = moveActive;
        this.modifiable = modifiable;
        this.jump = jump;
        this.stoponadime = stoponadime;
        this.mineActive = mineActive;
        this.creative = creative;
    }

    public byte packBooleans() {
        byte packed = 0;
        if (moveActive) packed |= 1;
        if (modifiable) packed |= 1 << 1;
        if (jump) packed |= 1 << 2;
        if (stoponadime) packed |= 1 << 3;
        if (mineActive) packed |= 1 << 4;
        if (creative) packed |= 1 << 5;
        return packed;
    }

    public static boolean[] unpackBooleans(byte packed) {
        boolean moveActive = (packed & (1)) != 0;
        boolean modifiable = (packed & (1 << 1)) != 0;
        boolean jump = (packed & (1 << 2)) != 0;
        boolean stoponadime = (packed & (1 << 3)) != 0;
        boolean mineActive = (packed & (1 << 4)) != 0;
        boolean creative = (packed & (1 << 5)) != 0;
        return new boolean[]{moveActive, modifiable, jump, stoponadime, mineActive, creative};
    }
}
