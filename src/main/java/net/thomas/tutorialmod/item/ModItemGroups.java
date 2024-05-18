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
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.custom")).icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                entries.add(ModItems.RUBY);
                entries.add(ModItems.RAW_RUBY);

                entries.add(ModBlocks.RUBY_BLOCK);
                entries.add(ModBlocks.RAW_RUBY_BLOCK);
                entries.add(ModBlocks.CHAEWON_BLOCK);
                entries.add(ModBlocks.ARTHUR_BLOCK_ONE);
                entries.add(ModBlocks.ARTHUR_BLOCK_TWO);

            }).build());

    public static void registerItemGroups(){
        TutorialMod.LOGGER.info("Registering Item Groups for: " + TutorialMod.MOD_ID);
    }
}
