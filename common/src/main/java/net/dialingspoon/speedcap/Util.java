package net.dialingspoon.speedcap;

import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.minecraft.nbt.CompoundTag;
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

                entityInterface.speedcap$setData(Util.getOrCreateTag(item));
            }
        }

        return item;
    }

    public static boolean shouldHandleSelf(LivingEntity entity) {
        return (entity instanceof Player) != (entity.level() instanceof ServerLevel);
    }

    public static CompoundTag getOrCreateTag(ItemStack item) {
        if (!item.getOrCreateTag().contains("SpeedCap")) {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("moveSpeed", 4.8f);
            tag.putFloat("mineSpeed", 4);
            tag.putBoolean("moveActive", true);
            tag.putBoolean("modifiable", false);
            tag.putBoolean("jump", true);
            tag.putBoolean("stoponadime", false);
            tag.putBoolean("mineActive", true);
            tag.putBoolean("creative", true);
            item.getTag().put("SpeedCap", tag);
        }
        return item.getTag().getCompound("SpeedCap");
    }
}
