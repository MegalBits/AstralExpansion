package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.megal.enchantment.EnchantmentContainer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentProvider extends FabricDynamicRegistryProvider {
    public ModEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RegistryEntryLookup<DamageType> damageTypeLookup = entries.getLookup(RegistryKeys.DAMAGE_TYPE);
        RegistryEntryLookup<Enchantment> enchantmentLookup = entries.getLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> itemLookup = entries.getLookup(RegistryKeys.ITEM);
        RegistryEntryLookup<Block> blockLookup = entries.getLookup(RegistryKeys.BLOCK);
        EnchantmentContainer.enchantmentContainers.forEach(e -> {
            entries.add(
                    e.getRegistryKey(),
                    e.getBuilder(damageTypeLookup, enchantmentLookup, itemLookup, blockLookup).build(e.getRegistryKey().getValue())
            );
        });
    }

    @Override
    public String getName() {
        return "Enchantments";
    }
}
