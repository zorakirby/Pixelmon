package pixelmon.worldGeneration;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import pixelmon.config.PixelmonBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.WorldGenerator;

public class WorldGenThunderStoneOre implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 30; i++) {
			int xPos = random.nextInt(16) + chunkX;
			int zPos = random.nextInt(16) + chunkZ;
			int yPos = random.nextInt(50) + 75; // generates 75 to 125
			new WorldGenMinable(PixelmonBlocks.thunderStoneOre.blockID, 2 + random.nextInt(2)).generate(world, random, xPos, yPos, zPos);
		}
	}

}
