package net.dialingspoon.speedcap.fabric.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.dialingspoon.speedcap.item.SpeedCapItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class ModItems {
    public static final SpeedCapItem SPEEDCAP = Registry.register(BuiltInRegistries.ITEM, Identifier.tryBuild(SpeedCap.MOD_ID, "speed_cap"), new SpeedCapItem(SpeedCapItem.DEFAULT_PROPERTIES));

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> {
            content.addAfter(Items.TURTLE_HELMET, SPEEDCAP);
        });
    }
}
