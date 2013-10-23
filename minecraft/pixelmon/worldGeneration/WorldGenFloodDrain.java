package pixelmon.worldGeneration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.google.common.eventbus.EventBus;

import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.CommonHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.MinimalList2D;
import pixelmon.util.PixelmonDebug;
import pixelmon.worldGeneration.biome.BiomeGenNeedsDraining;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

/**
 * This is a complex, frustrating-as-hell-to-program WorldGen class that drains water pools.
 * Have you ever had really bad problems with Stack-Overflows from recursion? I hadn't.
 * Until I started programming this thing. But anyway, back to what this class does.
 * The draining will only occur if the chunk at the X/Z coordinate passed into the 
 * <code>generate</code> function is completely flooded with water or ice-topped water <b> 
 * at the Y coordinate of 62</b> (sea level, 16x16 blocks). This check is done to prevent 
 * a not-very-likely, but still plausible case where there are multiple separate pools in 
 * the same chunk, and the wrong one is selected. After this initial check is made, this 
 * generator class will:<br><code>
 * 1. Drain all the contiguous water and ice-topped water in the chunk<br>
 * 2. Set the newly-uncovered surface block to the <b>topBlock</b> appropriate to the biome
 * at the X/Z location<br>
 * 3. Recursively create more instances of WorldGenFloodDrain per each adjacent chunk
 * connected by water blocks (again, at Y = 62)</code><br><br>
 * In addition to these standard draining features, there is also a "failsafe"
 * for cases when the drain function comes into contact with water-only biomes.<br>
 * These include: 
 * {@link BiomeGenBase#river River}, 
 * {@link BiomeGenBase#frozenRiver FrozenRiver},
 * {@link BiomeGenBase#ocean Ocean}, and
 * {@link BiomeGenBase#frozenOcean FrozenOcean}.<br>
 * When the detection function comes into contact with one of these biomes, it will instead
 * create a dam of blocks, the type of which is determined by <code>WorldGenFloodDrain</code>'s
 * constructor. <b>A <code>DamGenEvent</code> will then be posted to <code>DAM_BUS</code>.</b> The drainage function will not reach the rest of the watery biome, 
 * preventing it from literally destroying oceans.
 * 
 * <br><br><i>
 * The primary purpose of this class is to be used in biomes whose minHeight causes it to
 *  generate below sea level.</i> When that happens,  Minecraft just assumes "Well, 
 *  by golly, this is all s'posed to be covered in water! Let's fill 'er up!

 * @author Jack
 *
 */
public class WorldGenFloodDrain extends WorldGenerator{
	protected final static EventBus DAM_BUS = new EventBus();
	private static volatile MinimalList2D<Integer> drainedChunks = new MinimalList2D(), drainedBlocks = new MinimalList2D();
	private AbstractList2D<Object> checkedCoords = new MappedList2D();
	private boolean checkForFlood, isChild;
	private BiomeGenNeedsDraining user;
	private int damBlock, xLoc, zLoc;
	
	
	public WorldGenFloodDrain(BiomeGenNeedsDraining user, int damBlock, boolean checkForFullFlood, boolean isChild){
		this.user = user;
		this.damBlock = damBlock;
		this.checkForFlood = checkForFullFlood;
		this.isChild = isChild;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		this.xLoc = x>>4;
		this.zLoc = z>>4;
		if(drainedChunks.contains(xLoc, zLoc))
			return false;
		if(!checkForFlood || WorldHelper.isChunkFloodedOrFrozen(xLoc, zLoc, world, 2)){
			int[][] shape = detectLocalShape(world, x, z);
			drain(world, shape);
			drainedChunks.addValue(xLoc, zLoc, 1);
			drainNearbyChunks(world, shape);
			if(!this.isChild)
				user.onDoneDraining(world, random, x, y, z);
			return true;
		}
		return false;
		
	}
	
	private void debugFillMap(int[][] shape){
		for(int[] i : shape){
			System.out.println(Arrays.toString(i));
		}
	}

	private void drainNearbyChunks(World world, int[][]map){
		for(ForgeDirection dir : WorldHelper.NWSE){
			drainChunkOnSide(world, map, dir);
		}
	}
	
