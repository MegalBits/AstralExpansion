package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.megal.AstralExpansion;
import net.megal.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    private ItemModelGenerator itemModelGenerator;
    private BlockStateModelGenerator blockStateModelGenerator;

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        this.blockStateModelGenerator = blockStateModelGenerator;
    }

    private void registerLadder(Block block) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(block);
        blockStateModelGenerator.registerItemModel(block);
        getBlockModel("template_ladder", TextureKey.TEXTURE).upload(block, TextureMap.texture(block), blockStateModelGenerator.modelCollector);
    }

    private void registerPillarTop(Block block) {
        Identifier identifier = Models.CUBE_COLUMN.upload(block, new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.END, TextureMap.getSubId(block, "_top")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private void registerPillarTopBottom(Block block) {
        Identifier identifier = Models.CUBE_BOTTOM_TOP.upload(block, new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private void registerSideTop(Block side, Block top) {
        Identifier identifier = Models.CUBE_COLUMN.upload(side, new TextureMap().put(TextureKey.SIDE, TextureMap.getId(side)).put(TextureKey.END, TextureMap.getId(top)), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(side, identifier));
    }

    private static Model getBlockModel(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(AstralExpansion.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model getVanillaBlockModel(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.ofVanilla("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        this.itemModelGenerator = itemModelGenerator;

        registerGeneratedModel(ModItems.EXPERIENCE_SIPHON);
    }

    private void registerModel(Model model, Item item) {
        itemModelGenerator.register(item, model);
    }

    private void registerToolset(Item sword, Item shovel, Item pickaxe, Item axe, Item hoe) {
        registerHandheldModel(sword);
        registerHandheldModel(shovel);
        registerHandheldModel(pickaxe);
        registerHandheldModel(axe);
        registerHandheldModel(hoe);
    }

    private void registerHandheldModel(Item item) {
        registerModel(Models.HANDHELD, item);
    }

    private void registerRodModel(Item item) {
        registerModel(Models.HANDHELD_ROD, item);
    }

    private void registerGeneratedModel(Item item) {
        registerModel(Models.GENERATED, item);
    }

    private static Model getItemModel(String id, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(AstralExpansion.MOD_ID, "item/" + id)), Optional.empty(), requiredTextureKeys);
    }

    private static Model getVanillaItemModel(String id, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.ofVanilla("item/" + id)), Optional.empty(), requiredTextureKeys);
    }
}