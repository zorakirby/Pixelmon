package pixelmon.worldGeneration.mysteryDungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraftforge.common.ForgeDirection;
import pixelmon.RandomHelper;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.MappedList2D;

public class HallMarker extends AbstractFloorPart{
	//Hehehe, Hallmark... accidental
	protected int x1, z1, x2, z2;
	public RoomMarker part1, part2;
	protected ForgeDirection mainDir;
	public AbstractList2D<ForgeDirection> path = new MappedList2D();
	protected ForgeDirection[] instructions;
	protected AbstractList2D<ForgeDirection> endPoints = new MappedList2D();
	protected AbstractList2D<ForgeDirection> extendPoints = new MappedList2D();
	
	public HallMarker(RoomMarker p1, RoomMarker p2, Random rand, ForgeDirection... dir){
		this.part1 = p1;
		this.x1 = p1.xBase;
		this.z1 = p1.zBase;
		this.part2 = p2;
		this.x2 = p2.xBase;
		this.z2 = p2.zBase;
		this.mainDir = dir[rand.nextInt(dir.length)];
	}

	
	public void hallStart(AbstractList2D<RoomMarker> rooms, AbstractList2D<AbstractFloorPart> floorMap, Random random, int widthBase, int lengthBase){
		
		String legalDirs = getLegalLinkDirsAbbrev(part1, rooms);
		boolean chance = random.nextInt(20) < 4;
		ForgeDirection dir1 = chance ? WorldHelper.randomAdjacent(mainDir, random) : mainDir;
		if(!legalDirs.contains("" + WorldHelper.abbreviate(dir1)))
			dir1 = mainDir;
		if(!legalDirs.contains("" + WorldHelper.abbreviate(dir1)))
			dir1 = WorldHelper.toDirection(legalDirs.charAt(random.nextInt(legalDirs.length())));
		
		
		legalDirs = getLegalLinkDirsAbbrev(part2, rooms);
		chance = random.nextInt(20) < 4;
		ForgeDirection dir2 = chance ? WorldHelper.randomAdjacent(mainDir.getOpposite(), random) : mainDir.getOpposite();
		if(!legalDirs.contains(""+WorldHelper.abbreviate(dir2)))
			dir2 = mainDir.getOpposite();
		if(!legalDirs.contains(""+WorldHelper.abbreviate(dir2)))
			dir2 = WorldHelper.toDirection(legalDirs.charAt(random.nextInt(legalDirs.length())));
		
		int[] point1 = part1.getRandomEdgePoint(random, dir1, true);
		int[] point2 = part2.getRandomEdgePoint(random, dir2, true);
		
		makePath(rooms, floorMap, random, point1, dir1, point2, dir2,  widthBase, lengthBase);
	}
	

	
	public void makePath(AbstractList2D<RoomMarker> rooms, AbstractList2D<AbstractFloorPart> floorMap, Random random, int[] point1, ForgeDirection dir1, int[] point2, ForgeDirection dir2, int widthBase, int lengthBase){
		
		boolean hortz1 = WorldHelper.isHorizontal(dir1);
		boolean hortz2 = WorldHelper.isHorizontal(dir2);
		int maxStride1 = Math.min(hortz1 ? widthBase : lengthBase, maxStride(point1, dir1, floorMap)) +1;
		int maxStride2 = Math.min(hortz2 ? widthBase : lengthBase, maxStride(point2, dir2, floorMap)) +1;
		int stride1 = -1;
		int stride2 = -1;
		try{
			stride1 = RandomHelper.useRandomForNumberBetween(random, 2, maxStride1)&-2;
			stride2 = RandomHelper.useRandomForNumberBetween(random, 2, maxStride2)&-2;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(MysteryDungeonFloor.describeLayout(rooms.toSet()));
		}
		
		int width = stride1*dir1.offsetX;
		int length = stride1*dir1.offsetZ;
		int w = width + (hortz1 ? dir1.offsetX : 1);
		int l = length + (hortz1 ? 1 : dir1.offsetZ);
		this.addHall(floorMap, point1[0], point1[1], w, l);
		point1[0]+= width;
		point1[1]+= length;
		endPoints.addValue(point1[0], point1[1], dir1);
		
		width = stride2*dir2.offsetX;
		length = stride2*dir2.offsetZ;
		w = width + (hortz2 ? dir2.offsetX : 1);
		l = length + (hortz2 ? 1 :dir2.offsetX);
		this.addHall(floorMap, point2[0], point2[1], w, l);
		point2[0]+= width;
		point2[1]+= length;
		endPoints.addValue(point2[0], point2[1], dir2);
		
		finishPath(rooms, floorMap, random, point1, point2, widthBase, lengthBase);
	}
	
	
	
