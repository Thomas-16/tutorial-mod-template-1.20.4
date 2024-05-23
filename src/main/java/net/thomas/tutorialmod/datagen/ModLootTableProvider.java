package net.thomas.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.thomas.tutorialmod.block.ModBlocks;
import net.thomas.tutorialmod.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CHAEWON_BLOCK);
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.ARTHUR_BLOCK_ONE);
        addDrop(ModBlocks.ARTHUR_BLOCK_TWO);
        addDrop(ModBlocks.RAW_RUBY_BLOCK);
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, copperLikeOreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY, 3f, 4f));
        addDrop(ModBlocks.RUBY_ORE, copperLikeOreDrops(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY, 2f, 4f));
        addDrop(ModBlocks.NETHER_RUBY_ORE, copperLikeOreDrops(ModBlocks.NETHER_RUBY_ORE, ModItems.RAW_RUBY, 2f, 3f));
        addDrop(ModBlocks.END_STONE_RUBY_ORE, copperLikeOreDrops(ModBlocks.END_STONE_RUBY_ORE, ModItems.RAW_RUBY, 4f, 8f));
        addDrop(ModBlocks.SOUND_BLOCK);
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item, float minDrop, float maxDrop) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop,
                (LootPoolEntry.Builder)this.applyExplosionDecay(drop, ((LeafEntry.Builder) ItemEntry.builder(item)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrop, maxDrop))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
