package net.dialingspoon.speedcap.neoforge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.CapAnimComponent;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SpeedCap.MOD_ID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CapSettingsComponent>> SPEEDCAP_DATA = register("speed_cap", builder -> builder.persistent(CapSettingsComponent.CODEC).networkSynchronized(CapSettingsComponent.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CapAnimComponent>> SPEEDCAP_ANIM = register("speed_cap_anim", builder -> builder.persistent(CapAnimComponent.CODEC).networkSynchronized(CapAnimComponent.STREAM_CODEC));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