	private void finishPath(AbstractList2D<RoomMarker> rooms,
			AbstractList2D<AbstractFloorPart> floorMap, Random random, int[] point1,
			int[] point2, int widthBase, int lengthBase) {
		int distX = point2[0]-point1[0];
		int distZ = point2[1]-point1[1];
		ArrayList<ForgeDirection> towards = WorldHelper.getDirectionsTowards(distX, distZ);
		distX = Math.abs(distX);
		distZ = Math.abs(distZ);
		while(!towards.isEmpty()){
			ForgeDirection dir = towards.remove(random.nextInt(towards.size()));
			boolean hortz = WorldHelper.isHorizontal(dir);
			extendPoints.addValue(point1[0], point1[1], dir);
			//checkFlip(point1[0], point1[1], dir, floorMap, random);
			
			int width = distX == 0 ? 0 : (random.nextInt(distX))&-2;
			int length = distZ == 0 ? 0 : (random.nextInt(distZ))&-2;
			int w = (width*dir.offsetX) + (hortz ? dir.offsetX : 1);
			int l = (length*dir.offsetZ) + (hortz ? 1 : dir.offsetZ);
			addHall(floorMap, point1[0], point1[1], w, l);
			point1[0] += width*dir.offsetX;
			point1[1] += length*dir.offsetZ;
			//checkFlip(point1[0], point1[1], dir.getOpposite(), floorMap, random);
			extendPoints.addValue(point1[0], point1[1], dir.getOpposite());
			
			
			dir = dir.getOpposite();
			//checkFlip(point2[0], point2[1], dir, floorMap, random);
			extendPoints.addValue(point2[0], point2[1], dir);
			width = distX - width;
			length = distZ - length;
			w = (width*dir.offsetX) + (hortz ? dir.offsetX : 1);
			l = (length*dir.offsetZ) + (hortz ? 1 : dir.offsetZ);
			addHall(floorMap, point2[0], point2[1], w, l);
			point2[0] += width*dir.offsetX;
			point2[1] += length*dir.offsetZ;
			extendPoints.addValue(point2[0], point2[1], dir.getOpposite());
		}
		
	}
	
	private void addHall(AbstractList2D<AbstractFloorPart> floorMap, int x, int z, int width, int length){
		int dx = Integer.signum(width);
		int dz = Integer.signum(length);
		for(int i = 0; i!= width; i+=dx){
			for(int j = 0; j!= length; j+= dz){
				AbstractFloorPart part = floorMap.get(x+i, z+j);
				if(part!= null)
					joinWith(part);
				if(!(part instanceof RoomMarker))
					floorMap.addValue(x+i, z+j, this);
			}
		}
	}
	
	/**
	 * This is called in {@code finishPath} when a new direction is chosen to extend an endpoint.
	 * If the new chosen direction is the opposite of the endpoint's direction, this will extend
	 * the path along the old direction as far as it can go (if it leads to a room or another hall) 
	 * or a random length (if it reaches the outer bounds of the floor's map).
	 */
	private void checkFlip(int i, int j, ForgeDirection newDir, AbstractList2D<AbstractFloorPart> floorMap, Random random){
		
		ForgeDirection pathDir = endPoints.get(i, j);
		if(pathDir != null && pathDir.getOpposite() == newDir){
			Object[] typeAndStride = maxStrideUntilCollision(i, j, pathDir, floorMap);
			int stride = (Integer) typeAndStride[1];
			stride = typeAndStride[0] == null ? (RandomHelper.useRandomForNumberBetween(random, stride/5, stride/2)&-2) + 1 : stride;
			boolean hortz = WorldHelper.isHorizontal(pathDir);
			int width = hortz ? stride*pathDir.offsetX : 1;
			int length = hortz ? 1 : stride*pathDir.offsetZ;
			addHall(floorMap, i, j, width, length);
		}
	}
	
	public void touchup(AbstractList2D<AbstractFloorPart> floorMap, Random random){
		for(int i : endPoints.xList())
			for(int j : endPoints.zList(i)){
				ForgeDirection extendDir = extendPoints.get(i, j);
				if(extendDir != null)
					checkFlip(i,j,extendDir, floorMap, random);
			}
	}
	
	private void closeEnough(AbstractList2D<AbstractFloorPart> floorMap, int i, int j, ForgeDirection dir){
		AbstractFloorPart value = floorMap.get(i+dir.offsetX*2, j+dir.offsetZ*2);
		if(value != null && value instanceof RoomMarker){
			int n = 0;
			for(ForgeDirection dir0 : WorldHelper.NWSE){
				if(!(floorMap.get(i+dir0.offsetX, j+dir0.offsetZ) instanceof WallMarker)){
					n++;
					if(n > 1)
						return;
				}
			}
			floorMap.addValue(i+dir.offsetX, j+dir.offsetZ, this);
		}
	}
	
