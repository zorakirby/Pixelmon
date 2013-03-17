package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenLeafStoneOre extends WorldGenerator implements IWorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int leaf = Block.leaves.blockID;
		int log = Block.wood.blockID;
		if (world.getBlockId(x, y, z) != leaf) {
			return false;
		}
		world.setBlockAndMetadataWithNotify(x, y, z, PixelmonBlocks.leafStoneOre.blockID, 0, 0);
		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 20; i++) {
			int xPos = random.nextInt(16) + chunkX * 16;
			int zPos = random.nextInt(16) + chunkZ * 16;
			int yPos = random.nextInt(100) + 28;
			generate(world, random, xPos, yPos, zPos);
		}
	}

}
