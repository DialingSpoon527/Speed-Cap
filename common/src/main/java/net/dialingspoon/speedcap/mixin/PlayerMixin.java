package net.dialingspoon.speedcap.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {

    @WrapOperation(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getAttributeValue(Lnet/minecraft/core/Holder;)D"))
    private double checkSpeed(Player player, Holder<Attribute> attribute, Operation<Double> original) {
        AttributeInstance movementAttribute = player.getAttributes().getInstance(attribute);
        AttributeModifier sprintModifier = ((LivingEntityInterface)player).getSPEED_MODIFIER_SPRINTING();
        double speed = original.call(player, attribute);

        boolean isSprinting = movementAttribute.hasModifier(sprintModifier.id());
        if (isSprinting) {
            speed /= 1.0 + sprintModifier.amount();
        }

        ItemStack cap = Util.getActiveCap(player);
        CapSettingsComponent data = ((EntityInterface) player).speedcap$getData();

        if (!cap.isEmpty()) {
            float maxSpeed = Math.max(data.moveSpeed(), 0.1f) / 44f;
            if (data.moveActive() && data.modifiable() && speed > maxSpeed) {
                speed = maxSpeed;
                ((EntityInterface) player).speedcap$couldSpeed(true);
            } else {
                ((EntityInterface) player).speedcap$couldSpeed(false);
            }
        } else {
            ((EntityInterface) player).speedcap$couldSpeed(false);
        }

        if (isSprinting) {
            speed *= 1.0 + sprintModifier.amount();
        }

        return speed;
    }
}
