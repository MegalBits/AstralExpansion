package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.megal.AstralExpansion;
import net.megal.enchantment.EnchantmentContainer;
import net.megal.enchantment.ModEnchantments;
import net.megal.entity.ModEntityTypes;
import net.megal.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ModTranslationProvider extends FabricLanguageProvider {
    private TranslationBuilder translationBuilder;

    public ModTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        this.translationBuilder = translationBuilder;

        autoRegister(ModEnchantments.EXTRACTION);
        autoRegister(ModEnchantments.QUICKENED);

        autoRegister(ModItems.EXPERIENCE_SIPHON);

        autoRegister(ModEntityTypes.DELAYED_PICKUP_EXPERIENCE_ORB);

        includeManualFile();
    }

    private void autoRegister(Item item) {
        translationBuilder.add(item, identifierToName(Registries.ITEM.getId(item)));
    }

    private void autoRegister(Block block) {
        translationBuilder.add(block, identifierToName(Registries.BLOCK.getId(block)));
    }

    private void autoRegister(EntityType<?> entity) {
        translationBuilder.add(entity, identifierToName(Registries.ENTITY_TYPE.getId(entity)));
    }

    private void autoRegister(RegistryKey<Enchantment> enchantment) {
        translationBuilder.add(enchantment.getRegistry().getPath() + "." + enchantment.getValue().toString().replace(':', '.'), identifierToName(enchantment.getValue()));
    }

    private void autoRegister(EnchantmentContainer enchantment) {
        autoRegister(enchantment.getRegistryKey());
    }

    private String identifierToName(Identifier identifier) {
        StringBuilder nameBuilder = new StringBuilder();
        Arrays.stream(identifier.getPath().replace("_", "& ").split("&")).forEach(word -> nameBuilder.append(StringUtil.capitalize(word)));
        return nameBuilder.toString();
    }

    private void includeManualFile() {
        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/astral-expansion/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        }
        catch (Exception e) {
            throw new RuntimeException("Couldn't find existing lang file :P", e);
        }
    }
}