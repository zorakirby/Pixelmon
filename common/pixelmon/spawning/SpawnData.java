package pixelmon.spawning;

import pixelmon.config.PixelmonEntityList.ClassType;

public class SpawnData {
	public String name;
	public int rarity;
	public ClassType type;

	public SpawnData(String n, int r, ClassType t) {
		name = n;
		rarity = r;
		type = t;
	}
}
