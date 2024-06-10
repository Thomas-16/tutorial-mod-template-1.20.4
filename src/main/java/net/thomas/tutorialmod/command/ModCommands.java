package net.thomas.tutorialmod.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.thomas.tutorialmod.command.custom.BlockDataSavingCommands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static net.minecraft.server.command.CommandManager.argument;

public class ModCommands {

    public static void registerModCommands(){

		BlockDataSavingCommands.registerGenerateBlocksCommand();
        BlockDataSavingCommands.registerDecodeBlocksCommand();
    }

}
