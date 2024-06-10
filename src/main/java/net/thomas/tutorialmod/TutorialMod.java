package net.thomas.tutorialmod;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.thomas.tutorialmod.block.ModBlocks;
import net.thomas.tutorialmod.command.ModCommands;
import net.thomas.tutorialmod.item.ModItemGroups;
import net.thomas.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.minecraft.server.command.CommandManager.*;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorial-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModCommands.registerModCommands();

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);


	}
}