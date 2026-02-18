package net.dialingspoon.speedcap.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.Util;
import net.dialingspoon.speedcap.interfaces.EntityInterface;
import net.dialingspoon.speedcap.item.CapSettingsComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityInterface {
    
    @Unique
    private boolean speedcap$moving;
    @Unique
    private boolean speedcap$couldSpeed;
    @Unique
    private long speedcap$localTick;
    @Unique
    private boolean speedcap$clientSpeeding;

    @Unique
    private ItemStack speedcap$cap;
    @Unique
    private CapSettingsComponent speedcap$data;

    @Override
    public void speedcap$moving(boolean b) {
        speedcap$moving = b;
    }
    @Override
    public void speedcap$couldSpeed(boolean b) {
        speedcap$couldSpeed = b;
    }

    @Override
    public void speedcap$setData(CapSettingsComponent settings) {
        speedcap$data = settings;
    }
    @Override
    public CapSettingsComponent speedcap$getData() {
        return this.speedcap$data;
    }
    @Override
    public void speedcap$setCapStack(ItemStack stack) {
        this.speedcap$cap = stack;
    }
    @Override
    public ItemStack speedcap$getCapStack() {
        return this.speedcap$cap;
    }


    @ModifyVariable(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), argsOnly = true)
    private Vec3 slowDown(Vec3 vec3) {
        if ((Object)this instanceof LivingEntity entity) {

            if (Util.shouldHandleSelf(entity)) {
                boolean client = !(entity.level() instanceof ServerLevel);
                boolean oldSpeeding = false;
                if (client) {
                    oldSpeeding = speedcap$clientSpeeding;
                }

                long gameTime = entity.level() != null ? entity.level().getGameTime() : 0;
                if (speedcap$localTick != gameTime) {
                    if (client) {
                        speedcap$clientSpeeding = false;
                    } else {
                        PlatformSpecific.setSpeeding((Entity)(Object)this, false);
                    }
                    speedcap$localTick = gameTime;
                }

                ItemStack cap = Util.getActiveCap(entity);
                if (!cap.isEmpty() && speedcap$data.moveActive()) {
                    Vector3d modifiedVec = new Vector3d(vec3.x, 0, vec3.z);

                    float f = speedcap$data.moveSpeed() / 20.5f;
                    if (speedcap$data.modifiable()) {
                        if(speedcap$couldSpeed && speedcap$moving) {
                            if (client) {
                                speedcap$clientSpeeding = true;
                            } else {
                                PlatformSpecific.setSpeeding((Entity)(Object)this, true);
                            }
                        }

                    } else if (modifiedVec.length() >= f) {
                        modifiedVec.normalize().mul(f);
                        if (client) {
                            speedcap$clientSpeeding = true;
                        } else {
                            PlatformSpecific.setSpeeding((Entity)(Object)this, true);
                        }
                    }

                    double cappedY = vec3.y;
                    if (speedcap$data.jump()) {
                        cappedY = Math.min(vec3.y, .44);
                        if (vec3.y != cappedY) {
                            if (client) {
                                speedcap$clientSpeeding = true;
                            } else {
                                PlatformSpecific.setSpeeding((Entity)(Object)this, true);
                            }
                        }
                    }
                    if (client && oldSpeeding != speedcap$clientSpeeding) {
                        PlatformSpecific.sendAnimToServer(speedcap$clientSpeeding);
                    }
                    return new Vec3(modifiedVec.x, cappedY, modifiedVec.z);
                }
                if (client && oldSpeeding != speedcap$clientSpeeding) {
                    PlatformSpecific.sendAnimToServer(speedcap$clientSpeeding);
                }
            }
        }
        return vec3;
    }
}
