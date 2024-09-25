package net.megal.registry.tag;

import net.megal.AstralExpansion;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {
    public static final TagKey<Item> QUICKENED_ENCHANTABLE = of("enchantable/quickened_enchantable");
    public static final TagKey<Item> SIPHON_ENCHANTABLE = of("enchantable/siphon_enchantable");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(AstralExpansion.MOD_ID, id));
    }

    public static void initialize() {}
}
