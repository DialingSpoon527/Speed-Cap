package net.dialingspoon.speedcap.fabric;

import net.dialingspoon.speedcap.fabric.client.SpeedCapFabricClient;
import net.dialingspoon.speedcap.models.CapModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class Model {
    public static CapModel<HumanoidRenderState> capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(SpeedCapFabricClient.CAP_LAYER));
}
