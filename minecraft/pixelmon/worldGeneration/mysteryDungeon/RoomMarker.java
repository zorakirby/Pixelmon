package pixelmon.worldGeneration.mysteryDungeon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;
import net.minecraftforge.common.ForgeDirection;

public class RoomMarker extends AbstractFloorPart{
	
	public int xBase, zBase, xUnits, zUnits, id;
	public int x, z, width, length;
	public boolean used = false;
	public static final RoomMarker BLANK = new RoomMarker(-1,-1,-1,-1,-1, true);
	protected static boolean avoidCorners = false;
	public Object sharedObj = new Object();
	public ArrayList<RoomMarker> group = new ArrayList();
	public final boolean canModify;

	
	public RoomMarker(int x, int z, int width, int length, int id, boolean canChange){
		this.canModify = canChange;
		this.xBase = x;
		this.x = x;
		this.zBase = z;
		this.z = z;
		this.xUnits = width;
		this.width = width;
		this.zUnits = length;
		this.length = length;
		this.id = id;
		group.add(this);
	}

	
	public RoomMarker refactor(int x, int z, int w, int l){
		this.x = x;
		this.z = z;
		this.width = w;
		this.length = l;
		return this;
	}
	
	public void joinWith(AbstractFloorPart part){
		if(part instanceof RoomMarker){
			joinRoom((RoomMarker) part);
		}
		if(part instanceof HallMarker){
			joinRoom(((HallMarker)part).part1);
		}
	}
	
	public void joinRoom(RoomMarker other){
		if(this.sharedObj != other.sharedObj){
			other.group.addAll(this.group);
			for(RoomMarker room : group){
				room.sharedObj = other.sharedObj;
				room.group = other.group;
			}
			this.sharedObj = other.sharedObj;
			this.group = other.group;
		}
	}
	
	public void reset(){
		this.used = false;
	}

	
	public void expand(AbstractList2D<RoomMarker> rooms, int i, int j, ForgeDirection dir){
		if(dir.offsetX < 0)
			this.xBase--;
		if(dir.offsetZ < 0)
			this.zBase--;
		this.xUnits += Math.abs(dir.offsetX);
		this.zUnits += Math.abs(dir.offsetZ);
		
		boolean hortz = WorldHelper.isHorizontal(dir);
		int stride = hortz ? zUnits : xUnits;
		for(int n = 0; n < stride; n++){
			int xi = hortz ? (i + dir.offsetX) : (xBase+n);
			int zi = hortz ? (zBase+n) : (j + dir.offsetZ);
			rooms.addValue(xi, zi, this);
		}
		this.used = true;
	}
	
	public boolean canExpand(AbstractList2D<RoomMarker> rooms, int i, int j, ForgeDirection dir, int sizeLimit, int maxX, int maxZ){
		if(xUnits + Math.abs(dir.offsetX) > sizeLimit || zUnits + Math.abs(dir.offsetZ) > sizeLimit)
			return false;		
		boolean hortz = WorldHelper.isHorizontal(dir);
		
		int stride = hortz ? zUnits : xUnits;
		for(int n = 0; n < stride; n++){
			int xi = hortz ? (i + dir.offsetX) : (xBase+n);
			int zi = hortz ? (zBase+n) : (j + dir.offsetZ);
			if(!isPointValid(rooms, xi, zi, maxX, maxZ))
				return false;
		}
		return true;
	}
	
	protected boolean isPointValid(AbstractList2D<RoomMarker> rooms, int xi, int zi, int maxX, int maxZ){
		if(xi < 0 || xi > maxX || zi < 0 ||zi > maxZ)
			return false;
		RoomMarker roomAt = rooms.get(xi, zi);
		if(roomAt == null || roomAt == BLANK)
			return true;
		if(!roomAt.canModify)
			return false;
		if(roomAt.used && roomAt.xUnits!= 0 && roomAt.zUnits != 0)
			return false;
		return true;
	}
	
	public static AbstractList2D<Integer> to2DIntList(AbstractList2D<RoomMarker> rooms){
		AbstractList2D<Integer> result = new MappedList2D();
		for(int i : rooms.xList()){
			for(int j : rooms.zList(i)){
				result.addValue(i, j, rooms.get(i, j).id);
			}
		}
		return result;
	}
	

	@Override
	public ForgeDirection getRandomEdgePoint(Random random, int[] store, boolean evensOnly) {
		int allowed = (width*2 + length*2) - (avoidCorners ? 9 : 5);
		int i = random.nextInt(allowed);
		return coordsFromEdgeIndex(i, store, evensOnly);
	}
	
	
	public int[] getRandomEdgePoint(Random random, ForgeDirection dir, boolean evensOnly){
		int strip = WorldHelper.isHorizontal(dir) ? length : width;
		strip = random.nextInt(strip);
		if(evensOnly)
			strip &= -2;
		return coordsFromDirectionAndIndex(dir, strip);
	}
	
