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
import net.thomas.tutorialmod.item.custom.*;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(64)));
    public static final Item KABOOM = registerItem("kaboom", new KaboomItem(new FabricItemSettings().maxDamage(128)));
    public static final Item MEGA_BUCKET = registerItem("mega_bucket", new MegaBucketItem(new FabricItemSettings().maxCount(1)));
    public static final Item MAGNIFYING_GLASS = registerItem("magnifying_glass", new MagnifyingGlassItem(new FabricItemSettings()));
    public static final Item TOMATO = registerItem("tomato", new Item(new FabricItemSettings().food(ModFoodComponents.TOMATO)));
    public static final Item COAL_BRIQUETTE = registerItem("coal_briquette", new Item(new FabricItemSettings()));
    public static final Item SATISFYING_ELYTRA = registerItem("satisfying_elytra", new SatisfyingElytraItem(new FabricItemSettings()));
    public static final Item RUBY_STAFF = registerItem("ruby_staff", new RubyStaffItem(new FabricItemSettings().maxDamage(16)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(RUBY);
        entries.add(RAW_RUBY);
        entries.add(TOMATO);
        entries.add(COAL_BRIQUETTE);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries){
        entries.add(METAL_DETECTOR);
        entries.add(KABOOM);
        entries.add(MEGA_BUCKET);
        entries.add(MAGNIFYING_GLASS);
        entries.add(SATISFYING_ELYTRA);
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
