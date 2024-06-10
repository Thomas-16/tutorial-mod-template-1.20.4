package net.thomas.tutorialmod.command.custom;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thomas.tutorialmod.util.ModdingUtils;
import net.thomas.tutorialmod.util.Stopwatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.server.command.CommandManager.argument;

public class BlockDataSavingCommands {
    private static final Block[] blockLookupArr = {Blocks.STONE
		, Blocks.GRANITE
        , Blocks.POLISHED_GRANITE
        , Blocks.DIORITE
        , Blocks.POLISHED_DIORITE
        , Blocks.ANDESITE
        , Blocks.POLISHED_ANDESITE
        , Blocks.DEEPSLATE
        , Blocks.COBBLED_DEEPSLATE
        , Blocks.POLISHED_DEEPSLATE
        , Blocks.CALCITE
        , Blocks.COBBLESTONE
        , Blocks.OAK_PLANKS
        , Blocks.SPRUCE_PLANKS
        , Blocks.BIRCH_PLANKS
        , Blocks.JUNGLE_PLANKS
        , Blocks.ACACIA_PLANKS
        , Blocks.CHERRY_PLANKS
        , Blocks.DARK_OAK_PLANKS
        , Blocks.MANGROVE_PLANKS
        , Blocks.BAMBOO_PLANKS
        , Blocks.CRIMSON_PLANKS
        , Blocks.WARPED_PLANKS
        , Blocks.BAMBOO_MOSAIC
        , Blocks.COAL_ORE
        , Blocks.DEEPSLATE_COAL_ORE
        , Blocks.IRON_ORE
        , Blocks.DEEPSLATE_IRON_ORE
        , Blocks.COPPER_ORE
        , Blocks.DEEPSLATE_COPPER_ORE
        , Blocks.GOLD_ORE
        , Blocks.DEEPSLATE_GOLD_ORE
        , Blocks.EMERALD_ORE
        , Blocks.DEEPSLATE_EMERALD_ORE
        , Blocks.LAPIS_ORE
        , Blocks.DEEPSLATE_LAPIS_ORE
        , Blocks.DIAMOND_ORE
        , Blocks.DEEPSLATE_DIAMOND_ORE
        , Blocks.NETHER_GOLD_ORE
        , Blocks.NETHER_QUARTZ_ORE
        , Blocks.ANCIENT_DEBRIS
        , Blocks.COAL_BLOCK
        , Blocks.RAW_IRON_BLOCK
        , Blocks.RAW_COPPER_BLOCK
        , Blocks.RAW_GOLD_BLOCK
        , Blocks.AMETHYST_BLOCK
        , Blocks.IRON_BLOCK
        , Blocks.BLAST_FURNACE
        , Blocks.GOLD_BLOCK
        , Blocks.DIAMOND_BLOCK
        , Blocks.NETHERITE_BLOCK
        , Blocks.WAXED_COPPER_BLOCK
        , Blocks.WAXED_EXPOSED_COPPER
        , Blocks.WAXED_WEATHERED_COPPER
        , Blocks.WAXED_OXIDIZED_COPPER
        , Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS
        , Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB
        , Blocks.OAK_LOG
        , Blocks.SPRUCE_LOG
        , Blocks.BIRCH_LOG
        , Blocks.JUNGLE_LOG
        , Blocks.ACACIA_LOG
        , Blocks.CHERRY_LOG
        , Blocks.DARK_OAK_LOG
        , Blocks.MANGROVE_LOG
        , Blocks.GLASS
        , Blocks.LAPIS_BLOCK
        , Blocks.SANDSTONE
        , Blocks.CHISELED_SANDSTONE
        , Blocks.CUT_SANDSTONE
        , Blocks.NETHER_WART_BLOCK
        , Blocks.WHITE_WOOL
        , Blocks.ORANGE_WOOL
        , Blocks.MAGENTA_WOOL
        , Blocks.LIGHT_BLUE_WOOL
        , Blocks.YELLOW_WOOL
        , Blocks.LIME_WOOL
        , Blocks.PINK_WOOL
        , Blocks.GRAY_WOOL
        , Blocks.LIGHT_GRAY_WOOL
        , Blocks.CYAN_WOOL
        , Blocks.PURPLE_WOOL
        , Blocks.BLUE_WOOL
        , Blocks.BROWN_WOOL
        , Blocks.GREEN_WOOL
        , Blocks.RED_WOOL
        , Blocks.BLACK_WOOL
        , Blocks.OAK_SLAB
        , Blocks.SPRUCE_SLAB
        , Blocks.BIRCH_SLAB
        , Blocks.JUNGLE_SLAB
        , Blocks.ACACIA_SLAB
        , Blocks.CHERRY_SLAB
        , Blocks.DARK_OAK_SLAB
        , Blocks.MANGROVE_SLAB
        , Blocks.BAMBOO_SLAB
        , Blocks.BAMBOO_MOSAIC_SLAB
        , Blocks.CRIMSON_SLAB
        , Blocks.WARPED_SLAB
        , Blocks.STONE_SLAB
        , Blocks.SMOOTH_STONE_SLAB
        , Blocks.SANDSTONE_SLAB
        , Blocks.CUT_SANDSTONE_SLAB
        , Blocks.PETRIFIED_OAK_SLAB
        , Blocks.COBBLESTONE_SLAB
        , Blocks.BRICK_SLAB
        , Blocks.STONE_BRICK_SLAB
        , Blocks.MUD_BRICK_SLAB
        , Blocks.NETHER_BRICK_SLAB
        , Blocks.QUARTZ_SLAB
        , Blocks.RED_SANDSTONE_SLAB
        , Blocks.CUT_RED_SANDSTONE_SLAB
        , Blocks.PRISMARINE_SLAB
        , Blocks.PRISMARINE_BRICK_SLAB
        , Blocks.DARK_PRISMARINE_SLAB
        , Blocks.SMOOTH_QUARTZ
        , Blocks.BRICKS
        , Blocks.BOOKSHELF
        , Blocks.CHISELED_BOOKSHELF
        , Blocks.MOSSY_COBBLESTONE
        , Blocks.OBSIDIAN
        , Blocks.BONE_BLOCK
        , Blocks.SPAWNER
        , Blocks.CHEST
        , Blocks.CRAFTING_TABLE
        , Blocks.FURNACE
        , Blocks.COBBLESTONE_STAIRS
        , Blocks.SNOW_BLOCK
        , Blocks.CLAY
        , Blocks.JUKEBOX
        , Blocks.OAK_FENCE
        , Blocks.SPRUCE_FENCE
        , Blocks.BIRCH_FENCE
        , Blocks.JUNGLE_FENCE
        , Blocks.ACACIA_FENCE
        , Blocks.CHERRY_FENCE
        , Blocks.DARK_OAK_FENCE
        , Blocks.MANGROVE_FENCE
        , Blocks.BAMBOO_FENCE
        , Blocks.CRIMSON_FENCE
        , Blocks.WARPED_FENCE
        , Blocks.PUMPKIN
        , Blocks.CARVED_PUMPKIN
        , Blocks.JACK_O_LANTERN
        , Blocks.NETHERRACK
        , Blocks.SOUL_SAND
        , Blocks.SOUL_SOIL
        , Blocks.BASALT
        , Blocks.POLISHED_BASALT
        , Blocks.SMOOTH_BASALT
        , Blocks.GLOWSTONE
        , Blocks.STONE_BRICKS
        , Blocks.MOSSY_STONE_BRICKS
        , Blocks.CRACKED_STONE_BRICKS
        , Blocks.CHISELED_STONE_BRICKS
        , Blocks.MUD_BRICKS
        , Blocks.DEEPSLATE_BRICKS
        , Blocks.CRACKED_DEEPSLATE_BRICKS
        , Blocks.DEEPSLATE_TILES
        , Blocks.CRACKED_DEEPSLATE_TILES
        , Blocks.CHISELED_DEEPSLATE
        , Blocks.REINFORCED_DEEPSLATE
        , Blocks.BROWN_MUSHROOM_BLOCK
        , Blocks.RED_MUSHROOM_BLOCK
        , Blocks.MUSHROOM_STEM
        , Blocks.IRON_BARS
        , Blocks.GLASS_PANE
        , Blocks.MELON
        , Blocks.BRICK_STAIRS
        , Blocks.STONE_BRICK_STAIRS
        , Blocks.MUD_BRICK_STAIRS
        , Blocks.NETHER_BRICKS
        , Blocks.CRACKED_NETHER_BRICKS
        , Blocks.CHISELED_NETHER_BRICKS
        , Blocks.NETHER_BRICK_FENCE
        , Blocks.NETHER_BRICK_STAIRS
        , Blocks.ENCHANTING_TABLE
        , Blocks.END_PORTAL_FRAME
        , Blocks.END_STONE
        , Blocks.END_STONE_BRICKS
        , Blocks.SANDSTONE_STAIRS
        , Blocks.ENDER_CHEST
        , Blocks.EMERALD_BLOCK
        , Blocks.OAK_STAIRS
        , Blocks.SPRUCE_STAIRS
        , Blocks.BIRCH_STAIRS
        , Blocks.JUNGLE_STAIRS
        , Blocks.ACACIA_STAIRS
        , Blocks.CHERRY_STAIRS
        , Blocks.DARK_OAK_STAIRS
        , Blocks.MANGROVE_STAIRS
        , Blocks.BAMBOO_STAIRS
        , Blocks.BAMBOO_MOSAIC_STAIRS
        , Blocks.CRIMSON_STAIRS
        , Blocks.WARPED_STAIRS
        , Blocks.COMMAND_BLOCK
        , Blocks.BEACON
        , Blocks.COBBLESTONE_WALL
        , Blocks.MOSSY_COBBLESTONE_WALL
        , Blocks.BRICK_WALL
        , Blocks.PRISMARINE_WALL
        , Blocks.RED_SANDSTONE_WALL
        , Blocks.MOSSY_STONE_BRICK_WALL
        , Blocks.GRANITE_WALL
        , Blocks.STONE_BRICK_WALL
        , Blocks.MUD_BRICK_WALL
        , Blocks.NETHER_BRICK_WALL
        , Blocks.ANDESITE_WALL
        , Blocks.RED_NETHER_BRICK_WALL
        , Blocks.SANDSTONE_WALL
        , Blocks.END_STONE_BRICK_WALL
        , Blocks.DIORITE_WALL
        , Blocks.BLACKSTONE_WALL
        , Blocks.POLISHED_BLACKSTONE_WALL
        , Blocks.POLISHED_BLACKSTONE_BRICK_WALL
        , Blocks.COBBLED_DEEPSLATE_WALL
        , Blocks.POLISHED_DEEPSLATE_WALL
        , Blocks.DEEPSLATE_BRICK_WALL
        , Blocks.DEEPSLATE_TILE_WALL
        , Blocks.CHISELED_QUARTZ_BLOCK
        , Blocks.QUARTZ_BLOCK
        , Blocks.QUARTZ_BRICKS
        , Blocks.QUARTZ_PILLAR
        , Blocks.QUARTZ_STAIRS
        , Blocks.WHITE_TERRACOTTA
        , Blocks.ORANGE_TERRACOTTA
        , Blocks.MAGENTA_TERRACOTTA
        , Blocks.LIGHT_BLUE_TERRACOTTA
        , Blocks.YELLOW_TERRACOTTA
        , Blocks.LIME_TERRACOTTA
        , Blocks.PINK_TERRACOTTA
        , Blocks.GRAY_TERRACOTTA
        , Blocks.LIGHT_GRAY_TERRACOTTA
        , Blocks.CYAN_TERRACOTTA
        , Blocks.PURPLE_TERRACOTTA
        , Blocks.BLUE_TERRACOTTA
        , Blocks.BROWN_TERRACOTTA
        , Blocks.GREEN_TERRACOTTA
        , Blocks.RED_TERRACOTTA
        , Blocks.BLACK_TERRACOTTA
        , Blocks.PACKED_ICE
        , Blocks.WHITE_STAINED_GLASS
        , Blocks.ORANGE_STAINED_GLASS
        , Blocks.MAGENTA_STAINED_GLASS
        , Blocks.LIGHT_BLUE_STAINED_GLASS
        , Blocks.YELLOW_STAINED_GLASS
        , Blocks.LIME_STAINED_GLASS
        , Blocks.PINK_STAINED_GLASS
        , Blocks.GRAY_STAINED_GLASS
        , Blocks.LIGHT_GRAY_STAINED_GLASS
        , Blocks.CYAN_STAINED_GLASS
        , Blocks.PURPLE_STAINED_GLASS
        , Blocks.BLUE_STAINED_GLASS
        , Blocks.BROWN_STAINED_GLASS
        , Blocks.GREEN_STAINED_GLASS
        , Blocks.RED_STAINED_GLASS};

