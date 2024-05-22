package net.thomas.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thomas.tutorialmod.TutorialMod;
import net.thomas.tutorialmod.item.custom.KaboomItem;
import net.thomas.tutorialmod.item.custom.MagnifyingGlassItem;
import net.thomas.tutorialmod.item.custom.MegaBucketItem;
import net.thomas.tutorialmod.item.custom.MetalDetectorItem;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(64)));
    public static final Item KABOOM = registerItem("kaboom", new KaboomItem(new FabricItemSettings().maxDamage(128)));
    public static final Item MEGA_BUCKET = registerItem("mega_bucket", new MegaBucketItem(new FabricItemSettings().maxCount(1)));
    public static final Item MAGNIFYING_GLASS = registerItem("magnifying_glass", new MagnifyingGlassItem(new FabricItemSettings()));
    public static final Item TOMATO = registerItem("tomato", new Item(new FabricItemSettings().food(ModFoodComponents.TOMATO)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(RUBY);
        entries.add(RAW_RUBY);
        entries.add(TOMATO);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries){
        entries.add(METAL_DETECTOR);
        entries.add(KABOOM);
        entries.add(MEGA_BUCKET);
        entries.add(MAGNIFYING_GLASS);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }
}
