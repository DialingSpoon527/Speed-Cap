package net.dialingspoon.speedcap.item;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

public class ModMaterials {

    public static final ArmorMaterial SPEEDCAP = register("speed_cap", 15, 2, 9, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SpeedCap.MOD_ID, "repairs_speed_cap")));

    private static ArmorMaterial register(String name, int durabilityMultiplier, int protectionAmount, int enchantability, Holder<SoundEvent> soundEvent, float toughness, float knockbackResistence, TagKey<Item> repairMaterial) {
        EnumMap<ArmorType, Integer> defenseMap = new EnumMap<>(ArmorType.class);
        for (ArmorType type : ArmorType.values()) {
            defenseMap.put(type, protectionAmount);
        }
        return new ArmorMaterial(durabilityMultiplier, defenseMap, enchantability, soundEvent, toughness, knockbackResistence, repairMaterial, ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(SpeedCap.MOD_ID, name)));
    }
}
