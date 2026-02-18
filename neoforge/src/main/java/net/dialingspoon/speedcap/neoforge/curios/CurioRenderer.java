package net.dialingspoon.speedcap.neoforge.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dialingspoon.speedcap.neoforge.client.SpeedCapNeoForgeClientEvents;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import javax.annotation.Nonnull;

public class CurioRenderer implements ICurioRenderer {

    @Override
    public <S extends LivingEntityRenderState, M extends EntityModel<? super S>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, @Nonnull MultiBufferSource renderTypeBuffer, int light, S renderState, RenderLayerParent<S, M> renderLayerParent, EntityRendererProvider.Context context, float yRotation, float xRotation) {
        M model = renderLayerParent.getModel();
        if (model instanceof HumanoidModel<?>) {
            HumanoidModel<HumanoidRenderState> humanoidModel = (HumanoidModel<HumanoidRenderState>)model;
            SpeedCapNeoForgeClientEvents.capModel.render(matrixStack, renderTypeBuffer, stack, light, humanoidModel, true);
        }
    }
}
