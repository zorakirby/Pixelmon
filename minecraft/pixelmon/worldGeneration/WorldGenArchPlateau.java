package pixelmon.worldGeneration;

import java.util.Random;

import pixelmon.RandomHelper;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenArchPlateau extends WorldGenerator{

	public static final int width = 23, length = 23, height = 35;
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		for(int yi = 0; yi < height; yi++){
			//generateCircle(world, random, x, y+yi, z, width, length);
		}
		return true;
	}
	
	public static int circleFunction(int x, int radius){
		x-=radius;
		return radius - (int) Math.sqrt(radius*radius - x * x);
	}
	
/*	public static float warpFunction(float value, int x, int radius){
		x-=radius;
		
		
	}
	
	public static int[] fixCircAndStride(Random rand, int[] startEnd, int[] previous, int l, int circleFactor, int stride, int iteration){
		if(iteration != 0){
			int rand1 = rand.nextInt(2);
			int rand2 = rand.nextInt(2);
			if(circleFunction(iteration-1,l) > circleFactor){ //if the last iteration's starting point is greater than this one
				if(startEnd[0] > previous[0])
					startEnd[0] = rand1 == 0 ? circleFactor : rand1 == 1 ? previous[0] : previous[0] -1;
			}
		}
	}*/
	
/*	public static void generateCircle(World world, Random rand, int x, int y, int z, int w, int l){
		int[] previous = new int[2];
		for(int xi = 0; xi < width; xi++){
			int circleFactor = circleFunction(xi, w/2);
			int stride = l - (2* circleFactor);
			int circleStart = circleFactor + RandomHelper.useRandomForNumberBetween(rand, -stride/20, stride/20);
			int modStride = stride + RandomHelper.useRandomForNumberBetween(rand, stride/20, stride/20);
			if(xi != 0){
				int fixer = rand.nextInt(2);
				int fixer2 = rand.nextInt(2);
				if(circleFunction(xi-1, l) > circleFactor){
					if(circleStart > mod1){
						circleStart = fixer == 0 ? circleFactor : fixer == 1 ? mod1 : mod1-1;
					}
					if(modStride < mod2){
						modStride = fixer == 0 ? stride : fixer == 1 ? mod2 : mod2 + 1;
					}
				}
				else if (circleFunction(xi-1, l) < circleFactor)
			}
			

			for(int zi = circleFactor+modifier1; zi < l - circleFactor + modifier2; zi++){
				world.setBlock(x+xi, y, z+zi, Block.dirt.blockID, 0, 2);
			}
		}
	}*/

}
