package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.interfaces.HumanoidRenderStateInterface;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HumanoidRenderState.class)
public class HumanoidRenderStateMixin implements HumanoidRenderStateInterface {
    ItemStack speedCap;

    @Override
    public ItemStack getSpeedCap() {
        return speedCap;
    }

    @Override
    public void setSpeedCap(ItemStack stack) {
        speedCap = stack;
    }
}
