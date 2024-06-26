package net.thomas.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thomas.tutorialmod.TutorialMod;
import net.thomas.tutorialmod.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(TutorialMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.skibidi")).icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                entries.add(ModItems.RUBY);
                entries.add(ModItems.RAW_RUBY);
                entries.add(ModItems.METAL_DETECTOR);
                entries.add(ModItems.KABOOM);
                entries.add(ModItems.MEGA_BUCKET);
                entries.add(ModItems.MAGNIFYING_GLASS);
                entries.add(ModItems.TOMATO);
                entries.add(ModItems.COAL_BRIQUETTE);
                entries.add(ModItems.SATISFYING_ELYTRA);
                entries.add(ModItems.RUBY_STAFF);

                entries.add(ModBlocks.RUBY_BLOCK);
                entries.add(ModBlocks.RAW_RUBY_BLOCK);
                entries.add(ModBlocks.CHAEWON_BLOCK);
                entries.add(ModBlocks.ARTHUR_BLOCK_ONE);
                entries.add(ModBlocks.ARTHUR_BLOCK_TWO);
                entries.add(ModBlocks.RUBY_ORE);
                entries.add(ModBlocks.DEEPSLATE_RUBY_ORE);
                entries.add(ModBlocks.END_STONE_RUBY_ORE);
                entries.add(ModBlocks.NETHER_RUBY_ORE);
                entries.add(ModBlocks.SOUND_BLOCK);

                entries.add(ModBlocks.RUBY_STAIRS);
                entries.add(ModBlocks.RUBY_SLAB);
                entries.add(ModBlocks.RUBY_FENCE);
                entries.add(ModBlocks.RUBY_FENCE_GATE);
                entries.add(ModBlocks.RUBY_WALL);
                entries.add(ModBlocks.RUBY_BUTTON);
                entries.add(ModBlocks.RUBY_PRESSURE_PLATE);
                entries.add(ModBlocks.RUBY_DOOR);
                entries.add(ModBlocks.RUBY_TRAPDOOR);

            }).build());

    public static void registerItemGroups(){
        TutorialMod.LOGGER.info("Registering Item Groups for: " + TutorialMod.MOD_ID);
    }
}
