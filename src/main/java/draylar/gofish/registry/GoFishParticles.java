package draylar.gofish.registry;

import draylar.gofish.GoFish;
import draylar.gofish.particle.CustomDefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class GoFishParticles {

    public static final ParticleEffect LAVA_FISHING = ParticleTypes.LAVA;//register("lava_fishing", false);

    //private static DefaultParticleType register(String name, boolean alwaysShow) {
    //    return Registry.register(Registries.PARTICLE_TYPE, GoFish.id(name), new CustomDefaultParticleType(alwaysShow));
    //}

    public static void init() {

    }

    private GoFishParticles() {

    }
}
