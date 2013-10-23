package pixelmon.worldGeneration.mysteryDungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import pixelmon.RandomHelper;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.BooleanOp;
import pixelmon.util.CommonHelper;
import pixelmon.util.FunctionHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

/**
 * A pseudo-clone of the Mystery Dungeon generator in the Pokemon games.
 * The prime difference between this and the ones in the Pokemon games is that this
 * generator has a possibility to generate slightly-crazier hallways, but other than
 * that, (and the fact that ladders need to be used instead of stairs) it is the roughly the same.
 * <br>
 * <br>
 * {@code MysteryDungeonFloor} is currently hard-coded at {@code (width, length) = (65, 65)}, but 
 * shouldn't be too difficult to change that, if it were necessary.
 */
public class MysteryDungeonFloor {
	public static final String STRING_0 = "[0,0,1,1, 0,1,1,1, 1,0,2,2, 3,0,1,1, 3,1,1,1]";
	
	public static final byte B0 = 0, B1 = 1, B2 = 2, B3 = 3, B4 = 4, B5 = 5;
	public static final Number[]
			VALID_2 = new Number[]{null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, B0, B0, B0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, null, null},
			VALID_3 = new Number[]{null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, B0, B0, B0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, B1, B1, B1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, null, null, null, null},
			VALID_4 = new Number[]{null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, B0, B0, B0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, B1, B1, B1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, B2, B2, B2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, null, null},
			VALID_COMMON = new Integer[]{null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 1, 1, 1, null, null, null, 2, 2, 2, 2, 2, 2, 2, null, null, null, 3, 3, 3, 3, 3, 3, 3, null, null, null, 4, 4, 4, null, null, null, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, null, null, null, null},
			MAXWIDTHS = {null, null, 29, 17, 13};
	public static final Number[][] VALID_LOOKUP = {null, null, VALID_2, VALID_3, VALID_4};
	public static final int[][] BOUNDS_LOOKUP = {
		null,
		null,
		{2, 62},
		{4, 60},
		{2, 62},
	};
	public static final int[] BOUNDS_COMMON = {2, 62};
	public static final int ID_ROOM = -1, ID_HALL = -2, ID_WALL = -3, ID_LADDERSHAFT_UP = -4, ID_LADDERSHAFT_DOWN = -5, ID_LADDERSIDE = -6, ID_ANTICHEAT = -7;
	
	private static final int[] OFFSETS = {-1, 1};
	public static final AbstractList2D<Integer> antiCheatRing = new RoomMarker(0, 0,66,66, 0, false).getAllEdgePoints(false).replace(ID_ANTICHEAT),
												outerLayer = new RoomMarker(-1,-1,68,68,0,false).getAllEdgePoints(false).replace(ID_WALL);
	
	protected AbstractList2D<RoomMarker> layout;
	public AbstractList2D<Integer> theMap;
	protected AbstractList2D<Object> choosables = new MappedList2D();
	public final int width, length;
	protected int columns, rows;
	protected final boolean ladderShaftOverwrite;
	
	
	protected final int[] ensureStairs, myStairs = new int[3];
	
	/**
	 * The number of rooms this floor has.<br>
	 * Initialized in the {@code private} method "{@code applyRooms(...)}".
	 */
	protected Integer numRooms;
	
	
	public MysteryDungeonFloor(int width, int length, Random random, boolean ladderShaftOverwrite){
		this.width = width;
		this.length = length;
		this.layout = new MappedList2D(width, length, RoomMarker.BLANK);
		this.columns = RandomHelper.useRandomForNumberBetween(random, 2, 4);
		this.rows = RandomHelper.useRandomForNumberBetween(random, 2, 4);
		this.ensureStairs = null;
		this.ladderShaftOverwrite = ladderShaftOverwrite;
	}
	
