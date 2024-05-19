package net.thomas.tutorialmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.thomas.tutorialmod.TutorialMod;

public class ModBlocks {
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(
                    Blocks.register("ruby_block", new Block(FabricBlockSettings.create().mapColor(MapColor.RED).
                            instrument(Instrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL))))));
    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block",
            new Block(FabricBlockSettings.copyOf(
                    Blocks.register("raw_ruby_block", new Block(FabricBlockSettings.create().mapColor(MapColor.RED).
                            instrument(Instrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL))))));
    public static final Block CHAEWON_BLOCK = registerBlock("chaewon_block",
            new Block(FabricBlockSettings.copyOf(
                    Blocks.register("chaewon_block", new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).
                            instrument(Instrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL))))));
    public static final Block ARTHUR_BLOCK_ONE = registerBlock("arthur_block_one",
            new Block(FabricBlockSettings.copyOf(
                    Blocks.register("arthur_block_one", new Block(FabricBlockSettings.create().mapColor(MapColor.YELLOW).
                            instrument(Instrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL))))));
    public static final Block ARTHUR_BLOCK_TWO = registerBlock("arthur_block_two",
            new Block(FabricBlockSettings.copyOf(
                    Blocks.register("arthur_block_two", new Block(FabricBlockSettings.create().mapColor(MapColor.YELLOW).
                            instrument(Instrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL))))));
    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    FabricBlockSettings.copyOf(Blocks.STONE).strength(2f)));
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
            FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f)));
    public static final Block NETHER_RUBY_ORE = registerBlock("nether_ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(1.5f)));
    public static final Block END_STONE_RUBY_ORE = registerBlock("end_stone_ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 7),
                    FabricBlockSettings.copyOf(Blocks.END_STONE).strength(4f)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TutorialMod.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        TutorialMod.LOGGER.info("Registering Mod Blocks for: " + TutorialMod.MOD_ID);
    }
}
