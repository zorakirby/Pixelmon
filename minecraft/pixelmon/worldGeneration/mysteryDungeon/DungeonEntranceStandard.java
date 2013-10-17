package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import pixelmon.RandomHelper;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;

/**
 * This implementation of {@link AbstractDungeonEntrance} simply places one hallway for each 
 * {@link WorldHelper#NWSE cardinal direction}, by starting at one of the outermost rooms in that
 * direction, then extending out to the floor's boundaries.
 * @author Jack
 *
 */
public class DungeonEntranceStandard extends AbstractDungeonEntrance{


	@Override
	public void modFloorMap(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor, Random random, 
			int x, int y, int z,int floorIteration) {
		if(dungeon.up && floorIteration == 0){
			entrancesOn4Sides(floor, floor.theMap, random);
		}
		
	}
	
	@Override
	public void build(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor, Random random, 
			int x, int y, int z, int floorIteration) {
	//	if(dungeon.up && floorIteration == 0){
	//		entrancesOn4Sides(floor, random);
	//	}
	//	else if(!dungeon.up && floorIteration+1 == dungeon.numFloors){
	//		buildTopEntrance(world, dungeon, floor, random, x, y, z);
	//	}
	}
	
	protected void applyBottomEntrance(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor, Random random, int x, int y, int z){
		//AbstractList2D<Integer> entrances = entrancesOn4Sides(floor, floor.theMap, random);
		//for(int i : entrances.xList()){
			//for(int j : entrances.zList(i)){
			//	for(int n = 0; n <= dungeon.floorHeight; n++){
				//	if(n == 0)
				//		world.setBlock(x+i, y, z+j, dungeon.values.floorID, dungeon.values.floorMeta, 2);
				//	else if (n == dungeon.floorHeight)
				//		world.setBlock(x+i, y+n, z+j, dungeon.values.ceilingID, dungeon.values.ceilingMeta, 2);
				//	else
				//		world.setBlockToAir(x+i, y+n, z+j);
			//	}
			//}
		//}
	}
	
	protected void buildTopEntrance(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor, Random random, int x, int y, int z){
		
	}
	
	public static AbstractList2D<Integer> entrancesOn4Sides(MysteryDungeonFloor floor, AbstractList2D<Integer> map, Random random){
		for(ForgeDirection dir : WorldHelper.NWSE){
			boolean hortz = WorldHelper.isHorizontal(dir);
			boolean negative = WorldHelper.isNegative(dir);
			RoomMarker room = floor.randomOuterMostRoom(random, dir);
			AbstractList2D<Integer> strip = room.getEdgePointsOnSide(dir, false);
			int u1 = hortz ? strip.minZ() : strip.minX();
			int u2 = hortz ? strip.maxZ() : strip.maxX();
			//System.out.println("u1, u2 = " + PixelmonDebug.listObjs(u1, u2));
			int stripLength = u2-u1 + 1;
			int stride = Math.min(8, stripLength);
			int x0 = strip.minX() + (hortz ? 0 : RandomHelper.useRandomForNumberBetween(random, 0, stripLength - stride));
			int z0 = strip.minZ() + (hortz ? RandomHelper.useRandomForNumberBetween(random, 0, stripLength - stride) : 0);
			int hall = hortz ? negative ? -x0-2 : floor.width - x0+2 : negative ? -z0-2 : floor.length - z0+2;
			int width = hortz ? hall : stride;
			int length = hortz ? stride : hall;
			map.addRect(x0,z0,width,length,MysteryDungeonFloor.ID_ROOM,true);
			
			//cover up the 'danger block' value around the edge with a 'wall block' value
			if(hortz){
				int cx = negative ? MysteryDungeonFloor.antiCheatRing.minX() : MysteryDungeonFloor.antiCheatRing.maxX();
				int cz = z0 + length;
				map.addValue(cx, z0-1, MysteryDungeonFloor.ID_WALL);
				map.addValue(cx, cz, MysteryDungeonFloor.ID_WALL);
			}
			else{
				int cz = negative ? MysteryDungeonFloor.antiCheatRing.minZ() : MysteryDungeonFloor.antiCheatRing.maxZ();
				int cx = x0 + width;
				map.addValue(x0-1, cz, MysteryDungeonFloor.ID_WALL);
				map.addValue(cx, cz, MysteryDungeonFloor.ID_WALL);
			}
		}
		return map;
		
	}
	
	protected void genTopEntrance(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor floor,
			Random random, int x, int y, int z){
		
	}

}
