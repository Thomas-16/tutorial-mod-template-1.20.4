package net.thomas.tutorialmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.ArrayList;

public class MegaBucketItem extends Item {
    private static final int MAX_BLOCKS_CLEARING = 50000;
    private boolean isClearingWater;
    private ArrayList<BlockPos> blocksToClear;

    public MegaBucketItem(Settings settings) {
        super(settings);
        isClearingWater = false;
        blocksToClear = new ArrayList<BlockPos>();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()){
            return;
        }
        if(isClearingWater) {
            for (BlockPos blockPos : blocksToClear) {
                if(!shouldBeCleared(world.getBlockState(blockPos))){
                    continue;
                }
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState());

            }
            int n = blocksToClear.size();
            for (int i = 0; i < n; i++) {
                if (shouldBeCleared(world.getBlockState(blocksToClear.get(i).up(1))) && !blocksToClear.contains(blocksToClear.get(i).up(1))) {
                    blocksToClear.add(blocksToClear.get(i).up(1));
                }
                if (shouldBeCleared(world.getBlockState(blocksToClear.get(i).down(1))) && !blocksToClear.contains(blocksToClear.get(i).down(1))) {
                    blocksToClear.add(blocksToClear.get(i).down(1));
                }
                if (shouldBeCleared(world.getBlockState(blocksToClear.get(i).north(1))) && !blocksToClear.contains(blocksToClear.get(i).north(1))) {
                    blocksToClear.add(blocksToClear.get(i).north(1));
                }
                if (shouldBeCleared(world.getBlockState(blocksToClear.get(i).south(1))) && !blocksToClear.contains(blocksToClear.get(i).south(1))) {
                    blocksToClear.add(blocksToClear.get(i).south(1));
                }
                if (shouldBeCleared(world.getBlockState(blocksToClear.get(i).west(1))) && !blocksToClear.contains(blocksToClear.get(i).west(1))) {
                    blocksToClear.add(blocksToClear.get(i).west(1));
                }
                if (shouldBeCleared(world.getBlockState(blocksToClear.get(i).east(1))) && !blocksToClear.contains(blocksToClear.get(i).east(1))) {
                    blocksToClear.add(blocksToClear.get(i).east(1));
                }
            }
            if(n == blocksToClear.size() || isAllAir(world)){
                entity.sendMessage(Text.literal("bro is moses"));
                blocksToClear.clear();
                isClearingWater = false;
            }
            if (blocksToClear.size() > MAX_BLOCKS_CLEARING) {
                entity.sendMessage(Text.literal("the red sea too big bru"));
                blocksToClear.clear();
                isClearingWater = false;
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient()) {
            BlockHitResult blockHit = BucketItem.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
            if (blockHit.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isLiquid()) {
//                    user.sendMessage(Text.literal("clicked water"));
                    if (!isClearingWater) {
                        user.sendMessage(Text.literal("parting the red sea"));
                        world.playSound(null, new BlockPos(user.getBlockPos()), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1f, 1f);
//                        Fluids.WATER.getBucketFillSound().ifPresent(sound -> user.playSound(sound, 1.0f, 1.0f));
                        startClearingWater(world, blockPos, user);
                        return TypedActionResult.success(itemStack);
                    } else {
                        return TypedActionResult.fail(itemStack);
                    }
                }
            }
//            user.sendMessage(Text.literal("didnt click water"));
        }
        return TypedActionResult.fail(itemStack);
    }
    private void startClearingWater(World world, BlockPos startingPos, PlayerEntity user) {
        blocksToClear.clear();
        isClearingWater = true;
        blocksToClear.add(startingPos);
    }
    private boolean isAllAir(World world){
        for(BlockPos blockPos : blocksToClear){
            if(shouldBeCleared(world.getBlockState(blockPos))){
                return false;
            }
        }
        return true;
    }

    private boolean shouldBeCleared(BlockState blockState) {
        return blockState.isLiquid() ||
                !blockState.getFluidState().isOf(Fluids.EMPTY);

    }
}
