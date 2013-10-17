package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraftforge.common.ForgeDirection;
import pixelmon.util.AbstractList2D;

public class WallMarker extends AbstractFloorPart{
	
	public static final WallMarker MARK = new WallMarker();
	
	private WallMarker(){}
	
	

	@Override
	public ForgeDirection getRandomEdgePoint(Random random, int[] store,
			boolean evensOnly) {
		throw new RuntimeException("WallMarkers should not use this method!");
	}

	@Override
	public AbstractList2D getAllEdgePoints(boolean evensOnly) {
		throw new RuntimeException("WallMarkers should not use this method!");
	}

	@Override
	public void joinWith(AbstractFloorPart other) {
		
	}

}
