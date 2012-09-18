package pixelmon.worldGeneration;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import pixelmon.WorldHelper;
import pixelmon.config.PixelmonBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenFireStoneOre extends WorldGenerator implements IWorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y + 1, z) == Block.lavaStill.blockID) {
			if (world.getBlockId(x, y, z) != Block.lavaStill.blockID) {
				world.setBlockWithNotify(x, y, z, PixelmonBlocks.fireStoneOre.blockID);
				System.out.println("Firestone spawned at " + x + " " + y + " " + z);
				return true;
			}
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 50; i++) {
			int xPos = random.nextInt(16) + chunkX;
			int zPos = random.nextInt(16) + chunkZ;
			int yPos = random.nextInt(20);
			generate(world, random, xPos, yPos, zPos);
		}
	}

}
