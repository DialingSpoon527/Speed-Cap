package net.dialingspoon.speedcap.neoforge.registry;

import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, "speedcap");

    public static final Supplier<AttachmentType<Boolean>> SPEEDING =
            ATTACHMENT_TYPES.register("speeding",
                    () -> AttachmentType.builder(() -> false)
                            .sync(ByteBufCodecs.BOOL)
                            .build()
            );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
