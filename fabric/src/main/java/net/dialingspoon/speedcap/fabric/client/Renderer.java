package net.dialingspoon.speedcap.fabric.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dialingspoon.speedcap.fabric.Model;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class Renderer implements ArmorRenderer {

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource renderTypeBuffer, ItemStack stack, HumanoidRenderState bipedEntityRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        Model.capModel.render(matrixStack, renderTypeBuffer, stack, bipedEntityRenderState, light);
    }
}
