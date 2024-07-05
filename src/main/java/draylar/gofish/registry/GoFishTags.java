package draylar.gofish.registry;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class GoFishTags {
    public static final TagKey<Biome> ICY = TagKey.of(RegistryKeys.BIOME, Identifier.of("gofish", "icy_biomes"));
    public static final TagKey<Biome> PLAINS = TagKey.of(RegistryKeys.BIOME, Identifier.of("gofish", "plains_biomes"));
    public static final TagKey<Biome> SWAMP = TagKey.of(RegistryKeys.BIOME, Identifier.of("gofish", "swamp_biomes"));
}
