package net.dialingspoon.speedcap.fabric.trinkets;

public class TrinketRenderer /*implements dev.emi.trinkets.api.client.TrinketRenderer*/ {

    public static void register() {
        //TrinketRendererRegistry.registerRenderer(ModItems.SPEEDCAP, new TrinketRenderer());
    }

    /*@Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntityRenderState> entityModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntityRenderState livingEntityRenderState, float v, float v1) {
        if (entityModel instanceof HumanoidModel<?>) {
            HumanoidModel<HumanoidRenderState> humanoidModel = (HumanoidModel<HumanoidRenderState>)entityModel;
            Model.capModel.render(poseStack, multiBufferSource, itemStack, light, humanoidModel);
        }
    }*/
}
