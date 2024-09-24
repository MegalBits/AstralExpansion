package net.megal.block;

import net.megal.AstralExpansion;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    private static <T extends Block> T register(String id, T block) {
        return Registry.register(Registries.BLOCK, Identifier.of(AstralExpansion.MOD_ID, id), block);
    }

    public static void initialize() {}
}
