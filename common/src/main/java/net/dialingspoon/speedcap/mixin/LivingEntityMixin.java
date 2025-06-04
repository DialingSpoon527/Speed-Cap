package net.dialingspoon.speedcap.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
import net.dialingspoon.speedcap.item.CapAnimComponent;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityInterface {
    @Shadow
    public float xxa;
    @Shadow
    public float zza;

    @Shadow
    @Final private static AttributeModifier SPEED_MODIFIER_SPRINTING;
    @Override
    public AttributeModifier getSPEED_MODIFIER_SPRINTING() {
        return SPEED_MODIFIER_SPRINTING;
    }

    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"))
    private void stoponadime(LivingEntity instance, double x, double y, double z, Operation<Void> original) {
        ItemStack cap = Util.getActiveCap(instance);
        CapSettingsComponent data = ((EntityInterface) instance).speedcap$getData();
        if (!cap.isEmpty() && data.moveActive() && data.stoponadime()) {
            if (this.xxa == 0.0f && this.zza == 0.0f) {
                original.call(instance, 0d, y, 0d);
            }
        } else {
            original.call(instance, x, y, z);
        }
    }

    @Inject(method = "travel", at = @At(value = "HEAD"))
    private void checkMoving(Vec3 vec3, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (Util.shouldHandleSelf(entity)) {
            ItemStack cap = Util.getActiveCap(entity);
            CapSettingsComponent data = ((EntityInterface) entity).speedcap$getData();
            ((EntityInterface) entity).speedcap$moving(!cap.isEmpty() && data.moveActive() && data.modifiable() && !(vec3.x == 0 && vec3.z == 0));
        }
    }

    @Inject(method = "getSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void checkSpeed(CallbackInfoReturnable<Float> cir) {
        float speed = cir.getReturnValue();
        EntityInterface entity = (EntityInterface) this;
        ItemStack cap = Util.getActiveCap((LivingEntity)(Object)this);
        CapSettingsComponent data = entity.speedcap$getData();

        if (!cap.isEmpty()) {
            float maxSpeed = Math.max(data.moveSpeed(), 0.1f) / 44f;
            if (data.moveActive() && data.modifiable() && speed > maxSpeed) {
                speed = maxSpeed;
                entity.speedcap$couldSpeed(true);
            } else {
                entity.speedcap$couldSpeed(false);
            }
        } else {
            entity.speedcap$couldSpeed(false);
        }

        cir.setReturnValue(speed);
    }

    @Inject(at = @At(value = "HEAD", ordinal = -2), method = "baseTick")
    private void flipAnimation(CallbackInfo ci) {
        ItemStack cap = Util.getActiveCap((LivingEntity)(Object)this);
        if (cap != null) {
            CapAnimComponent animComponent = cap.getOrDefault(PlatformSpecific.getAnimComponent(), new CapAnimComponent(false, 0));
            Level level = ((LivingEntity) (Object) this).level();
            if (!level.isClientSide) {
                boolean isSpeeding = ((EntityInterface) this).speedcap$isSpeeding();

                if (isSpeeding != animComponent.sailDirection()) {
                    long tick = level.getGameTime();
                    long sailTick = tick - (9 - Math.min((tick - animComponent.sailTick()), 10));
                    cap.set(PlatformSpecific.getAnimComponent(), new CapAnimComponent(isSpeeding, sailTick));
                }
            }
        }
    }
}
