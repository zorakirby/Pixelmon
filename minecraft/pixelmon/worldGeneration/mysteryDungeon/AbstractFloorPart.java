package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraftforge.common.ForgeDirection;

import pixelmon.util.AbstractList2D;

public abstract class AbstractFloorPart {
	public abstract ForgeDirection getRandomEdgePoint(Random random, int[] store, boolean evensOnly);
	public abstract AbstractList2D getAllEdgePoints(boolean evensOnly);
	public abstract void joinWith(AbstractFloorPart other);
}
