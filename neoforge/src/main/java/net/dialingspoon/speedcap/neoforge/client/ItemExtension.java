package net.dialingspoon.speedcap.neoforge.client;

import net.dialingspoon.speedcap.models.CapModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ItemExtension implements IClientItemExtensions {

    public static final ItemExtension INSTANCE = new ItemExtension();

    @Override
    public HumanoidModel<?> getGenericArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
        CapModel<HumanoidRenderState> model = SpeedCapNeoForgeClientEvents.capModel;
        return model;
    }
}
