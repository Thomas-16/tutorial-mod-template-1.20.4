package net.thomas.tutorialmod.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thomas.tutorialmod.util.ModdingUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KaboomItem extends Item {
    public KaboomItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient()) {
            HitResult hit = ModdingUtils.cameraRayCast(1000, false);
            int maxDistance = 200;
            if (hit.getType() != HitResult.Type.MISS && hit.squaredDistanceTo(user) < Math.pow(maxDistance, 2)) {
                Vec3d hitPos = hit.getPos();
                world.createExplosion(user, hitPos.getX(), hitPos.getY(), hitPos.getZ(), 10.0f, true, World.ExplosionSourceType.MOB);
                user.sendMessage(Text.literal("ALLAHU AKBARRR"));
            }
            itemStack.damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        }

        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("ALLAHU AKBAR TYPE SHI"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
