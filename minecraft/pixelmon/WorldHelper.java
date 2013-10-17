package pixelmon;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.UNKNOWN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeDirection;
import pixelmon.util.AbstractList2D;
import pixelmon.util.MappedList2D;

public class WorldHelper {
	
	public static final ForgeDirection[] NWSE = {NORTH, WEST, SOUTH, EAST};
	public static final ForgeDirection[] ESWN = {EAST, SOUTH, WEST, NORTH};
	
	
	public static final AbstractList2D<ForgeDirection> COMPASS = new MappedList2D();
	
	static{
		for(ForgeDirection dir : NWSE)
			COMPASS.addValue(dir.offsetX, dir.offsetZ, dir);
	}
	
	public static boolean isHorizontal(ForgeDirection dir){
		return dir.offsetX!=0;
	}
	
	public static boolean isNegative(ForgeDirection dir){
		return(dir.offsetX < 0 || dir.offsetY < 0 || dir.offsetZ < 0);
	}
	
	public static ForgeDirection[] getOpposites(ForgeDirection... dirs){
		ForgeDirection[] result = new ForgeDirection[dirs.length];
		for(int i = 0; i < dirs.length; i++){
			result[i] = dirs[i].getOpposite();
		}
		return result;
	}
	
	public static ForgeDirection ccw(ForgeDirection dir){
		switch(dir){
		case DOWN:return DOWN;
		case EAST:return NORTH;
		case NORTH:return WEST;
		case SOUTH:return EAST;
		case UNKNOWN:return UNKNOWN;
		case UP:return UP;
		case WEST:return SOUTH;
		}
		return null;//CANNOT REACH
	}
	
	public static char abbreviate(ForgeDirection dir){
		return dir.toString().charAt(0);
	}
	
	public static ForgeDirection toDirection(char c){
		switch(c){
		case 'N' : return NORTH;
		case 'W' : return WEST;
		case 'S' : return SOUTH;
		case 'E' : return EAST;
		}
		return UNKNOWN;
	}
	
	/**
	 * @return the Direction or 
	 */
	public static ForgeDirection[] dirsFromOffsets(float x, float z){
		ArrayList<ForgeDirection> resultList = new ArrayList();
		int angle = (int) (Math.toDegrees(Math.atan2(z, x))/45);
		switch(angle){
		case -3 : return new ForgeDirection[]{NORTH, WEST};
		case -2 : return new ForgeDirection[]{NORTH};
		case -1 : return new ForgeDirection[]{NORTH, EAST};
		case 0 : return new ForgeDirection[]{EAST};
		case 1 : return new ForgeDirection[]{SOUTH, EAST};
		case 2 : return new ForgeDirection[]{SOUTH};
		case 3 : return new ForgeDirection[]{SOUTH, WEST};
		case 4 : return new ForgeDirection[]{WEST};
		}
		return new ForgeDirection[]{UNKNOWN};
	}
	
	public static ArrayList<ForgeDirection> getDirectionsTowards(int distX, int distZ){
		ForgeDirection leftRight = distX == 0 ? null : distX < 0 ? ForgeDirection.WEST : ForgeDirection.EAST;
		ForgeDirection upDown = distZ == 0 ? null : distZ < 0 ? ForgeDirection.NORTH : ForgeDirection.SOUTH;
		ArrayList<ForgeDirection> result = new ArrayList();
		if(leftRight!=null)
			result.add(leftRight);
		if(upDown!= null)
			result.add(upDown);
		return result;
	}
	
	public static ForgeDirection randomAdjacent(ForgeDirection dir, Random random){
		int i = RandomHelper.useRandomForNumberBetween(random, -1, 1);
		return i==0 ? dir : COMPASS.get(dir.offsetZ*i, dir.offsetX*i);
	}
	
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
	
	public static int getWaterDepthAt(int posX, int posZ, World worldObj){
		if(!isWaterOrIce(worldObj, posX, 62, posZ))
			return 0;
		int depth = 0;
		for(depth = 1; isWaterOrIce(worldObj, posX, 63-depth, posZ); depth++);
		return depth;
	}
	
