package net.megal.enchantment;

import net.megal.AstralExpansion;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentContainer {
    public static List<EnchantmentContainer> enchantmentContainers = new ArrayList<>();

    private final RegistryKey<Enchantment> registryKey;
    private final EnchantmentBuilder enchantmentBuilder;

    public EnchantmentContainer(String id, EnchantmentBuilder enchantmentBuilder) {
        registryKey = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(AstralExpansion.MOD_ID, id));
        this.enchantmentBuilder = enchantmentBuilder;

        enchantmentContainers.add(this);
    }

    public RegistryKey<Enchantment> getRegistryKey() {
        return registryKey;
    }

    public Enchantment.Builder getBuilder(Registerable<Enchantment> registry) {
        return enchantmentBuilder.builder(
                registry.getRegistryLookup(RegistryKeys.DAMAGE_TYPE),
                registry.getRegistryLookup(RegistryKeys.ENCHANTMENT),
                registry.getRegistryLookup(RegistryKeys.ITEM),
                registry.getRegistryLookup(RegistryKeys.BLOCK)
        );
    }

    public Enchantment.Builder getBuilder(RegistryEntryLookup<DamageType> damageTypeLookup, RegistryEntryLookup<Enchantment> enchantmentLookup, RegistryEntryLookup<Item> itemLookup, RegistryEntryLookup<Block> blockLookup) {
        return enchantmentBuilder.builder(damageTypeLookup, enchantmentLookup, itemLookup, blockLookup);
    }

    @FunctionalInterface
    public interface EnchantmentBuilder {
        Enchantment.Builder builder(
                RegistryEntryLookup<DamageType> damageTypeLookup,
                RegistryEntryLookup<Enchantment> enchantmentLookup,
                RegistryEntryLookup<Item> itemLookup,
                RegistryEntryLookup<Block> blockLookup
        );
    }
}
