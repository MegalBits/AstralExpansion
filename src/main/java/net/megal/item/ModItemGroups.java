package net.megal.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.megal.AstralExpansion;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> MAIN = register(createKey("main"), FabricItemGroup.builder()
            .displayName(Text.translatable("astral-expansion.itemgroup.main"))
            .icon(() -> new ItemStack(Items.OAK_SAPLING))
            .build()
    );

    public static RegistryKey<ItemGroup> createKey(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(AstralExpansion.MOD_ID, id));
    }

    public static RegistryKey<ItemGroup> register(RegistryKey<ItemGroup> registryKey, ItemGroup itemGroup) {
        Registry.register(Registries.ITEM_GROUP, registryKey, itemGroup);
        return registryKey;
    }
}
