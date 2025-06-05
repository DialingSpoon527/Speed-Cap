package net.dialingspoon.speedcap.neoforge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpeedCap.MOD_ID);
    public static final DeferredItem<SpeedCapItem> SPEEDCAP = ITEMS.register("speed_cap", () -> new SpeedCapItem(SpeedCapItem.DEFAULT_PROPERTIES));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