	public MysteryDungeonFloor(int width, int length, int[] ensureStairs, Random random, boolean ladderShaftOverwrite){
		this.width = width;
		this.length = length;
		this.layout = new MappedList2D(width, length, RoomMarker.BLANK);
		this.ensureStairs = ensureStairs;
		this.ladderShaftOverwrite = ladderShaftOverwrite;
		ensureColRow(random);
		initStairsRoom(random);
	}
	
	/**
	 * ensures that the Column/Row values for this floor will be suitable for the ladder point to spawn
	 */
	protected void ensureColRow(Random random){
			int stairsX = ensureStairs[0];
			int stairsZ = ensureStairs[1];
					ArrayList<Integer> validColumns = new ArrayList();
					ArrayList<Integer> validRows = new ArrayList();
					for(int i = 2; i<=4; i++){
						if(isInBounds(stairsX, i))
							validColumns.add(i);
						if(isInBounds(stairsZ, i))
							validRows.add(i);
					}
					this.columns = validColumns.get(random.nextInt(validColumns.size()));
					this.rows = validRows.get(random.nextInt(validRows.size()));
	}
	
	/**
	 * @param storyHeight - ceiling height of a floor
	 * @return an int[] representing the newly established point for this floor's stairs,
	 * or null, if none of the valid points were in-bounds of any of the valid row/column 
	 * boundaries (this would only be possible if the ceiling height was way too high) 
	 */
	protected int[] ensureStairs0(int storyHeight, Random random){
			int stairsX = 0;
			int stairsZ = 0;
			ArrayList<ForgeDirection> dirs = new ArrayList(Arrays.asList(WorldHelper.NWSE));
			while(!dirs.isEmpty()){
				ForgeDirection dir = dirs.remove(random.nextInt(dirs.size()));
				stairsX = ensureStairs[0] + dir.offsetX*(storyHeight-1);
				stairsZ = ensureStairs[1] + dir.offsetZ*(storyHeight-1);
				if(!isInCommonBounds(stairsX, stairsZ))
					continue;
				else{
					ArrayList<Integer> validColumns = new ArrayList();
					ArrayList<Integer> validRows = new ArrayList();
					for(int i = 2; i<=4; i++){
						if(isInBounds(stairsX, i))
							validColumns.add(i);
						if(isInBounds(stairsZ, i))
							validRows.add(i);
					}
					this.columns = validColumns.get(random.nextInt(validColumns.size()));
					this.rows = validRows.get(random.nextInt(validRows.size()));
					return new int[]{stairsX, stairsZ};
				}
			}
			System.out.println("UH OH, " + Arrays.toString(ensureStairs));
				return null;
	}
	
	protected boolean isInBounds(int i, int lookup){
		return i >= BOUNDS_LOOKUP[lookup][0] && i <= BOUNDS_LOOKUP[lookup][1];
	}
	
	protected boolean isInBounds(int i, int j, int lookup){
		return isInBounds(i, j, BOUNDS_LOOKUP[lookup]);
	}
	protected boolean isInCommonBounds(int i, int j){
		return isInBounds(i, j, BOUNDS_COMMON);
	}
	
	private boolean isInBounds(int i, int j, int[] bounds){
		return i >= bounds[0] && i <= bounds[1] &&
				j >= bounds[0] && j <= bounds[1];
	}
	
