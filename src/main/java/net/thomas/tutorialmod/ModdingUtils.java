package net.thomas.tutorialmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
}
