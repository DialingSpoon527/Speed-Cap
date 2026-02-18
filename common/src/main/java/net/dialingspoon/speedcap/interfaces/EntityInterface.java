package net.dialingspoon.speedcap.interfaces;

import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.world.item.ItemStack;

public interface EntityInterface {

    void speedcap$couldSpeed(boolean b);

    void speedcap$moving(boolean b);

    CapSettingsComponent speedcap$getData();

    void speedcap$setData(CapSettingsComponent settings);

    ItemStack speedcap$getCapStack();

    void speedcap$setCapStack(ItemStack stack);
}
