package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraft.world.World;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;

/**
 * The general contract of {@code AbstractDungeonEntrance} is that at least one of 
 * {@link #build(World, WorldGenMysteryDungeon, MysteryDungeonFloor, Random, int, int, int, int) build(...)} or
 * {@link #modFloorMap(World, WorldGenMysteryDungeon, MysteryDungeonFloor, Random, int, int, int) modFloorMap(...)} 
 * have to do something to generate an entrance.
 * @author Jack
 *
 */
public abstract class AbstractDungeonEntrance {

	/**Do modifications to a dungeon. This is called for each of the dungeon's floors, prior to the dungeon actually
	 * being generated within the world.
	 * @param world - The World object in which a dungeon is to be placed. Generating blocks and the like should not be 
	 * done within this method! 
	 * @param dungeon - The Dungeon object
	 * @param floor - The floor being operated on
	 * @param random - The... well, this is obvious.
	 * @param x - x location of dungeon about to be placed.
	 * @param y - y location of dungeon about to be placed.
	 * @param z - z location of dungeon about to be placed.
	 * @param floorIteration - the # of the floor, from 0 to {@link WorldGenMysteryDungeon#numFloors dungeon.numFloors}. 
	 * This can help decide whether or not to even do anything - for example, maybe the entrance should only be on the 
	 * first floor, so don't do anything if {@code floorIteration != 0}
	 */
	public abstract void modFloorMap(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor, Random random, int x, int y, int z, int floorIteration);
	
	public abstract void build(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor, 
			Random random, int x, int y, int z, int floorIteration);
	
	protected static boolean isPointAlignedWithLadder(int x, int z, int[] ladderShaft){
		return x == ladderShaft[0] || z == ladderShaft[1];
	}
	
	protected static boolean doesStripOverlayLadder(int iMin, int iMax, int ladderI){
		return ladderI >= iMin && ladderI <= iMax;
	}
	
	/**
	 * @param bounds - (xMin, zMin, xMax, zMax)
	 */
	protected static boolean isPointInBounds(int x, int z, int[] bounds){
		return x >= bounds[0] && x <= bounds[2] && z >= bounds[1] && z <= bounds[3];
	}
	
}
