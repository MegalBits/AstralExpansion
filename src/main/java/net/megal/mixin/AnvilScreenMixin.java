package net.megal.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin {
    @ModifyExpressionValue(
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z"
            ),
            method = "drawForeground"
    )
    private boolean removeMaxLevelCap(boolean original) {
        return true;
    }
}
