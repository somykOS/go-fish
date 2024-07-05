package draylar.gofish.mixin;

import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FishingBobberEntity.class)
public interface FishingBobberEntityAccessor {
    @Accessor
    int getLuckBonus();

    @Mutable
    @Accessor
    void setLuckBonus(int luckBonus);

    @Accessor
    int getWaitTimeReductionTicks();

    @Mutable
    @Accessor
    void setWaitTimeReductionTicks(int ticks);
}
