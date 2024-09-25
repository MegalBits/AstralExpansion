package net.megal.enchantment;

import net.megal.registry.tag.ModItemTags;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;

public class ModEnchantments {
    public static EnchantmentContainer EXTRACTION = new EnchantmentContainer("extraction",
            (damageTypeLookup, enchantmentLookup, itemLookup, blockLookup) ->
                    Enchantment.builder(
                            Enchantment.definition(
                                    itemLookup.getOrThrow(ModItemTags.SIPHON_ENCHANTABLE),
                                    5,
                                    5,
                                    Enchantment.leveledCost(1, 24),
                                    Enchantment.leveledCost(27, 24),
                                    4,
                                    AttributeModifierSlot.ANY
                            )
                    )
    );

    public static EnchantmentContainer QUICKENED = new EnchantmentContainer("quickened",
            (damageTypeLookup, enchantmentLookup, itemLookup, blockLookup) ->
                    Enchantment.builder(
                            Enchantment.definition(
                                    itemLookup.getOrThrow(ModItemTags.QUICKENED_ENCHANTABLE),
                                    3,
                                    1,
                                    Enchantment.constantCost(32),
                                    Enchantment.constantCost(52),
                                    4,
                                    AttributeModifierSlot.ANY
                            )
                    )
    );

    public static void registerEnchantments(Registerable<Enchantment> registry) {
        EnchantmentContainer.enchantmentContainers.forEach(enchantmentContainer -> {
            registry.register(
                    enchantmentContainer.getRegistryKey(),
                    enchantmentContainer.getBuilder(registry)
                            .build(enchantmentContainer.getRegistryKey().getValue()));
        });
    }

    public static int getEnchantmentLevel(ItemStack stack, ServerWorld world, RegistryKey<Enchantment> registryKey) {
        return EnchantmentHelper.getEnchantments(stack).getLevel(ModEnchantments.getRegistryEntry(world, registryKey));
    }

    public static RegistryEntry<Enchantment> getRegistryEntry(ServerWorld world, RegistryKey<Enchantment> registryKey) {
        Registry<Enchantment> enchantmentRegistry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT);
        return enchantmentRegistry.getEntry(enchantmentRegistry.get(registryKey));
    }

    public static void initialize() {}
}
