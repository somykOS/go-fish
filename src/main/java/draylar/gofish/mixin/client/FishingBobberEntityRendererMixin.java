package draylar.gofish.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import draylar.gofish.item.ExtendedFishingRodItem;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberEntityRendererMixin {

    @ModifyExpressionValue(method = "getHandPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean checkIfCustomFishingRod(boolean original, @Local ItemStack stack) {
        return original || stack.getItem() instanceof ExtendedFishingRodItem;
    }
}
