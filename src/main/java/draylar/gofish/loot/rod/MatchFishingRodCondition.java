package draylar.gofish.loot.rod;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import draylar.gofish.registry.GoFishLoot;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public record MatchFishingRodCondition(List<Identifier> rods) implements LootCondition {

    public static final MapCodec<MatchFishingRodCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Identifier.CODEC.listOf().fieldOf("rods").forGetter(MatchFishingRodCondition::rods)
                    )
                    .apply(instance, MatchFishingRodCondition::new)
    );

    @Override
    public LootConditionType getType() {
        return GoFishLoot.MATCH_FISHING_ROD;
    }

    @Override
    public Set<LootContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootContextParameters.TOOL);
    }

    @Override
    public boolean test(LootContext lootContext) {
        if(lootContext.get(LootContextParameters.TOOL) != null) {
            Identifier tool = lootContext.get(LootContextParameters.TOOL).getItem().getRegistryEntry().getKey().get().getValue();
            return rods.contains(tool);
        }

        return false;
    }

    public static LootCondition.Builder builder(Identifier... rods) {
        return () -> new MatchFishingRodCondition(List.of(rods));
    }
}