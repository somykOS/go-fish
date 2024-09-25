package draylar.gofish.registry;

import draylar.gofish.GoFish;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class GoFishParticles {

    public static final SimpleParticleType LAVA_FISHING = register("lava_fishing", false);

    private static SimpleParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registries.PARTICLE_TYPE, GoFish.id(name), new SimpleParticleType(alwaysShow) {});
    }

    public static void init() {

    }

    private GoFishParticles() {

    }
}