	public static boolean isWaterOrIce(World world, int x, int y, int z){
		int blockID = world.getBlockId(x, y, z);
		return blockID == Block.waterStill.blockID || blockID == Block.ice.blockID;
	}
	
	public static boolean isChunkFloodedOrFrozen(int chunkX, int chunkZ, World worldObj, int jump){
		if(jump>= 16)
			throw new IllegalArgumentException("Cannot check a whether a chunk is flooded or not if the initial jump value overshoots the chunk's bounds right after the first iteration!");
		for(int i = 0; i + jump <= 16; i+=jump){
			for(int j = 0; j + jump <= 16; j+=jump){
				if(!isWaterOrIce(worldObj, chunkX*16+i, 62, chunkZ*16+j))
					return false;
			}
		}
		System.out.println("Chunk is flooded");
		return true;
	}
	
	/**
	 * Super-Lazy helper function for converting block-based world coordinates to 
	 * chunk-based world coordinates
	 */
	public static int[] block2ChunkCoords(int x, int z){
		return new int[]{x >> 4, z >> 4};
	}
	
	
	/**
	 * @return: the coordinates just outside a chunk's bounds in <code>dir</code> direction.<br>
	 * NORTH = [0, -1]<br>
	 * WEST = [-1, 0]<br>
	 * SOUTH = [0, 16]<br>
	 * EAST = [16, 0]<br>
	 */
	public static int[] forgeDirectionToLocalChunkCoords(ForgeDirection dir){
		int x = (int) (dir.offsetX == 0 ? 0 : 17F/2F*dir.offsetX + 15F/2F);
		int z = (int) (dir.offsetZ == 0 ? 0 : 17F/2F*dir.offsetZ + 15F/2F);
		return new int[]{x, z};
	}
	
	/**
	 * @return: the coordinates just outside a chunk's bounds in <code>dir</code> 
	 * direction, aligned on <code>offset</code> in <code>dir</code>'s nonsignificant
	 * axis.<br>
	 * NORTH = [offset, -1]<br>
	 * WEST = [-1, offset]<br>
	 * SOUTH = [offset, 16]<br>
	 * EAST = [16, offset]<br>
	 */
	public static int[] forgeDirectionAndOffsetToLocalChunkCoords(ForgeDirection dir, int offset){
		int x = (int) (dir.offsetX == 0 ? offset : 17F/2F*dir.offsetX + 15F/2F);
		int z = (int) (dir.offsetZ == 0 ? offset : 17F/2F*dir.offsetZ + 15F/2F);
		return new int[]{x, z};
	}
	/**
	 * checks to see if the biome at (<code>startX</code>, <code>startZ</code>) in <code>world</code> matches the biome in <code>world</code> at every <code>skip</code> points in <code>points</code>, offset by (<code>startX</code>, <code>startZ</code>)
	 */
	public static boolean canFitWithinBiome(World world, BiomeGenBase biome, int startX, int startZ, int coordBaseMode, int skip, AbstractList2D<?> points, int tolerance){
		int violations = 0;
		int index = 0;
		for(Integer i : points.xList()){
			Collection<Integer> column= points.zList(i);
			for(Integer j : column){
				if(index % skip != 0){
					index++;
					continue;
				}
				int realI = coordBaseMode%2 == 0 ? i : j;
				int realJ = coordBaseMode%2 == 0 ? j : i;
				index++;
				BiomeGenBase currentBiome = world.getBiomeGenForCoords(startX + realI, startZ + realJ);
				if(currentBiome != biome)
					violations++;
				if(violations > tolerance)
					return false;
			}
		}
		return true;
	}
	
