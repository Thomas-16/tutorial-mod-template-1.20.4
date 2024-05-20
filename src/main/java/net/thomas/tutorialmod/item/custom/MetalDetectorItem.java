package net.thomas.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;
            ArrayList<Map.Entry<BlockState, Integer>> foundBlocks = new ArrayList<>();

            int searchRadius = 8;
            int searchRadiusSquared = searchRadius * searchRadius;
            for (int x = positionClicked.getX() - searchRadius; x <= positionClicked.getX() + searchRadius; x++) {
                for (int y = positionClicked.getY() - searchRadius; y <= positionClicked.getY() + searchRadius; y++) {
                    for (int z = positionClicked.getZ() - searchRadius; z <= positionClicked.getZ() + searchRadius; z++) {
                        // Calculate the distance squared from the center point
                        int dx = x - positionClicked.getX();
                        int dy = y - positionClicked.getY();
                        int dz = z - positionClicked.getZ();
                        int distanceSquared = dx * dx + dy * dy + dz * dz;

                        // Check if the block is within the half sphere
                        if (distanceSquared <= searchRadiusSquared) {
                            BlockPos blockPos = new BlockPos(x, y, z);
                            BlockState state = context.getWorld().getBlockState(blockPos);

                            if (isBlockValuable(state)) {
                                if(tupleListContainsElement(foundBlocks, state)){
                                    for(int i = 0; i < foundBlocks.size(); i++){
                                        if(foundBlocks.get(i).getKey().getBlock().getName().getString().equals(state.getBlock().getName().getString())){
                                            foundBlocks.get(i).setValue(foundBlocks.get(i).getValue() + 1);
                                            break;
                                        }
                                    }
                                }else{
                                    foundBlocks.add(new AbstractMap.SimpleEntry<>(state, 1));
                                }
                                foundBlock = true;
                            }
                            else {
//                                context.getWorld().removeBlock(blockPos, state.isLiquid() || state.blocksMovement());
                                context.getWorld().setBlockState(blockPos, Blocks.AIR.getDefaultState());
//                                runVanillaCommand(player, "setblock " + blockPos.getX() + " " + blockPos.getY() + " " + blockPos.getZ() + " air");
                            }
                        }
                    }
                }
            }
            if (!foundBlock) {
                player.sendMessage(Text.literal("No valuables found!"));
            } else {
                int count = 0;
                for(Map.Entry<BlockState, Integer> entry : foundBlocks){
                    count += entry.getValue();
                }
                player.sendMessage(Text.literal("Found " + count + " valuable blocks!"));
                for (Map.Entry<BlockState, Integer> entry : foundBlocks) {
                    player.sendMessage(Text.literal("\u2022" + entry.getValue() +" "+entry.getKey().getBlock().getName().getString() + " found!"));
                }
            }
            context.getStack().damage(1, context.getPlayer(), playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        }

        return ActionResult.SUCCESS;
    }
    private boolean isBlockValuable(BlockState state) {
        return state.getBlock().getName().getString().toUpperCase().contains("ORE");
    }
    private boolean tupleListContainsElement(ArrayList<Map.Entry<BlockState, Integer>> list, BlockState state){
        for(Map.Entry<BlockState, Integer> entry : list){
            if(entry.getKey().getBlock().getName().getString().equals(state.getBlock().getName().getString())){
                return true;
            }
        }
        return false;
    }
    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("\u2022" + block.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }
}
