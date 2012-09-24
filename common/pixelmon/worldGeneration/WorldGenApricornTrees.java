package pixelmon.worldGeneration;

import java.util.Random;

import pixelmon.WorldHelper;
import pixelmon.config.ApricornTreeBlocks;
import pixelmon.config.PixelmonBlocks;
import pixelmon.enums.EnumBiomes;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenApricornTrees extends WorldGenerator implements IWorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if(world.getBlockId(x, y, z) == Block.grass.blockID){
			world.setBlockWithNotify(x, y+1, z, ApricornTreeBlocks.apricornTreeRed.blockID);
			return true;
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.getBiomeGenForCoords(chunkX, chunkZ) == EnumBiomes.Forest.getBiome()) {
			for (int i = 0; i < 2; i++) {
				int xPos = random.nextInt(16) + chunkX;
				int zPos = random.nextInt(16) + chunkZ;
				int y = world.getHeightValue(chunkX, chunkZ);

				generate(world, random, xPos, y, zPos);
			}
		}

	}
}
