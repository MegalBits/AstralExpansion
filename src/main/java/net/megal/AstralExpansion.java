package net.megal;

import net.fabricmc.api.ModInitializer;

import net.megal.block.ModBlocks;
import net.megal.enchantment.ModEnchantments;
import net.megal.entity.ModEntityTypes;
import net.megal.item.ModItems;
import net.megal.registry.tag.ModItemTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstralExpansion implements ModInitializer {
	public static final String MOD_ID = "astral-expansion";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
		ModEntityTypes.initialize();
		ModEnchantments.initialize();
		ModItemTags.initialize();
	}
}