	protected void initStairsRoom(Random random){
		int xBase = VALID_LOOKUP[columns][ensureStairs[0]].intValue();
		int zBase = VALID_LOOKUP[rows][ensureStairs[1]].intValue();
		
		int xUnits = isStandardPoint(ensureStairs[0], VALID_LOOKUP[columns]) ? 1 : 2;
		int zUnits = isStandardPoint(ensureStairs[1], VALID_LOOKUP[rows])? 1 : 2;
		
		int wBase = width / (columns*2);
		int lBase = length / (rows*2);
		
		int midPushX = ((width - ((columns*2-1)*wBase))/2);
		int midPushZ = ((length - ((rows*2-1)*lBase))/2);
		
		int xMul = wBase*2; //Value to use for multiplying the x value to fit the room's scale
		int zMul = lBase*2; //Value to use for multiplying the z value to fit the room's scale
		
		int minX = (xBase == 0 && wBase/2 > midPushX) ? -midPushX+1 : -wBase/2+1;
		int maxX = (xBase == columns-1 && wBase/2 > midPushX) ? midPushX-1 : wBase/2-1;

		int minZ = (zBase == 0 && lBase/2 > midPushZ) ? -midPushZ+1 : -lBase/2+1;
		int maxZ = (zBase == rows-1 && lBase/2 > midPushZ) ? midPushZ-1 : lBase/2-1;

		int xOffset = RandomHelper.useRandomForNumberBetween(random, minX, maxX);
		int zOffset = RandomHelper.useRandomForNumberBetween(random, minZ, maxZ);

		int maxW = maxX - xOffset;
		int maxL = maxZ - zOffset;

		int widthOffset = RandomHelper.useRandomForNumberBetween(random, 0, maxW);
		int lengthOffset = RandomHelper.useRandomForNumberBetween(random, 0, maxL);
		
		int x0 = (1+xMul*xBase + midPushX + xOffset)&-2;
		int z0 = (1+zMul*zBase + midPushZ + zOffset)&-2;
		if(x0 <= 0) x0=2;
		if(x0 > ensureStairs[0])
			x0 = ensureStairs[0]&-2;
		if(z0 <= 0) z0=2;
		if(z0 > ensureStairs[1])
			z0 = ensureStairs[1]&-2;
		
		int roomW = -1 + ((wBase*(xUnits+xUnits/2) + widthOffset)&-2);
		if(x0+roomW < ensureStairs[0])
			roomW = 1+((ensureStairs[0]-x0)&-2);
		int roomL = -1 + ((lBase*(zUnits+zUnits/2) + lengthOffset)&-2);
		if(z0+roomL < ensureStairs[1])
			roomL = 1+((ensureStairs[1]-z0)&-2);
		RoomMarker stairsRoom = new RoomMarker(xBase, zBase, xUnits, zUnits, 0, false).refactor(x0, z0, roomW, roomL);
		layout.addRect(xBase, zBase, xUnits, zUnits, stairsRoom, true);
	}
	
	protected boolean isStandardPoint(int i, Number[] strip){
		return strip[i] instanceof Integer; //Otherwise, it's a Byte
	}

	
	/**
	 * This is it. The main floor generator. Generates a buncha rooms and links 'em with
	 * hallways. Also assigns some values to this {@code MysteryDungeonFloor}'s variables.
	 * @param random - {@link Random} object for generating random values. What did you expect?
	 * @param maxRoomSizeMultiplier - The max amount of 'grid units' a room can engulf
	 * on a single axis. This is not the limit of the size of a room, rather, a limit of the 
	 * room's scale on each axis, when compared to a regular room's size
	 * @return The complete floor map: rooms, hallways, etc.
	 */
	public AbstractList2D<Integer> floorMain(Random random, int maxRoomSizeMultiplier){
		AbstractList2D<AbstractFloorPart> semifinal = new MappedList2D(width, length, WallMarker.MARK);
		
		//System.out.println("(columns, rows) = " + PixelmonDebug.coordStyleInts(columns, rows));
		startRooms(random, columns, rows, maxRoomSizeMultiplier);
		layout = AbstractList2D.copyWithout(layout, RoomMarker.BLANK);
		
		int wBase = width / (columns*2);
		int lBase = length / (rows*2);
		applyRooms(semifinal, columns, rows, wBase, lBase, random);
		initChoosables();
		int[] tempLadder = this.randomPoint(random, true);
		System.arraycopy(tempLadder, 0, myStairs, 0, 2);
		Collection<HallMarker> halls = linkAllRooms(random);
		for(HallMarker hall : halls){
			hall.hallStart(layout, semifinal, random, wBase, lBase);
		}
		for(HallMarker hall : halls){
			hall.touchup(semifinal, random);
		}
		AbstractList2D<Integer> floorMap = convertMap(semifinal);
		applyLadder(floorMap, random);
		return (this.theMap = floorMap.combine(antiCheatRing, true).combine(outerLayer, true));
	}

