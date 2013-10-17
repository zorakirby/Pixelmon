package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.geom.Fractal;

public class WorldGenGoldragonHills extends WorldGenerator{
	
	public static AbstractList2D<Float> goldragon = Fractal.goldDragon(300, 15, true).recreateWithMinAtZero();
	protected static double minVal = goldragon.minVal(), maxVal = goldragon.maxVal();

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int[] usage = getFittingLocalCoordsAndMode(world, x, z);
		if(usage == null)
			return false;
		for(Integer i : goldragon.xList()){
			for(Integer j : goldragon.zList(i)){
				int i0 = x + usage[0] + (usage[2] == 0 ? i : j);
				int j0 = z + usage[1] + (usage[2] == 0 ? j : i);
				float val = goldragon.get(i, j);
				float scale = (float) FunctionHelper.slider(val, minVal, maxVal);
				int worldY = world.getTopSolidOrLiquidBlock(i0, j0);
				for(int a = 0; a < 2*scale; a++){
					world.setBlock(i0, worldY+a, j0, Block.dirt.blockID, 0, 2);
				}
			}
		}
		return true;
	}
	
	protected int[] getFittingLocalCoordsAndMode(World world, int x, int z){
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				int usage = WorldHelper.getFitting(world, null, x+i, z+j, goldragon, 5, 20);
				if(usage != -1){
					System.out.println("It fits in " + world.getBiomeGenForCoords(x+i, z+j).biomeName);
					return new int[]{i,j,usage};
				}
			}
		}
		return null;
	}
	

	
	

}
