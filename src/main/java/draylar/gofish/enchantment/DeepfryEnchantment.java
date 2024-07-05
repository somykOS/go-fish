package draylar.gofish.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.Optional;

/*
 * Original idea is from The-Myth-Of-Legends at (https://www.reddit.com/r/minecraftsuggestions/comments/ai9hr8/new_fishing_rod_enchantment_deepfry/).
 * "Only 1 level. Upon fishing out either Cod or Salmon, it will be cooked."
 */
public class DeepfryEnchantment extends Enchantment {
    public DeepfryEnchantment() {
        super(new Properties(ItemTags.FISHING_ENCHANTABLE,
                Optional.of(ItemTags.FISHING_ENCHANTABLE), 2, 1,
                new Cost(15, 0), new Cost(50, 0), 7,
                FeatureSet.empty(), new EquipmentSlot[] {
                EquipmentSlot.MAINHAND,
                EquipmentSlot.OFFHAND
        }));
    }
}
