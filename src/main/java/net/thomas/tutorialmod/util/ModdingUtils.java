package net.thomas.tutorialmod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.hit.HitResult;
import net.minecraft.client.MinecraftClient;
import java.util.Objects;

public class ModdingUtils {
    public static void runVanillaCommand(PlayerEntity player, String command) {
        CommandManager commandManager = Objects.requireNonNull(player.getServer()).getCommandManager();
        ServerCommandSource commandSource = player.getServer().getCommandSource();
        commandManager.executeWithPrefix(commandSource, command);
    }

    public static HitResult cameraRayCast(double maxReach, boolean includeFluids){
        MinecraftClient client = MinecraftClient.getInstance();
        return client.cameraEntity.raycast(maxReach, 1.0F, includeFluids);
    }
    public static HitResult playerReachRayCast(){
        MinecraftClient client = MinecraftClient.getInstance();
        return client.crosshairTarget;
    }
}
