package pixelmon.worldGeneration;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import pixelmon.WorldHelper;
import pixelmon.config.PixelmonBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenWaterStoneOre extends WorldGenerator implements IWorldGenerator{
	
	public boolean generate(World world, Random rand, int x, int y, int z){
		if((world.getBlockId(x, y+1, z) == Block.waterStill.blockID)
				&& (world.getBlockId(x, y - 1, z) != Block.waterStill.blockID && 
				WorldHelper.getWaterDepth(x, y+1, z, world) > 4)){
			world.setBlockWithNotify(x, y, z, PixelmonBlocks.waterStoneOre.blockID);
			return true;
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 10; i++) {
			int xPos = random.nextInt(16) + chunkX;
			int zPos = random.nextInt(16) + chunkZ;
			int yPos = random.nextInt(40) + 40;
			new WorldGenWaterStoneOre().generate(world, random, xPos, yPos, zPos);
		}
	}

}
