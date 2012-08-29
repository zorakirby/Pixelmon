package pixelmon.worldGeneration;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import pixelmon.config.PixelmonBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenLeafStoneOre extends WorldGenerator implements IWorldGenerator{
	
	public boolean generate(World world, Random rand, int x, int y, int z){
		int leaf = Block.leaves.blockID;
		int log = Block.wood.blockID;
		if(world.getBlockId(x, y, z) != leaf){
			return false;
		}
		world.setBlockWithNotify(x, y, z, PixelmonBlocks.leafStoneOre.blockID);
		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 20; i++) {
			int xPos = random.nextInt(16) + chunkX;
			int zPos = random.nextInt(16) + chunkZ;
			int yPos = random.nextInt(100) + 28;
			generate(world, random, xPos, yPos, zPos);
		}		
	}

}
