package pixelmon;

import java.util.Random;

import pixelmon.config.PixelmonBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenWaterStoneOre extends WorldGenerator{
	
	public boolean generate(World world, Random rand, int x, int y, int z){
		if((world.getBlockId(x, y+1, z) == Block.waterStill.blockID)
				&& (world.getBlockId(x, y - 1, z) != Block.waterStill.blockID && 
				WorldHelper.getWaterDepth(x, y+1, z, world) > 4)){
			world.setBlockWithNotify(x, y, z, PixelmonBlocks.waterStoneOre.blockID);
			return true;
		}
		return false;
	}

}
