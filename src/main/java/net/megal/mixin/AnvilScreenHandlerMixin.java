package net.megal.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.megal.AstralExpansion;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
    @Unique
    private static final int maxAnvilCost = 100;

    @ModifyExpressionValue(
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=40"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I", ordinal = 0),
                    to = @At(value = "CONSTANT", args = "intValue=39")
            ),
            method = "updateResult"
    )
    private int changeMaxRenameCost(int original) {
        return maxAnvilCost;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/Property;set(I)V"
            ),
            method = "updateResult"
    )
    private int setToMaxCostWhenOver(int original) {
        return Math.min(original, maxAnvilCost);
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/Property;set(I)V"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I", ordinal = 0),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I", ordinal = 1)
            ),
            method = "updateResult"
    )
    private int setMaxRenameCost(int original) {
        return maxAnvilCost;
    }

    @ModifyExpressionValue(
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z"
            ),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "intValue=39")
            ),
            method = "updateResult"
    )
    private boolean removeMaxLevelCap(boolean original) {
        return true;
    }
}