	private void drainChunkOnSide(World world, int[][] map, ForgeDirection dir){
		int[] strip = getStrip(map, dir);
		for(int i = 0; i < 16; i++){
			if(strip[i] == 1){
				int[] localTestCoords = WorldHelper.forgeDirectionAndOffsetToLocalChunkCoords(dir, i);
				if(isLocalCoordWaterOrIce(world, localTestCoords[0], localTestCoords[1]) && !pointIsNonDrainable(world, this.xLoc*16 + localTestCoords[0], this.zLoc*16 + localTestCoords[1])){
					int worldX = this.xLoc*16 + localTestCoords[0];
					int worldZ = this.zLoc*16 + localTestCoords[1];
					world.getBiomeGenForCoords(worldX, worldZ);
					if(!drainedChunks.contains(worldX>>4, worldZ>>4)){
						new WorldGenFloodDrain(this.user, this.damBlock, false, true).generate(world, null, worldX, -1, worldZ);
					}
					//if the chunk was ALREADY drained, then the above check to see if the coordinate was 
						//ice or water SHOULD HAVE equated to false.
					//Since it didn't, that means the chunk's biome layout is positioned weird and needs 
						//to be redrained.
					else if(drainedChunks.contains(worldX>>4, worldZ>>4)){
						drainedChunks.remove(worldX>>4, worldZ>>4);
						new WorldGenFloodDrain(this.user, this.damBlock, false, true).generate(world, null, worldX, -1, worldZ);
					}
				}
			}
		}
	}
	
	/**
	 * gets the 16-length int[] in <code>map</code> based on an input forgeDirection:<br>
	 * {@link ForgeDirection#NORTH NORTH}: gets the horizontal strip from (0,0) to (15,0)<br>
	 * {@link ForgeDirection#SOUTH SOUTH}: gets the horizontal strip from (0,15) to (15,15)<br>
	 * {@link ForgeDirection#WEST WEST}: gets the vertical strip from (0,0) to (0,15)<br>
	 * {@link ForgeDirection#EAST EAST}: gets the vertical strip from (15,0) to (15,15)
	 */
	private int[] getStrip(int[][] map, ForgeDirection dir){
		int[] result = new int[16];
		for(int i = 0; i < 16; i++){
			int xi = (int) (dir.offsetX != 0 ? 15F/2F*dir.offsetX + 15F/2F : i);
			int zi = (int) (dir.offsetZ != 0 ? 15F/2F*dir.offsetZ + 15F/2F : i);
			result[i] = map[xi][zi];
		}
		return result;
	}
	
	private boolean isLocalCoordWaterOrIce(World world, int localX, int localZ){
		return WorldHelper.isWaterOrIce(world, xLoc*16+localX, 62, zLoc*16+localZ);
	}
	
	/**
	 * starting point for {@link #addPointRecursively(World, int, int, int[][])}
	 */
	private int[][] detectLocalShape(World world, int x, int z){
		int[][] result = new int[16][16];
		addPointRecursively(world, x, z, result);
		return result;
	}
	
	/**
	 * Recursively adds points to a 16x16 int[][].<br>
	 * For each local coordinate(16x16) in this chunk:<br>
	 * A sea-level drainable column inserts the value <code>1</code><br> and recursively calls this function again
	 * A sea-level {@link #pointIsNonDrainable(World, int, int) non-drainable} column inserts the value <code>2</code>
	 * and does not call the function again.
	 */
	public void addPointRecursively(World world, int x, int z, int[][] fill){
		if(checkedCoords.isntNull(x, z))
			return;
		checkedCoords.addValue(x, z, CommonHelper.THING);
		int[] local = getLocalCoords(x, z);
		int i = local[0], j = local[1];
		if(isInChunkBounds(i, j) && fill[i][j] == 0 && WorldHelper.isWaterOrIce(world, x, 62, z)){
			if(!pointIsNonDrainable(world, x, z)){
				fill[i][j] = pointBordersNonDrainable(world, x, z) ? 2 : 1;
				for(ForgeDirection dir : WorldHelper.NWSE){
					addPointRecursively(world, x+dir.offsetX, z +dir.offsetZ, fill);
				}
			}
			//else{
			//	damHere(world, x, z);
			//}
		}
	}
	
