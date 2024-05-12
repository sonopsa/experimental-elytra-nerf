package io.github.sonopsa.explytranerf.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.sonopsa.explytranerf.ExperimentalElytraNerf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyExpressionValue(method = "tickFallFlying", at = @At(value = "CONSTANT", args = "intValue=2"))
    private int injectedz(int original){
        int ticks = super.getWorld().getGameRules().getInt(ExperimentalElytraNerf.ELYTRA_FLIGHT_DURABILITY_RATE);
        if (ticks == 0){
            return 2;
        }
        return ticks*2;
    }

    @ModifyArg(method = "tickFallFlying", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V"), index = 0)
    private int dontDamageElytra(int damage) {
        int ticks = super.getWorld().getGameRules().getInt(ExperimentalElytraNerf.ELYTRA_FLIGHT_DURABILITY_RATE);
        return ticks == 0 ? 0 : 1;
    }
}