	protected static AbstractList2D<Integer> convertMap(AbstractList2D<AbstractFloorPart> floorMap){
		AbstractList2D<Integer> result = floorMap.createNew();
		for(int i : floorMap.xList()){
			for(int j : floorMap.zList(i)){
				AbstractFloorPart value = floorMap.get(i, j);
				int id = value instanceof RoomMarker ? ID_ROOM : value instanceof HallMarker ? ID_HALL : ID_WALL;
				result.addValue(i, j, id);
			}
		}
		return result;
	}
	
	public void startRooms(Random random, int columns, int rows, int maxSize){
		initRooms(random, columns, rows);
		enlargeRooms(random, maxSize, columns - 1, rows - 1);
	}



	public void initRooms(Random random, int columns, int rows){
		ArrayList<Integer> tickets = new ArrayList();
		for(int i = 0; i < columns*rows; i++)
			tickets.add(i,i);
		int roomCounter = RandomHelper.useRandomForNumberBetween(random, (columns*rows)/2, columns*rows);
		if(ensureStairs != null) //Then we already have a room from initStairsRooms(...).
			roomCounter--;
		int roomID = 0;
		
		while(roomCounter > 0){
			int i = tickets.remove(random.nextInt(tickets.size()));
			int xi = i/rows;
			int zi = i%rows;
			if(layout.get(xi, zi).canModify){
				layout.addValue(xi, zi, new RoomMarker(xi,zi,1,1,roomID++, true));
			}
			roomCounter--;
		}
	}

	public void enlargeRooms( Random random, int maxSize, int maxX, int maxZ){
		for(int i : layout.xList()){
			for(int j : layout.zList(i)){
				RoomMarker room = layout.get(i, j);
				if(room == RoomMarker.BLANK || room.used)
					continue;
				attemptExpansion(layout, i, j, random, room, maxSize, maxX, maxZ);
			}
		}
	}
	
	private void attemptExpansion(AbstractList2D<RoomMarker> rooms,
			int i, int j, Random random, RoomMarker room, int maxSize, int maxX, int maxZ) {
		for(ForgeDirection dir : WorldHelper.NWSE){
			if(random.nextInt(11)==0){
				if(room.canExpand(rooms, i, j, dir, maxSize, maxX, maxZ)){
					room.expand(rooms, i, j, dir);
				}
			}
		}
	}
	
