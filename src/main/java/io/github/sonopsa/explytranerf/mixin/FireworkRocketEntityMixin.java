package io.github.sonopsa.explytranerf.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "doubleValue=1.5"))
    private double slowerFireworks(double original){
        return original * 0.8333;
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)V", at = @At("RETURN"))
    private void elytraFireworkDamage(World world, ItemStack stack, LivingEntity shooter, CallbackInfo ci){
        if (shooter != null) {
            if (shooter.isFallFlying()) {
                ItemStack itemStack = shooter.getEquippedStack(EquipmentSlot.CHEST);
                if (itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack)){
                    int damageNum = Math.min(itemStack.getMaxDamage()-1 - itemStack.getDamage(), 3);

                    itemStack.damage(damageNum, shooter, (player) -> {
                        player.sendEquipmentBreakStatus(EquipmentSlot.CHEST);
                    });
                }
            }
        }
    }
}