	private static Block[] loadedBlocks = null;

    public static void registerGenerateBlocksCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("generateblocks")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("path", StringArgumentType.greedyString())
				.executes(context -> onGenerateBlocksCalled(StringArgumentType.getString(context, "path"), context)))));
    }
	public static void registerDecodeBlocksCommand() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("decodeblocks")
				.requires(source -> source.hasPermissionLevel(2))
				.then(CommandManager.literal("load")
						.executes(context -> onLoadBlocksCalled(context)))
				.then(CommandManager.literal("tofile")
						.then(argument("path", StringArgumentType.greedyString())
								.executes(context -> onToFileCalled(StringArgumentType.getString(context, "path"), context))))
				.then(CommandManager.literal("isloaded")
						.executes(context -> onIsLoadedCalled(context)))
				.then(CommandManager.literal("clearloaded")
						.executes(context -> onClearLoadedCalled(context)))
		));

	}
	private static int onLoadBlocksCalled(CommandContext<ServerCommandSource> context) {
		World world = context.getSource().getWorld();
		List<Block> loadedBlocks = new ArrayList<>();
		BlockPos startingPos = ((BlockHitResult) ModdingUtils.playerReachRayCast()).getBlockPos();
		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();

		int xLength = 0;
		int yLength = 0;
		int zLength = 0;
		while(!world.getBlockState(startingPos.add(xLength, 0, 0)).isAir() || doesLookUpArrContainOrIsBedrock(world.getBlockState(startingPos.add(xLength, 0, 0)).getBlock())) {
			xLength++;
		}
		while(!world.getBlockState(startingPos.add(0, yLength, 0)).isAir() || doesLookUpArrContainOrIsBedrock(world.getBlockState(startingPos.add(0, yLength, 0)).getBlock())) {
			yLength++;
		}
		while(!world.getBlockState(startingPos.add(0, 0, zLength)).isAir() || doesLookUpArrContainOrIsBedrock(world.getBlockState(startingPos.add(0, 0, zLength)).getBlock())) {
			zLength++;
		}
		outerloop:
		for(int yOffset = 0; yOffset < yLength; yOffset++){
			for(int zOffset = 0; zOffset < zLength; zOffset++){
				for(int xOffset = 0; xOffset < xLength; xOffset++){
					if(yOffset == yLength - 1 && (world.getBlockState(startingPos.add(xOffset, yOffset, zOffset)).isAir()
							|| !doesLookUpArrContainOrIsBedrock(world.getBlockState(startingPos.add(xOffset, yOffset, zOffset)).getBlock()))) {
						break outerloop;
					}
					loadedBlocks.add(world.getBlockState(startingPos.add(xOffset, yOffset, zOffset)).getBlock());
				}
			}
		}

		stopwatch.stop();
		context.getSource().sendFeedback(() -> Text.literal(loadedBlocks.size() + " blocks loaded in " + formatNanoSeconds(stopwatch.getElapsedTime())), true);
		BlockDataSavingCommands.loadedBlocks = loadedBlocks.toArray(new Block[0]);

		return 1;
	}
	private static int onToFileCalled(String pathString, CommandContext<ServerCommandSource> context){
		if(loadedBlocks == null) {
			context.getSource().sendFeedback(() -> Text.literal("no blocks loaded"), true);
			return 0;
		}
		Path folderPath = Paths.get(pathString);
		if(!Files.isDirectory(folderPath)) {
			context.getSource().sendFeedback(() -> Text.literal("path doesn't lead to a valid directory"), true);
			return 0;
		}
		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();

		Pair<List<Byte>, String> data = convertBlocksToByteListAndFileFormat(loadedBlocks);
		List<Byte> byteList = data.getFirst();
		String fileFormat = data.getSecond();
		String outputFileName = "decoded." + fileFormat;

		byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }

        // Create the output directory if it doesn't exist
        File directory = new File(pathString);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // Create the full path for the output file
        File outputFile = new File(directory, outputFileName);
        // Write the byte array to the specified file
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(byteArray);
			stopwatch.stop();
			context.getSource().sendFeedback(() -> Text.literal(byteList.size() + " bytes written to file successfully at: " + outputFile.getAbsolutePath() +
					" in " + formatNanoSeconds(stopwatch.getElapsedTime())), true);
        } catch (IOException e) {
            e.printStackTrace();
			context.getSource().sendFeedback(() -> Text.literal("Couldn't write file successfully"), true);
			return 0;
        }
		return 1;
	}
	private static int onIsLoadedCalled(CommandContext<ServerCommandSource> context) {
		if(loadedBlocks != null){
			context.getSource().sendFeedback(() -> Text.literal(loadedBlocks.length + " blocks loaded"), true);
		} else {
			context.getSource().sendFeedback(() -> Text.literal("nothing loaded"), true);
		}
		return 1;
	}
	private static int onClearLoadedCalled(CommandContext<ServerCommandSource> context) {
		loadedBlocks = null;
		context.getSource().sendFeedback(() -> Text.literal("cleared loaded blocks"), true);
		return 1;
	}
    private static int onGenerateBlocksCalled(String pathString, CommandContext<ServerCommandSource> context){
		Path path = Paths.get(pathString);
		World world = context.getSource().getWorld();
		PlayerEntity player = context.getSource().getPlayer();

		if(Files.exists(path) && Files.isRegularFile(path)){
			context.getSource().sendFeedback(() -> Text.literal("file found at \"" + path + "\""), true);

			Stopwatch stopwatch = new Stopwatch();
			stopwatch.start();

			Pair<byte[], String> data = loadBinaryData(pathString);
			byte[] bytes = data.getFirst();
			String fileFormat = data.getSecond();
			BlockPos startingPos = player.getBlockPos().add(2, 0, 2);
			BlockPos currentPos = startingPos;
			int totalLength = bytes.length + 1 + fileFormat.length();

			// Builds a n by n by n cube
			int maxYLength = 319 - startingPos.getY();
			int yLength, zLength, xLength;
			yLength = (int) Math.ceil(Math.cbrt(totalLength));
			if(yLength > maxYLength) {
				xLength = yLength;
				yLength = maxYLength;
				zLength = (int) Math.ceil(totalLength / (double)xLength / (double)yLength);
			} else {
				zLength = yLength;
				xLength = zLength;
			}

			int index = 0;
			outerloop:
			for(int yOffset = 0; yOffset < yLength; yOffset++){
				for(int zOffset = 0; zOffset < zLength; zOffset++){
					for(int xOffset = 0; xOffset < xLength; xOffset++){
						currentPos = startingPos.add(xOffset, yOffset, zOffset);
						if(index < bytes.length) {
							world.setBlockState(currentPos, blockLookupArr[Byte.toUnsignedInt(bytes[index])].getDefaultState());
						}
						else if(index == bytes.length) {
							world.setBlockState(currentPos, Blocks.BEDROCK.getDefaultState());
						} else if(index > bytes.length && index < totalLength) {
							world.setBlockState(currentPos, blockLookupArr[(int) fileFormat.charAt(index - bytes.length - 1)].getDefaultState());
						} else {
							break outerloop;
						}
						index++;
					}
				}
			}
			stopwatch.stop();
			context.getSource().sendFeedback(() -> Text.literal(totalLength + " blocks placed in " + formatNanoSeconds(stopwatch.getElapsedTime())), true);

			return 1;
		}else{
			context.getSource().sendFeedback(() -> Text.literal("no valid file found at \"" + path + "\""), true);
			return 0;
		}
	}
	private static Pair<List<Byte>, String> convertBlocksToByteListAndFileFormat(Block[] blocks){
		List<Byte> byteList = new ArrayList<>();
		boolean isFileFormat = false;
		String fileFormat = "";
		for(Block block : blocks) {
			if(block.getDefaultState().isOf(Blocks.BEDROCK)) {
				isFileFormat = true;
				continue;
			}
			if(!isFileFormat) {
				byteList.add((byte) indexOfBlock(block));
			} else {
				fileFormat += (char) indexOfBlock(block);
			}
		}
		return new Pair<List<Byte>, String>(byteList, fileFormat);
	}
	private static Pair<byte[], String> loadBinaryData(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			// Extract the file format from the file path
			String fileFormat = "";
			int dotIndex = filePath.lastIndexOf('.');
			if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
				fileFormat = filePath.substring(dotIndex + 1);
			}

            byte[] data = new byte[fileInputStream.available()];
            int bytesRead = fileInputStream.read(data);

            if (bytesRead == data.length) {
                return new Pair<byte[], String>(data, fileFormat);
            } else {
                System.err.println("Could not read the entire file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	private static int indexOfBlock(Block block) {
		for(int i = 0; i < blockLookupArr.length; i++) {
			if(blockLookupArr[i].getDefaultState().isOf(block)) {
				return i;
			}
		}
		return -1;
	}
	private static boolean doesLookUpArrContainOrIsBedrock(Block block){
		for (Block block1 : blockLookupArr){
			if(block1.getDefaultState().isOf(block) || block.getDefaultState().isOf(Blocks.BEDROCK)) return true;
		}
		return false;
	}
	private static String formatNanoSeconds(long nanoSeconds) {
        double totalSeconds = nanoSeconds / 1_000_000_000.0;
        int hours = (int) (totalSeconds / 3600);
        totalSeconds %= 3600;
        int minutes = (int) (totalSeconds / 60);
        totalSeconds %= 60;

        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours).append(hours == 1 ? " hour " : " hours ");
        }
        if (minutes > 0) {
            sb.append(minutes).append(minutes == 1 ? " minute " : " minutes ");
        }
        if (totalSeconds > 0 || sb.length() == 0) {
            sb.append(String.format("%.2f seconds", totalSeconds));
        }
        return sb.toString().trim();
    }

}
class Pair<U, V> {
	/**
	 * The first element of this <code>Pair</code>
	 */
	private U first;

	/**
	 * The second element of this <code>Pair</code>
	 */
	private V second;

	/**
	 * Constructs a new <code>Pair</code> with the given values.
	 *
	 * @param first  the first element
	 * @param second the second element
	 */
	public Pair(U first, V second) {

		this.first = first;
		this.second = second;
	}
	/**
	 * Returns the first element
	 */
	public U getFirst() {
		return first;
	}
	/**
	 * Returns the second element
	 */
	public V getSecond() {
		return second;
	}
}