	private void applyRooms(AbstractList2D<AbstractFloorPart> floorMap, int columns, int rows, int wBase, int lBase, Random random){
		
		int midPushX = ((width - ((columns*2-1)*wBase))/2);
		int midPushZ = ((length - ((rows*2-1)*lBase))/2);
		int xMul = wBase*2; //Value to use for multiplying the x value to fit the room's scale
		int zMul = lBase*2; //Value to use for multiplying the z value to fit the room's scale
		Set<RoomMarker> roomMarkers = layout.toSet();
		this.numRooms = roomMarkers.size();
		for(RoomMarker marker: roomMarkers){
			if(marker.canModify){
				int minX = (marker.xBase == 0 && wBase/2 > midPushX) ? -midPushX+1 : -wBase/2+1;
				int maxX = (marker.xBase == columns-1 && wBase/2 > midPushX) ? midPushX-1 : wBase/2-1;

				int minZ = (marker.zBase == 0 && lBase/2 > midPushZ) ? -midPushZ+1 : -lBase/2+1;
				int maxZ = (marker.zBase == rows-1 && lBase/2 > midPushZ) ? midPushZ-1 : lBase/2-1;

				int xOffset = RandomHelper.useRandomForNumberBetween(random, minX, maxX);
				int zOffset = RandomHelper.useRandomForNumberBetween(random, minZ, maxZ);

				int maxW = maxX - xOffset;
				int maxL = maxZ - zOffset;

				int widthOffset = RandomHelper.useRandomForNumberBetween(random, 0, maxW);
				int lengthOffset = RandomHelper.useRandomForNumberBetween(random, 0, maxL);

				int x0 = (1+xMul*marker.xBase + midPushX + xOffset)&-2;
				int z0 = (1+zMul*marker.zBase + midPushZ + zOffset)&-2;
				if(x0 <= 0) x0=2;
				if(z0 <= 0) z0=2;
				int roomW = -1 + ((wBase*(marker.xUnits+marker.xUnits/2) + widthOffset)&-2);
				int roomL = -1 + ((lBase*(marker.zUnits+marker.zUnits/2) + lengthOffset)&-2);
				
				marker.refactor(x0, z0, roomW, roomL);
			}
			floorMap.addRect(marker.x, marker.z, marker.width, marker.length, marker, true);
			
		}
	}
	
	
	private void applyLadder(AbstractList2D<Integer> floorMap, Random random) {
		floorMap.addValue(myStairs[0], myStairs[1], ID_LADDERSHAFT_UP);
		int[] shaftSide = chooseValidShaftSide(myStairs, floorMap, random);
		System.out.println(this.myStairs[2]);
		if(ladderShaftOverwrite || floorMap.get(shaftSide[0], shaftSide[1]) != ID_WALL)
			floorMap.addValue(shaftSide[0], shaftSide[1], ID_LADDERSIDE);
		if(this.ensureStairs != null){
			floorMap.addValue(ensureStairs[0], ensureStairs[1], ID_LADDERSHAFT_DOWN);
		}
	}
	
	private int[] chooseValidShaftSide(int[] point, AbstractList2D<Integer> floorMap, Random random){
		ArrayList<ForgeDirection> dirs = new ArrayList();
		for(ForgeDirection dir : WorldHelper.NWSE){
			Integer candidate = floorMap.get(point[0]+dir.offsetX, point[1]+dir.offsetZ);
			if(candidate == ID_WALL || candidate == ID_ROOM || candidate == ID_LADDERSIDE)
				dirs.add(dir);
		}
		ForgeDirection side = dirs.get(random.nextInt(dirs.size()));
		point[2] = side.getOpposite().ordinal();
		return new int[]{point[0]+side.offsetX, point[1]+side.offsetZ};
	}
	
	public static String encodeValidStrip(int length, int colrow){
		Map<Integer, ?> strip = getValid1x1RoomStrip(length, colrow);
		Integer[] temp = new Integer[length];
		int n = -1;
		Object last = null;
		for(int i = 0; i < length; i++){
			Object value = strip.get(i);
			if(last == null && value!= null)
				n++;
			temp[i] = value == null ? null : n;
			last = value;
		}
		return Arrays.toString(temp).replace('[', '{').replace(']', '}');
	}
	
	public static String encodeCommon(int length, int... ints){
		Map<Integer, ?>[] maps = new Map[ints.length];
		for(int i = 0; i < ints.length; i++){
			maps[i] = getValid1x1RoomStrip(length, ints[i]);
		}
		Map<Integer, Boolean> common = new TreeMap();
		for(int i = 0; i < length; i++){
			for(int j = 0; j < ints.length; j++){
				if(maps[j].containsKey(i)){
					if(j == ints.length - 1)
						common.put(i, true);
					else continue;
				}
				else break;
			}
		}
		Integer[] temp = new Integer[length];
		int n = -1;
		Object last = null;
		for(int i = 0; i < length; i++){
			Object value = common.get(i);
			if(last == null && value != null)
				n++;
			temp[i] = value == null ? null : n;
			last = value;
		}
		return Arrays.toString(temp).replace('[', '{').replace(']', '}');
	}
	