	@Override
	public AbstractList2D<Integer> getAllEdgePoints(boolean evensOnly){
		AbstractList2D<Integer> result = new MappedList2D();
		int i = (width == 1 || length == 1) ? width*length - 1 : width*2 + length*2 - (avoidCorners ? 9 : 5);
		while(i >= 0){
			int[] coords = new int[2];
			coordsFromEdgeIndex(i, coords, evensOnly);
			result.addValue(coords[0], coords[1], 2);
			i--;
		}
		return result;
	}
	
	public AbstractList2D<Integer> getAllEdgePointsForgeDir(boolean evensOnly){
		AbstractList2D<Integer> result = new MappedList2D();
		for(ForgeDirection dir : WorldHelper.NWSE){
			result.combine(getEdgePointsOnSide(dir, evensOnly), false);
		}
		return result;
	}

	
	public AbstractList2D<Integer> getEdgePointsOnSide(ForgeDirection dir, boolean evensOnly){
		AbstractList2D<Integer> result = new MappedList2D();
		int strip = -1 + (WorldHelper.isHorizontal(dir) ? length : width);
		while(strip >= 0){
			int[] coords = coordsFromDirectionAndIndex(dir, strip);
			result.addValue(coords[0], coords[1], 1);
			strip-=(evensOnly ? 2 : 1);
		}
		return result;
	}
	
	/**
	 * @param edgeIndex : Index of the edge point. The index starts at the point to the 
	 * right of the top left of this marker, at 0, and goes around clockwise 
	 * <i>while skipping <b>corner points</b></i>, reaching the max value, which is:<br>
	 * <code>(width*2 + length*2 - 8) - 1</code>
	 * @return
	 */
	@SuppressWarnings("incomplete-switch")
	public ForgeDirection coordsFromEdgeIndex(int edgeIndex, int[] store, boolean evensOnly){
		if(width == 1){
			store[0] = x;
			store[1] = edgeIndex+z;
			return ForgeDirection.UNKNOWN;
		}
		if(length == 1){
			store[0] = edgeIndex+x;
			store[1] = z;
			return ForgeDirection.UNKNOWN;
		}
		edgeIndex+= avoidCorners ? 2 : 1;
		int dirIndex = 0;
		int strip = width-1;
		ForgeDirection dir = ForgeDirection.EAST;
		while(edgeIndex - strip > 0){
			edgeIndex+=(avoidCorners ? 1 : 0)-strip;
			dirIndex++;
			dir = WorldHelper.ESWN[dirIndex];
			strip = (WorldHelper.isHorizontal(dir) ? width : length)-1;
		}
		//edgeIndex+=dirIndex;
		edgeIndex = (WorldHelper.isNegative(dir) ? ((WorldHelper.isHorizontal(dir) ? width : length) - edgeIndex) : edgeIndex);
		int i = 0;
		int j = 0;
		switch(dir){
		case EAST :
			i = (edgeIndex-1)&(evensOnly? -2 : -1);
			j = 0;
			break;
		case SOUTH :
			i = xUnits-1;
			j = (edgeIndex-1)&(evensOnly? -2 : -1);
			break;
		case WEST :
			i = edgeIndex&(evensOnly? -2 : -1);
			j = zUnits-1;
			break;
		case NORTH:
			i = 0;
			j = edgeIndex&(evensOnly? -2 : -1);
			break;
		}
		store[0] = i+x;
		store[1] = j+z;
		return WorldHelper.ccw(dir);
	}
	
	public int[] coordsFromDirectionAndIndex(ForgeDirection dir, int i) {
		boolean hortz = WorldHelper.isHorizontal(dir);
		int positive = WorldHelper.isNegative(dir) ? 0 : 1;
		int xi = hortz ? (positive * width - positive) : i;
		int zi = hortz ? i : (positive * length - positive);
		return new int[]{xi+x, zi+z};
	}
	
	public int getAlignedDistance(RoomMarker other){
		return Math.abs(other.xBase-this.xBase) + Math.abs(other.zBase - this.zBase);
	}
	
	public boolean canLinkTo(RoomMarker linkee){
		return linkee != null && this.sharedObj != linkee.sharedObj;
	}
	
	public boolean isAlignedWith(RoomMarker linkee){
		return (this.xBase == linkee.xBase || this.zBase == linkee.zBase);
	}
	
	public boolean is1x1Room(){
		return this.xUnits == 1 && this.zUnits == 1;
	}
	
}
