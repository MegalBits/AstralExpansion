package net.megal;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.megal.block.ModBlocks;
import net.megal.client.render.entity.ModEntityRenderers;
import net.megal.enchantment.ModEnchantments;
import net.megal.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstralExpansionClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityRenderers.initialize();
	}
}