	private boolean coordsAreEvensOnly(int[] coords){
		return (coords[0]&1) == 0 && (coords[1]&1)==0;
	}
	
	


	public int maxStride(int[] point, ForgeDirection dir, AbstractList2D<AbstractFloorPart> floorMap){
		int i = 0;
		AbstractFloorPart value = null;
		while((value = floorMap.get(point[0] + dir.offsetX*(i+1), point[1] + dir.offsetZ*(i+1))) != null && (!(value instanceof RoomMarker))){
			i++;
		}
		return i;
	}
	
	/**
	 * @return an array containing:<br>
	 * {@code 0}: the first non-{@code WallMarker} object collided into, starting at {@code (i,j)} and moving
	 * in {@code dir} direction, or {@code null} if it would reach {@code floorMap}'s bounds.<br>
	 * {@code 1}: an {@code int} representing the distance it took to collide with said object (or, 
	 * the distance it took to reach {@code floorMap}'s bounds {@code - 2}.
	 */
	public Object[] maxStrideUntilCollision(int i, int j, ForgeDirection dir, AbstractList2D<AbstractFloorPart> floorMap){
		int n = 0;
		AbstractFloorPart part = null;
		while(true){
			int x = i + (n+1)*dir.offsetX;
			if(x < 2 || x > floorMap.maxX()-2){
				part = null;
				break;
			}
			int z = j + (n+1)*dir.offsetZ;
			if(z < 2 || z > floorMap.maxZ() -2){
				part = null;
				break;
			}
			part = floorMap.get(x, z);
			if(part == null || !(part instanceof WallMarker))
				break;
			n++;
		}
		return new Object[]{part, n+1};
	}

	@Override
	public ForgeDirection getRandomEdgePoint(Random random, int[] store, boolean evensOnly) {
		int[] temp = path.randomPoint(random);
		store[0] = temp[0];
		store[1] = temp[1];
		return path.get(store[0], store[1]);
	}

	@Override
	public AbstractList2D<ForgeDirection> getAllEdgePoints(boolean evensOnly){
		return path;
	}
	
	public static AbstractList2D<Integer> testingMap(Collection<HallMarker> halls){
		System.out.println("Hall list length = " + halls.size());
		AbstractList2D<Integer> result = new MappedList2D();
		int b = 0;
		for(HallMarker hall : halls){
			int x0 = hall.part1.x + (WorldHelper.isHorizontal(hall.mainDir) ? (WorldHelper.isNegative(hall.mainDir) ? 0 : hall.part1.width) : hall.part1.width/2);
			int z0 = hall.part1.z + (!WorldHelper.isHorizontal(hall.mainDir) ? (WorldHelper.isNegative(hall.mainDir) ? 0 : hall.part1.length) : hall.part1.length/2);
			
			int width = (!WorldHelper.isHorizontal(hall.mainDir)) ? 1 : (hall.part2.x + (WorldHelper.isNegative(hall.mainDir) ? hall.part2.width : 0)) - x0;
			int length = (WorldHelper.isHorizontal(hall.mainDir)) ? 1 : (hall.part2.z + (WorldHelper.isNegative(hall.mainDir) ? hall.part2.length : 0)) -z0;
			result.addRect(x0, z0, width, length, b++, true);
		}
		return result;
	}
	
	public static String getLegalLinkDirsAbbrev(RoomMarker room, AbstractList2D<RoomMarker> rooms){
		String result = "NWSE";
		if(room.xBase == 0)
			result = result.replace("W", "");
		if(room.zBase == 0)
			result = result.replace("N", "");
		if(room.xBase+room.xUnits >= rooms.maxX()+1)
			result = result.replace("E", "");
		if(room.zBase+room.zUnits >= rooms.maxZ()+1)
			result = result.replace("S", "");
		return result;
	}
	
	public static ForgeDirection[] getLegalLinkDirs(RoomMarker room, AbstractList2D<RoomMarker> rooms){
		Collection<ForgeDirection> legalDirs = new ArrayList(Arrays.asList(WorldHelper.NWSE));
		if(room.xBase == 0)
			legalDirs.remove(ForgeDirection.WEST);
		if(room.zBase == 0)
			legalDirs.remove(ForgeDirection.NORTH);
		if(room.xBase+room.xUnits >= rooms.maxX()+1)
			legalDirs.remove(ForgeDirection.EAST);
		if(room.zBase+room.zUnits >= rooms.maxZ()+1)
			legalDirs.remove(ForgeDirection.SOUTH);
		return legalDirs.toArray(new ForgeDirection[]{});
	}


	@Override
	public void joinWith(AbstractFloorPart other) {
		part1.joinWith(other);
		//only need for part1 to join, because part2 will already be in part 1's group, and thus have "joinWith" called automatically
		//part2.joinWith(other);
	}
	
}