	/**
	 * drains world coordinates based on a 2D int[]. For each <code>int</code> in <code>map</code>, looked up by local coordinates:<br>
	 * <code>0</code>: causes nothing to happen<br>
	 * <code>1</code>: drains the column<br>
	 * <code>2</code>: creates a dam column
	 */
	public void drain(World world, int[][] map){
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				if(map[i][j] == 1){
					drainHere(world, this.xLoc*16 + i, this.zLoc*16 + j);
					possiblyPlaceDam(world, this.xLoc*16+i, this.zLoc*16 + j);
				}
				else if(map[i][j] == 2){
					damHere(world, this.xLoc*16 + i, this.zLoc*16 + j);
				}
			}
		}
	}
	
	/**
	 * @param localX - local x coordinate
	 * @param localZ -local z coordinate
	 * @return true <code>localX</code> and <code>localZ</code> are both between 0 and 15 <i>inclusive</i>
	 */
	private boolean isInChunkBounds(int localX, int localZ){
		return localX >= 0 && localX < 16 && localZ >= 0 && localZ <16;
	}
	
	
	/**
	 * @return the local X/Z coordinates, given global block coordinates.
	 * This is the offset of the given block coords from this <code>WorldGenFloodDrain</code>'s xLoc/zLoc
	 */
	public int[] getLocalCoords(int x, int z){
		return new int[]{x - (xLoc*16), z - (zLoc*16)};
	}
	
	/**
	 * places a dam column at this X/Z coordinate
	 */
	private void damHere(World world, int x, int z){
		if(drainedBlocks.contains(x, z))
			return;
		drainedBlocks.addValue(x, z, 1);
		if(WorldHelper.isWaterOrIce(world, x, 62, z)){
			int depth = WorldHelper.getWaterDepthAt(x, z, world);
			int i = 0;
			world.setBlock(x, 63, z, damBlock, 0, 2);
			for(i = 0; i < depth; i++){
				world.setBlock(x, 62-i, z, damBlock, 0, 2);
			}
			new DamGenEvent(x, z, world);
		}
	}
	
	/**
	 * drains the sea-level water/ice column at this coordinate, and sets the block after
	 * the last water/ice block to the appropriate {@link BiomeGenBase#topBlock top block}
	 */
	private void drainHere(World world, int x, int z){
		if(drainedBlocks.contains(x, z))
		return;
		drainedBlocks.addValue(x, z, 1);
		if(WorldHelper.isWaterOrIce(world, x, 62, z)){
			int depth = WorldHelper.getWaterDepthAt(x, z, world);
			int i = 0;
			for(i = 0; i < depth; i++){
				world.setBlock(x, 62-i, z, 0, 0, 2);
			}
			if(world.getBlockId(x, 62-i, z) != 0){
				int surfaceBlock = world.getBiomeGenForCoords(x, z).topBlock;
				world.setBlock(x, 62-i, z, surfaceBlock, 0, 2);
			}
		}
	}
	
	/**
	 * @return true if this coordinate is adjacent to (NWSE) a non-drainable coordinate
	 */
	private boolean pointBordersNonDrainable(World world, int x, int z){
		for(ForgeDirection dir : WorldHelper.NWSE){
			if(pointIsNonDrainable(world, x+dir.offsetX, z+dir.offsetZ))
					return true;
		}
		return false;
	}
	
	/**
	 * places a dam column for each non-drainable adjacent (NWSE) coordinate next to this coordinate
	 */
	private void possiblyPlaceDam(World world, int x, int z){
		for(ForgeDirection dir : WorldHelper.NWSE){
			if(pointIsNonDrainable(world, x+dir.offsetX, z+dir.offsetZ)){
				damHere(world, x+dir.offsetX, z+dir.offsetZ);
			}
		}
	}
	
	private boolean pointIsNonDrainable(World world, int x, int z){
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return biome == BiomeGenBase.frozenRiver || biome == BiomeGenBase.river ||biome == BiomeGenBase.ocean || biome == BiomeGenBase.frozenOcean;
	}
	
	public static void register(Object obj){
		DAM_BUS.register(obj);
	}
	
	public static class DamGenEvent{
		final int worldX, worldZ;
		World world;
		
		protected DamGenEvent(int x, int z, World world){
			this.worldX = x;
			this.worldZ = z;
			this.world = world;
			WorldGenFloodDrain.DAM_BUS.post(this);
		}
	}



	

}
