package net.megal.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.megal.AstralExpansion;
import net.megal.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item item() {
        return new Item(new Item.Settings());
    }

    public static Item fitem() {
        return new Item(new Item.Settings().fireproof());
    }

    public static Item.Settings s_sword(ToolMaterial material, int damage, float speed) {
        return new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_sword(ToolMaterial material) {
        return s_sword(material, 3, -2.4f);
    }

    public static Item.Settings s_shovel(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_shovel(ToolMaterial material) {
        return s_shovel(material, 1.5f, -3.0f);
    }

    public static Item.Settings s_pickaxe(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_pickaxe(ToolMaterial material) {
        return s_pickaxe(material, 1.0f, -2.8f);
    }

    public static Item.Settings s_axe(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_axe(ToolMaterial material, float damage) {
        return s_axe(material, damage, -3.2f);
    }

    public static Item.Settings s_axe(ToolMaterial material) {
        return s_axe(material, 6.0f);
    }

    public static Item.Settings s_hoe(ToolMaterial material, float damage, float speed) {
        return new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(material, damage, speed));
    }

    public static Item.Settings s_hoe(ToolMaterial material, float speed) {
        return s_hoe(material, -material.getAttackDamage(), speed);
    }

    public static Item.Settings s_hoe(ToolMaterial material) {
        return s_hoe(material, 0);
    }

    public static BlockItem register(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }

    public static <T extends Item> T register(String id, T item) {
        return Registry.register(Registries.ITEM, Identifier.of(AstralExpansion.MOD_ID, id), item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MAIN).register(entries -> {

        });
    }
}
