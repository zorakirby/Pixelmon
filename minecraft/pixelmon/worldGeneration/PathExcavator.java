package pixelmon.worldGeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import pixelmon.RandomHelper;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.MappedList2D;

public class PathExcavator {
	
	public final int x1, x2, y, z1, z2;
	
	public PathExcavator(int[] p1, int[]p2, int y){
		this.x1 = p1[0];
		this.z1 = p1[1];
		this.x2 = p2[0];
		this.z2 = p2[1];
		this.y = y;
	}
	
	public PathExcavator(int x1, int z1, int x2, int z2, int y){
		this.x1 = x1;
		this.z1 = z1;
		this.x2 = x2;
		this.z2 = z2;
		this.y = y;
	}
	
	public AbstractList2D<Integer> genPath(Random random, int radius){
		AbstractList2D<Integer> path = new MappedList2D();
		ArrayList<ForgeDirection> dirs = WorldHelper.getDirectionsTowards(x2-x1, z2-z1);
		int[] p1 = {x1, z1};
		int[] p2 = {x2, z2};
		while(!dirs.isEmpty()){
			boolean lastDir = dirs.size() == 1;
			ForgeDirection dir = dirs.remove(random.nextInt(dirs.size()));
			boolean hortz = WorldHelper.isHorizontal(dir);
			int stride = hortz ? Math.abs(p1[0]-p2[0]) : Math.abs(p1[1]-p2[1]);
			if(lastDir){
				int width = hortz ? (stride+1)*dir.offsetX: 1;
				int length = hortz ? 1 : (stride+1)*dir.offsetZ;
				path.addRect(p1[0], p1[1], width, length, 1, true);
				}
			else{
				int stride0 = RandomHelper.useRandomForNumberBetween(random, 0, stride)&-2;
				int width = hortz ? stride0*dir.offsetX : 1;
				int length = hortz ? 1 : stride0*dir.offsetZ;
				path.addRect(p1[0], p1[1], width, length, 1, true);
				p1[0]+=hortz ? width : 0;
				p1[1]+=hortz ? 0 : length;
				stride0 = stride-stride0;
				width = hortz ? stride0*dir.offsetX*-1 : 1;
				length = hortz ? 1 : stride0*dir.offsetZ*-1;
				path.addRect(p2[0], p2[1], width, length, 1, true);
				p2[0]+=hortz ? width : 0;
				p2[1]+=hortz ? 0 : length;
				}
		}
		return path;
	}

}