	public static boolean canFitWithinBiome(World world, BiomeGenBase biome, int x, int z, int width, int length, int tolerance){
		int violations = 0;
		for(int i = 0; i < width >> 4; i++){
			for(int j = 0; j < length >>4; j++){
				BiomeGenBase currentBiome = world.getBiomeGenForCoords((x+i)*16, (z+j)*16);
				if(biome != currentBiome)
					violations++;
				if(violations > tolerance)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @return
	 * If <code>points</code> can fit all within the Biome at <code>(x, z)</code>:<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<code>0:</code> If the coord mode that works is 0 (<code>points(x, z) = offset(x,z)</code>)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<code>1:</code> If the coord mode that works is 1 (<code>points(x, z) = offset(z,x)</code>)<br>
	 * If it can't fit, returns -1.
	 */
	public static int getFitting(World world, BiomeGenBase biome, int x, int z, AbstractList2D<Float> points, int skip, int tolerance){
		if(canFitWithinBiome(world, biome, x, z, 0, skip, points, tolerance))
			return 0;
		if(canFitWithinBiome(world, biome, x, z, 1, skip, points, tolerance))
			return 1;
		return -1;
	}
	
	public static AbstractList2D<Integer> getNearbyBiomesFromChunkCoords(World world, int chunkX, int chunkZ, int radius){
		AbstractList2D<Integer> result = new MappedList2D();
		for(int i = -radius; i <= radius; i++){
			for(int j = -radius; j <= radius; j++){
				if(i == 0 && j == 0)
					continue;
				int biomeID = world.getBiomeGenForCoords((chunkX+i)*16, (chunkZ+j)*16).biomeID;
				result.addValue(i, j, biomeID);
				}
			}
		return result;
	}
	
	public static AbstractList2D<Integer> getNearbyBiomes(World world, int blockX, int blockZ, int radius){
		AbstractList2D<Integer> result = new MappedList2D();
		for(int i = -radius; i <= radius; i++){
			for(int j = -radius; j <= radius; j++){
				if(i == 0 && j == 0)
					continue;
				int biomeID = world.getBiomeGenForCoords(blockX + i*16, blockZ+j*16).biomeID;
				result.addValue(i, j, biomeID);
				}
			}
		return result;
	}
	
	public static AbstractList2D<Float> getNearbyMatchingBiomes(BiomeGenBase biome, World world, int x, int z, int radius){
		AbstractList2D<Float> result = new MappedList2D();
		for(int i = -radius; i <= radius; i++){
			for(int j = -radius; j <= radius; j++){
				if(world.getBiomeGenForCoords(x+(i*16), z+(j*16)) == biome){
					result.addValue(i, j, 1F);
				}
			}
		}
		return result;
	}
	
	public static AbstractList2D<Float> getContinuousBiomeFromChunkCoords(BiomeGenBase biome, World world, int chunkX, int chunkZ){
		AbstractList2D<Float> result = new MappedList2D();
		fillMapWithContinuousBiome(biome, world, result, chunkX, chunkZ);
		return result;
	}
	
	public static void fillMapWithContinuousBiome(BiomeGenBase biome, World world, AbstractList2D<Float> map, int chunkX, int chunkZ){
		for(ForgeDirection dir : NWSE){
			int trueX = chunkX + dir.offsetX;
			int trueZ = chunkZ + dir.offsetZ;
			if(!map.containsValue(trueX, trueZ) && world.getBiomeGenForCoords(trueX*16, trueZ*16) == biome){
				map.addValue(trueX, trueZ, 1F);
				fillMapWithContinuousBiome(biome, world, map, trueX, trueZ);
			}
		}
	}
	
	/*	 No, no adding Ordinal Directions to ForgeDirection...
	 * .... Well, we CAN, just it's a horrible idea, because if any other mod installed
	 *  calls "values()" on ForgeDirection, the extra ordinal directions will also be 
	 *  included, which would almost certainly muck everything up. 
	 *  ...Which, calling "values()" on ForgeDirection is stupid in itself, because
	 *  ForgeDirection.UNKNOWN will be included, and ForgeDirection actually has a 
	 *  VALID_DIRECTIONS array, but ya never know what other modders will decide to 
	 *  do
*/
	/**
	 * @param name
	 * @param offsetX
	 * @param offsetY
	 * @param offsetZ
	 * @return
	 */
	@Deprecated
	private static ForgeDirection newDirection(String name, int offsetX, int offsetY, int offsetZ){
		Class[][] DIRECTION_PARAMS = new Class[][]{
				{ForgeDirection.class, int.class, int.class, int.class}
			};
		return EnumHelper.addEnum(DIRECTION_PARAMS, ForgeDirection.class, name, offsetX, offsetY, offsetZ);
	}
}
