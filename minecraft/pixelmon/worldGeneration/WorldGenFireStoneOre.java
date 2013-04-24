package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenFireStoneOre extends WorldGenerator implements
		IWorldGenerator {

	public boolean generate(World world, Random rand, int x, int none, int z) {
		int y = getTopLavaBlock(world, x, z);
		if (world.getBlockMaterial(x, y + 1, z) == Material.air) {
			y += 2;
			while (world.getBlockMaterial(x, y, z) == Material.air)
				y++;

			world.setBlock(x, y, z, PixelmonBlocks.fireStoneOre.blockID, 0, 0);
			return true;
		}

		return false;
	}

	private int getTopLavaBlock(World world, int x, int z) {
		int top = -1;
		for (int i = 0; i < 40; i++) {
			if (world.getBlockId(x, i, z) == Block.lavaStill.blockID)
				top = i;
			else if (top != -1)
				break;
		}
		return top;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 15; i++) {
			int xPos = random.nextInt(16) + chunkX * 16;
			int zPos = random.nextInt(16) + chunkZ * 16;
			if (xzHasLava(world, xPos, zPos))
				generate(world, random, xPos, 0, zPos);
		}
	}

	private boolean xzHasLava(World world, int x, int z) {
		for (int i = 0; i < 30; i++) {
			if (world.getBlockId(x, i, z) == Block.lavaStill.blockID)
				return true;
		}
		return false;
	}

}
