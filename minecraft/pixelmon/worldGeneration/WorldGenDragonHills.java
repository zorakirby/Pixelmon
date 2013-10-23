package pixelmon.worldGeneration;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import pixelmon.RandomHelper;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.geom.Fractal;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDragonHills extends WorldGenSpecificBiome{
	private static boolean debugPlacement = true;
	private static boolean generating = false;
	
	public WorldGenDragonHills(BiomeGenBase user) {
		super(user);
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if(generating)
			return false;
		int iterations = RandomHelper.useRandomForNumberBetween(random, 12, 18);
		int height = RandomHelper.useRandomForNumberBetween(random, 56, 64);
		AbstractList2D<Float> dragon = chooseDragon(random);
		int mode = getFitting(world, x, z, dragon, 3, 4);
		if(mode == -1)
			return false;
		generating = true;
		float min = dragon.minVal().floatValue()+.2F;
		float max = (float)dragon.maxVal().floatValue();
		if(debugPlacement)
			System.out.println(String.format("Dragon Hills at %s, %s", x, z));
		
		for(Integer i : dragon.xList()){
			Collection<Integer> column = dragon.zList(i);
			for(Integer j : column){
				int i0 = x + (mode == 0 ? i : j);
				int j0 = z + (mode == 0 ? j : i);
				int heightVal = world.getTopSolidOrLiquidBlock(i0, j0);
				float scale = (float) FunctionHelper.slider(dragon.get(i, j), min, max);
				int heightAdjustment = Math.round(scale*height);
				for(int yi = 0; yi < heightAdjustment; yi++){
					int blockID = yi+1 == heightAdjustment ? world.getBiomeGenForCoords(i0, j0).topBlock : Block.dirt.blockID;
					world.setBlock(i0, heightVal + yi, j0, blockID, 0, 2);
				}
			}
		}
		return true;
	}
	
	
	
	public static AbstractList2D<Float> chooseDragon(Random random){
		int iterations = RandomHelper.useRandomForNumberBetween(random, 12, 18);
		return Fractal.dragonLSystem(iterations, true);
	}
	
	
	

}
