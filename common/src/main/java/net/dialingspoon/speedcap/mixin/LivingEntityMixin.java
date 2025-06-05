package net.dialingspoon.speedcap.mixin;

import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.interfaces.LivingEntityInterface;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityInterface {
    @Shadow
    public float xxa;
    @Shadow
    public float zza;

    @Unique
    boolean speedcap$sailDirection;
    @Unique
    long speedcap$sailTick;

    @Override
    public boolean speedcap$sailDirection() {
        return speedcap$sailDirection;
    }
    @Override
    public long speedcap$getSailTick() {
        return speedcap$sailTick;
    }

    @Shadow
    @Final private static AttributeModifier SPEED_MODIFIER_SPRINTING;
    @Override
    public AttributeModifier getSPEED_MODIFIER_SPRINTING() {
        return SPEED_MODIFIER_SPRINTING;
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"))
    private void stoponadime(LivingEntity instance, double x, double y, double z) {
        ItemStack cap = Util.getActiveCap(instance);
        CompoundTag data = ((EntityInterface) instance).speedcap$getData();
        if (!cap.isEmpty() && data.getBoolean("moveActive") && data.getBoolean("stoponadime")) {
            if (this.xxa == 0.0f && this.zza == 0.0f) {
                instance.setDeltaMovement(0, y, 0);
            }
        } else {
            instance.setDeltaMovement(x, y, z);
        }
    }

    @Inject(method = "travel", at = @At(value = "HEAD"))
    private void checkMoving(Vec3 vec3, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (Util.shouldHandleSelf(entity)) {
            ItemStack cap = Util.getActiveCap(entity);
            CompoundTag data = ((EntityInterface) entity).speedcap$getData();
            ((EntityInterface) entity).speedcap$moving(!cap.isEmpty() && data.getBoolean("moveActive") && data.getBoolean("modifiable") && !(vec3.x == 0 && vec3.z == 0));
        }
    }

    @Inject(method = "getSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void checkSpeed(CallbackInfoReturnable<Float> cir) {
        float speed = cir.getReturnValue();
        EntityInterface entity = (EntityInterface) this;
        ItemStack cap = Util.getActiveCap((LivingEntity)(Object)this);
        CompoundTag data = entity.speedcap$getData();

        if (!cap.isEmpty()) {
            float maxSpeed = Math.max(data.getFloat("moveSpeed"), 0.1f) / 44f;
            if (data.getBoolean("moveActive") && data.getBoolean("modifiable") && speed > maxSpeed) {
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
        Level level = ((LivingEntity)(Object)this).level();
        if (level.isClientSide) {
            boolean isSpeeding = ((EntityInterface) this).speedcap$isSpeeding();

            if (isSpeeding != speedcap$sailDirection) {
                long tick = level.getGameTime();
                speedcap$sailTick = tick - (9 - Math.min((tick - speedcap$sailTick), 10));
                speedcap$sailDirection = isSpeeding;
            }
        }
    }
}
