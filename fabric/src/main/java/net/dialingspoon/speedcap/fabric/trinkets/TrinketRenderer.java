package net.dialingspoon.speedcap.fabric.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.dialingspoon.speedcap.fabric.Model;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;

public class TrinketRenderer implements dev.emi.trinkets.api.client.TrinketRenderer {

    public static void register() {
        TrinketRendererRegistry.registerRenderer(ModItems.SPEEDCAP, new TrinketRenderer());
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntityRenderState> entityModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntityRenderState livingEntityRenderState, float v, float v1) {
        if (entityModel instanceof HumanoidModel<?>) {
            HumanoidModel<HumanoidRenderState> humanoidModel = (HumanoidModel<HumanoidRenderState>) entityModel;
            Model.capModel.render(poseStack, multiBufferSource, itemStack, light, humanoidModel);
        }
    }
}
