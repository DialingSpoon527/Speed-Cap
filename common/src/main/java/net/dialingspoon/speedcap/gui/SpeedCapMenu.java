package net.dialingspoon.speedcap.gui;

import net.dialingspoon.speedcap.PlatformSpecific;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class SpeedCapMenu extends AbstractContainerMenu {
    private final ItemStack cap;

    public SpeedCapMenu(int i, Inventory playerInventory) {
        super(PlatformSpecific.getMenu(), i);
        cap = findSpeedCap(playerInventory);
    }

    private ItemStack findSpeedCap(Inventory playerInventory) {
        ItemStack stack = playerInventory.player.getMainHandItem();
        if (stack.is(PlatformSpecific.getItem())) return stack;
        stack = playerInventory.player.getOffhandItem();
        if (stack.is(PlatformSpecific.getItem())) return stack;
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return !cap.isEmpty();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    public ItemStack getCap() {
        return cap;
    }
}
