package draylar.gofish.registry;

import draylar.gofish.GoFish;
import draylar.gofish.impl.GoFishLootTables;
import draylar.gofish.loot.WeatherCondition;
import draylar.gofish.loot.biome.MatchBiomeLootCondition;
import draylar.gofish.loot.moon.FullMoonCondition;
import draylar.gofish.loot.rod.MatchFishingRodCondition;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.FishingHookPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class GoFishLootHandler {
    public static void init() {
        registerFishHandler();
    }

    private static void registerFishHandler() {
        LootTableEvents.MODIFY.register((RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source) -> {
            if(LootTables.FISHING_GAMEPLAY.equals(key) && source.isBuiltin()) {
                var canModify = new MutableBoolean(true);
                tableBuilder.modifyPools(lpb -> {
                    if (canModify.booleanValue()) {
                        canModify.setFalse();
                    } else {
                        return;
                    }
                    lpb.with(LootTableEntry.builder(GoFishLootTables.CRATES)
                            .weight(5)
                            .quality(2)
                            .conditionally(
                                    EntityPropertiesLootCondition.builder(
                                            LootContext.EntityTarget.THIS,
                                            EntityPredicate.Builder.create().typeSpecific(FishingHookPredicate.of(true))
                                    )
                            )
                    );
                });
            } else if(LootTables.FISHING_FISH_GAMEPLAY.equals(key) || GoFishLootTables.NETHER_FISHING.equals(key) || GoFishLootTables.END_FISHING.equals(key) && source.isBuiltin()) {
                var canModify = new MutableBoolean(true);
                tableBuilder.modifyPools(lpb -> {
                    if (canModify.booleanValue()) {
                        canModify.setFalse();
                    } else {
                        return;
                    }
                    // The default fish loot table has a total weight of 100.
                    // An entry with a weight of 10 represents a 10% chance to get that fish compared to the standard 4, but the percentage goes down as more custom fish are added.
                    // In most situations, only 1-2 fish are added per biome or area, so the chance for that fish is still ~5-10%.

                    if (LootTables.FISHING_GAMEPLAY.equals(key)) {

                    // Cold Fish in Icy biomes
                    lpb.with(ItemEntry.builder(GoFishItems.ICICLE_FISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.ICY)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.SNOWBALL_FISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.ICY)).build());

                    // Swamp
                    lpb.with(ItemEntry.builder(GoFishItems.SLIMEFISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.SWAMP)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.LILYFISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.SWAMP)).build());

                    // Ocean
                    lpb.with(ItemEntry.builder(GoFishItems.SEAWEED_EEL).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.OCEAN)).build());

                    // Mesa
                    lpb.with(ItemEntry.builder(GoFishItems.TERRAFISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.MESA)).build());

                    // General Plains
                    lpb.with(ItemEntry.builder(GoFishItems.CARROT_CARP).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.PLAINS)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.OAKFISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.PLAINS)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.CARROT_CARP).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.FOREST)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.OAKFISH).weight(10).conditionally(MatchBiomeLootCondition.builder(ConventionalBiomeTags.FOREST)).build());

                    // Misc
                    lpb.with(ItemEntry.builder(GoFishItems.LUNARFISH).weight(50).conditionally(FullMoonCondition.builder()).build());
                    lpb.with(ItemEntry.builder(GoFishItems.GALAXY_STARFISH).weight(25).conditionally(FullMoonCondition.builder()).build());
                    lpb.with(ItemEntry.builder(GoFishItems.STARRY_SALMON).weight(50).conditionally(FullMoonCondition.builder()).build());
                    lpb.with(ItemEntry.builder(GoFishItems.NEBULA_SWORDFISH).weight(25).conditionally(FullMoonCondition.builder()).build());

                    // weather
                    lpb.with(ItemEntry.builder(GoFishItems.RAINY_BASS).weight(100).conditionally(WeatherCondition.builder(true, false, false)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.THUNDERING_BASS).weight(50).conditionally(WeatherCondition.builder(false, true, false)).build());
                    lpb.with(ItemEntry.builder(GoFishItems.CLOUDY_CRAB).weight(50).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(NumberRange.DoubleRange.atLeast(150)))).build());
                    lpb.with(ItemEntry.builder(GoFishItems.BLIZZARD_BASS).weight(100).conditionally(WeatherCondition.builder(false, false, true)).build());

                    } else if (GoFishLootTables.NETHER_FISHING.equals(key)) {

                    } else if (GoFishLootTables.END_FISHING.equals(key)) {
                        lpb.with(ItemEntry.builder(GoFishItems.MATRIX_FISH)
                                .weight(100)
                                .conditionally(MatchFishingRodCondition.builder(GoFish.id("matrix_rod")))
//                                .conditionally(MatchBiomeLootCondition.builder(net.minecraft.world.biome.BiomeKeys.END_HIGHLANDS, net.minecraft.world.biome.BiomeKeys.END_MIDLANDS, net.minecraft.world.biome.BiomeKeys.END_BARRENS, net.minecraft.world.biome.BiomeKeys.SMALL_END_ISLANDS))
                                .build());
                    }

                    lpb.with(ItemEntry.builder(Items.SKELETON_SKULL)
                            .weight(50)
                            .conditionally(MatchFishingRodCondition.builder(GoFish.id("skeletal_rod")))
                            .build());
//                    lpb.with(ItemEntry.builder(Registries.ITEM.get(Identifier.of("bartefacts:ancient_bones")))
//                            .weight(10)
//                            .conditionally(MatchFishingRodCondition.builder(GoFish.id("skeletal_rod")))
//                            .build());
                });
            }
        });
    }
}
