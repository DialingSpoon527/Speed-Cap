package net.dialingspoon.speedcap.fabric.registry;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class ModAttachments {
    public static final AttachmentType<Boolean> SPEEDING =
            AttachmentRegistry.create(
                    Identifier.fromNamespaceAndPath("speedcap", "speeding"),
                    builder -> builder
                            .initializer(() -> false)
                            .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
            );
}
