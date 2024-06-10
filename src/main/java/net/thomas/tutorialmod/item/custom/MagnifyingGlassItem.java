package net.thomas.tutorialmod.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thomas.tutorialmod.util.ModTags;
import net.thomas.tutorialmod.util.ModdingUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagnifyingGlassItem extends Item {
    public MagnifyingGlassItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient()) {
            BlockPos blockPos = ((BlockHitResult) ModdingUtils.playerReachRayCast()).getBlockPos();
            world.setBlockState(blockPos, Blocks.DIAMOND_BLOCK.getDefaultState());
        }

        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("testing tool that prints info on a block"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