	public static Map<Integer, Boolean> getValid1x1RoomStrip(int length, int colrow){
		Map<Integer, Boolean> result = new TreeMap();
		int base = length / (colrow*2);
		int mul = base*2;
		int midPush = ((length - ((colrow*2-1)*base))/2);
		for(int i = 0; i < colrow; i++){
			int min = (i == 0 && base/2 > midPush) ? -midPush+1 : -base/2+1;
			int max = (i == colrow-1 &&base/2 > midPush) ? midPush-1 : base/2-1;
			int length0 =  max - min;
			int x0 = (1+(mul*i + midPush) + min)&-2;
			int l0 = -1 + ((base + length0)&-2);
			//System.out.println(l0);
			for(int j = 0; j < l0; j++){
				result.put(x0+j, true);
			}
		}
		return result;
	}
	public static AbstractList2D<Integer> getValid1x1RoomAreas(int width, int length, int columns, int rows){
		AbstractList2D<Integer> result = new MappedList2D();
		int wBase = width / (columns*2);
		int lBase = length / (rows*2);
		int xMul = wBase*2; //Value to use for multiplying the x value to fit the room's scale
		int zMul = lBase*2; //Value to use for multiplying the z value to fit the room's scale
		int midPushX = ((width - ((columns*2-1)*wBase))/2);
		int midPushZ = ((length - ((rows*2-1)*lBase))/2);
		for(int i = 0; i < columns; i++){
			for(int j = 0; j < rows; j++){
				int minX = (i == 0 && wBase/2 > midPushX) ? -midPushX+1 : -wBase/2+1;
				int maxX = (i == columns-1 && wBase/2 > midPushX) ? midPushX-1 : wBase/2-1;

				int minZ = (j == 0 && lBase/2 > midPushZ) ? -midPushZ+1 : -lBase/2+1;
				int maxZ = (j == rows-1 && lBase/2 > midPushZ) ? midPushZ-1 : lBase/2-1;
				
				int width0 = maxX - minX;
				int length0 = maxZ - minZ;
				
				int x0 = (1+(xMul*i + midPushX) + minX)&-2;
				int z0 = (1+(zMul*j + midPushZ) + minZ)&-2;
				
				int w0 = -1 + ((wBase + width0)&-2);
				int l0 = -1 + ((lBase + length0)&-2);
				
				result.addRect(x0, z0, w0, l0, 1, true);
			}
		}
		return result;
	}
	
	private static void link2Rooms(RoomMarker room1, RoomMarker room2, Collection<HallMarker> halls, Random random, ForgeDirection... hallDirs){
		room2.joinWith(room1);
		halls.add(new HallMarker(room1, room2, random, hallDirs));
	}
	
	public Collection<HallMarker> linkAllRooms(Random random){
		Collection<HallMarker> halls = new ArrayList<HallMarker>();
		Collection<RoomMarker> roomsList = layout.toList();
		for(RoomMarker rm : roomsList)
			rm.reset();
		for(RoomMarker room : roomsList){
			ForgeDirection[] legalDirs = HallMarker.getLegalLinkDirs(room, layout);
			for(ForgeDirection dir : legalDirs){
				RoomMarker linkee = getFirstRoomInDirection(room, layout, dir);
				if(linkee != null && !linkee.used && random.nextInt(20) < 17){
					link2Rooms(room, linkee, halls, random, dir);
					//if(random.nextInt(20) == 0)
						//halls.add(new HallMarker(room, linkee, dir));
				}
			}
			room.used = true;
		}
		ensureConnections(roomsList, halls, random);
		return halls;
	}
	
	

	
	/**
	 * Creates additional HallMarkers, if necessary, to guarantee that all rooms are 
	 * accessible.
	 */
	private void ensureConnections(Collection<RoomMarker> roomsList, Collection<HallMarker> halls, Random random){
		Object groupObject = null;
		for(RoomMarker room : roomsList){
			if(groupObject == null)
				groupObject = room.sharedObj;
			else{
				if(room.sharedObj != groupObject){
					if(!planB(room, roomsList, halls, random)){
						planC(room, halls, random);
					}
				}
			}
		}
	}
	
