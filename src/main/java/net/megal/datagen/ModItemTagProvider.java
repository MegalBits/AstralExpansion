package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.megal.item.ModItems;
import net.megal.registry.tag.ModItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModItemTags.QUICKENED_ENCHANTABLE)
                .add(ModItems.EXPERIENCE_SIPHON);

        getOrCreateTagBuilder(ModItemTags.SIPHON_ENCHANTABLE)
                .add(ModItems.EXPERIENCE_SIPHON);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModItemTags.SIPHON_ENCHANTABLE);
    }
}
