package net.dialingspoon.speedcap.fabric.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
//import dev.emi.trinkets.api.SlotReference;
//import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.dialingspoon.speedcap.fabric.Model;
import net.dialingspoon.speedcap.fabric.registry.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;

public class TrinketRenderer /*implements dev.emi.trinkets.api.client.TrinketRenderer*/ {

    public static void register() {
        //TrinketRendererRegistry.registerRenderer(ModItems.SPEEDCAP, new TrinketRenderer());
    }

//    @Override
//    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntityRenderState> entityModel, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState livingEntityRenderState, float v, float v1) {
//        if (livingEntityRenderState instanceof HumanoidRenderState humanoidRenderState) {
//            Model.capModel.render(poseStack, submitNodeCollector, itemStack, humanoidRenderState, light);
//        }
//    }
}
