package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.interfaces.HumanoidRenderStateInterface;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HumanoidRenderState.class)
public class HumanoidRenderStateMixin implements HumanoidRenderStateInterface {
    @Unique
    ItemStack speedcap$speedCap;

    @Override
    public ItemStack getSpeedCap() {
        return speedcap$speedCap;
    }

    @Override
    public void setSpeedCap(ItemStack stack) {
        speedcap$speedCap = stack;
    }
}
