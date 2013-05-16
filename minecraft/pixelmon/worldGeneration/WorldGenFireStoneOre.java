package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenFireStoneOre extends WorldGenerator implements IWorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z) {
		y = findTopLavaBlock(world, x, z);
		y++;
		if (world.getBlockMaterial(x, y, z) == Material.air) {
			for (int i = 1; i < 10; i++) {
				y++;
				if (world.getBlockMaterial(x, y, z) != Material.air){
					world.setBlock(x, y, z, PixelmonBlocks.fireStoneOre.blockID, 0, 0);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 20; i++) {
			int xPos = random.nextInt(16) + chunkX * 16;
			int zPos = random.nextInt(16) + chunkZ * 16;
			if (hasLava(world, xPos, zPos))
				generate(world, random, xPos, 0, zPos);
		}
	}

	private boolean hasLava(World world, int xPos, int zPos) {
		for (int i = 0; i < 30; i++) {
			if (world.getBlockId(xPos, i, zPos) == Block.lavaStill.blockID)
				return true;
		}
		return false;
	}

	private int findTopLavaBlock(World world, int x, int z) {
		for (int i = 0; i < 30; i++) {
			if (world.getBlockId(x, i, z) == Block.lavaStill.blockID)
				return i;
		}
		return -1;
	}

}
