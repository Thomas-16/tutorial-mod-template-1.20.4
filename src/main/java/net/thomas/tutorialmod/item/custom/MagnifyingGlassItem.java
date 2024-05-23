package net.thomas.tutorialmod.item.custom;

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
import net.thomas.tutorialmod.ModdingUtils;
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
            HitResult hit = ModdingUtils.cameraRayCast(1000, true);
            int maxDistance = 200;
            if (hit.getType() != HitResult.Type.MISS && hit.squaredDistanceTo(user) < Math.pow(maxDistance, 2)) {
                Vec3d hitPos = hit.getPos();
                BlockPos hitBlockPos = ((BlockHitResult) hit).getBlockPos();

                user.sendMessage(Text.literal(world.getBlockState(hitBlockPos).getFluidState().toString()));
            }
        }

        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("testing tool that prints info on a block"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
