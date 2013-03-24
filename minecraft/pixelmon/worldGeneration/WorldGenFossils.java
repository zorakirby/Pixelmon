package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.WorldHelper;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenFossils extends WorldGenerator implements IWorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int waterY = rand.nextInt(4);
		if (world.getBlockId(x, y, z) == Block.gravel.blockID) {
			world.setBlock(x, y, z, PixelmonBlocks.fossil.blockID, 0, 0);
			System.out.println("Fossil spawned at " + x + " " + y + " " + z + " ");
			return true;
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 3; i++) {
			int xPos = random.nextInt(16) + chunkX * 16;
			int zPos = random.nextInt(16) + chunkZ * 16;
			int yPos = random.nextInt(30) + 20;
			if (generate(world, random, xPos, yPos - random.nextInt(9), zPos))
				break;
		}
	}

}
