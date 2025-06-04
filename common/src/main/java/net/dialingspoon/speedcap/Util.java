package net.dialingspoon.speedcap;

import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Util {

    public static ItemStack getActiveCap(LivingEntity entity) {
        ItemStack head = entity.getSlot(103).get();
        ItemStack item = ItemStack.EMPTY;

        if (head.is(PlatformSpecific.getItem())) {
            item = head;
        } else {
            ItemStack curiosItem = PlatformSpecific.getItemFromModdedSlots(entity);
            if (curiosItem != null && !curiosItem.isEmpty()) {
                item = curiosItem;
            }
        }

        if (item != null) {
            EntityInterface entityInterface = (EntityInterface) entity;
            if (entityInterface.speedcap$getCapStack() != item) {
                entityInterface.speedcap$setCapStack(item);

                entityInterface.speedcap$setData(item.getOrDefault(PlatformSpecific.getDataComponent(), new CapSettingsComponent(4.8f, 4, true, false, true, false, true, true)));
            }
        }

        return item;
    }

    public static boolean shouldHandleSelf(LivingEntity entity) {
        return (entity instanceof Player) != (entity.level() instanceof ServerLevel);
    }
}
