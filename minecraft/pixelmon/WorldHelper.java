package pixelmon;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldHelper {
	public static int getWaterDepth(int posX,int posY, int posZ, World worldObj){
		int count=0;
		while (worldObj.getBlockId(posX, posY, posZ) == Block.waterStill.blockID){
			posY++;
			count++;
		}
		return count;
	}

	public static int getLavaDepth(int posX,int posY, int posZ, World worldObj){
		int count=0;
		while (worldObj.getBlockId(posX, posY, posZ) == Block.lavaStill.blockID){
			posY++;
			count++;
		}
		return count;
	}

}