	/**
	 * finds the closest {@code RoomMarker} that 
	 * {@link RoomMarker#canLinkTo(RoomMarker) room can link to} and <b>also</b>
	 * {@link RoomMarker#isAlignedWith(RoomMarker) room is not aligned with} and links them with a new
	 * {@code HallMarker}
	 */
	protected static boolean planB(RoomMarker room, Collection<RoomMarker> roomsList, Collection<HallMarker> halls, Random random){
		RoomMarker linkee = null;
		float distance = 0;
		for(RoomMarker marker : roomsList){
			if(linkee == null){
				if(marker != room && room.canLinkTo(marker)){
					linkee = marker;
					distance = (float) FunctionHelper.dist(room.xBase, room.zBase, marker.xBase, marker.zBase);
				}
			}
			else if(marker != room && room.canLinkTo(marker) && !room.isAlignedWith(marker)){
				float tempDist = (float) FunctionHelper.dist(room.xBase, room.zBase, marker.xBase, marker.zBase);
				if(tempDist < distance){
					linkee = marker;
					distance = tempDist;
				}
			}
		}
		if(linkee == null)
			return false;
		int xDist = linkee.xBase - room.xBase;
		int zDist = linkee.zBase - room.zBase;
		ForgeDirection[] dirs = WorldHelper.dirsFromOffsets(xDist, zDist);
		link2Rooms(room, linkee, halls, random, dirs);
		return true;
	}
	
	protected boolean planC(RoomMarker room, Collection<HallMarker> halls, Random random){
		RoomMarker linkee = null;
		int dist = -1;
		ForgeDirection hallDir = null;
		for(ForgeDirection dir : WorldHelper.NWSE){
			RoomMarker marker = getFirstRoomInDirection(room, layout, dir);
			if(marker != null && room.canLinkTo(marker)){
				if(linkee == null){
					dist = room.getAlignedDistance(marker);
					linkee = marker;
					hallDir = dir;
				}
				else{
					int tempDist = room.getAlignedDistance(marker);
					if(tempDist < dist || (tempDist == dist && random.nextInt(4) == 0)){
						dist = tempDist;
						linkee = marker;
						hallDir = dir;
					}
				}
			}
		}
		if(linkee == null)
			return false;
		link2Rooms(room, linkee, halls, random, hallDir);
		return true;
		
	}
	
	
	private static RoomMarker getFirstRoomInDirection(RoomMarker room, AbstractList2D<RoomMarker> rooms, ForgeDirection dir) {
		int i = 1;
		while(true){
			int x0 = room.xBase + i*dir.offsetX;
			int z0 = room.zBase + i*dir.offsetZ;
			if(!rooms.isInBounds(x0, z0))
				return null;
			RoomMarker other = rooms.get(x0, z0);
			if(other != null && other != room)
				return other;
			i++;
		}
	}
	
	public int roomCount(){
		if(this.numRooms == null){
			Set<RoomMarker> roomSet = layout.toSet();
			this.numRooms = roomSet.size();
		}
		return this.numRooms;
	}
	
	public int[] myStairsPoint(){
		return this.myStairs;
	}
	
	public int[] ensureStairsPoint(){
		return this.ensureStairs;
	}
	
	public TreeSet<String> describeLayout(){
		return describeLayout(this.layout.toSet());
	}
	
	public static TreeSet<String> describeLayout(Set<RoomMarker> roomSet){
		TreeSet<String> result = new TreeSet();
		for(RoomMarker room : roomSet){
			String description = String.format("%s,%s,%s,%s", room.xBase, room.zBase, room.xUnits, room.zUnits);
			result.add(description);
		}
		return result;
	}
	
