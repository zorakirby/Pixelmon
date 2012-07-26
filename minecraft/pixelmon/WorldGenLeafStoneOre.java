package pixelmon;

import java.util.Random;

import pixelmon.blocks.PixelmonBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.mod_Pixelmon;

public class WorldGenLeafStoneOre extends WorldGenerator{
	
	public boolean generate(World world, Random rand, int x, int y, int z){
		int leaf = Block.leaves.blockID;
		int log = Block.wood.blockID;
		if(world.getBlockId(x, y, z) != leaf){
			return false;
		}
		world.setBlockWithNotify(x, y, z, PixelmonBlocks.leafStoneOre.blockID);
		return true;
	}

}
