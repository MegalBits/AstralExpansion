package net.megal.mixin;

import net.megal.enchantment.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.Registerable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Enchantments.class)
public abstract class EnchantmentsMixin {
    @Inject(
            at = @At("TAIL"),
            method = "bootstrap"
    )
    private static void registerModEnchantments(Registerable<Enchantment> registerable, CallbackInfo info) {
        ModEnchantments.registerEnchantments(registerable);
    }
}