	public RoomMarker randomOuterMostRoom(Random random, ForgeDirection side){
		ArrayList<RoomMarker> roomList = new ArrayList();
		TreeSet<Integer> stripAsSet = null;
		boolean hortz = WorldHelper.isHorizontal(side);
		boolean negative = WorldHelper.isNegative(side);
		int i = negative ? 0 : hortz ? columns-1 : rows-1;
		//int reach = negative ? hortz ? columns : rows: -1;
		int step = negative ? 1 : -1;
		//System.out.println("i, reach, step = " + PixelmonDebug.coordStyleInts(i, reach, step));
		while(stripAsSet == null){
			boolean contains = hortz ? layout.containsX(i) : layout.containsZ(i);
			if(contains){
				stripAsSet = hortz ? layout.zList(i) : layout.xList(i);
				break;
			}
			i+=step;
		}
		ArrayList<Integer> strip = new ArrayList(stripAsSet);
		//System.out.println("Strip size is " + strip.size());
		int j = strip.get(random.nextInt(strip.size()));
		//System.out.println("Thus, we are returning the value at " + (hortz ? PixelmonDebug.coordStyleInts(i, j) : PixelmonDebug.coordStyleInts(j, i)));
		return hortz ? layout.get(i, j) : layout.get(j, i);
	}
	
	/**
	 * @param random
	 * @return a randomly-chosen room out of all the rooms on this floor. Each room is given equal chance
	 * to be picked (meaning room size does not affect the chance to be picked)
	 */
	public RoomMarker randomRoom(Random random){
		ArrayList<RoomMarker> roomList = new ArrayList(layout.toSet());
		return roomList.get(random.nextInt(roomList.size()));
	}
	
	protected void initChoosables(){
		Set<RoomMarker> roomSet = layout.toSet();
		boolean flag = this.func_asdf();
		for(RoomMarker room : roomSet){
			if(flag && room.xUnits == 2)
				continue;
			for(int i = 2; i <= room.width-2; i+=2){
				for(int j = 2; j <= room.length-2; j+=2){
					choosables.addValue(i+room.x, j+room.z, CommonHelper.THING);
				}
			}
		}
		if(ensureStairs != null)
			choosables.remove(ensureStairs[0], ensureStairs[1]);
	}

	/**
	 * @param random
	 * @return a randomly-chosen point on this floor.
	 * The outer-two 'rings' of coordinates in a room are omitted from the possible results, because this
	 * method is used to determine a ladder point. Omitting the outer two rings greatly simplifies the 
	 * functionality of other things, such as creating an entrance to the dungeon, without unintentionally
	 * screwing with the accessibility of the ladder. In other words, the outer two 'rings' of room
	 * coordinates are 'fair game' for other things to intersect with, without causing any damage.
	 */
	public int[] randomPoint(Random random, boolean remove){
		int[] point = choosables.randomPoint(random);
		if(remove)
			choosables.remove(point[0], point[1]);
		return point;
	}
	
	public AbstractList2D getChoosablePoints(){
		return this.choosables;
	}
	
	public int[] randomRoomEdgePoint(Random random, boolean evensOnly){
		ArrayList<RoomMarker> roomList = new ArrayList(layout.toSet());
		RoomMarker room = roomList.get(random.nextInt(roomList.size()));
		int[] result = new int[2];
		room.getRandomEdgePoint(random, result, true);
		return result;
	}
	
	public boolean matchesLayout(Set<RoomMarker> compare){
		String describeThis = describeLayout(this.layout.toSet()).toString();
		String describeThat = describeLayout(compare).toString();
		return describeThis.equals(describeThat);
	}
	
	public static boolean isDangerBlock(int x, int z){
		return antiCheatRing.contains(x, z);
	}
	
	public static boolean func_jkl(Set<RoomMarker> asdf){
		return describeLayout(asdf).toString().equals(STRING_0);
	}
	
	public boolean func_asdf(){
		return this.describeLayout().toString().equals(STRING_0);
	}

}
