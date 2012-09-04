package pixelmon.spawning;

import net.minecraft.src.WeightedRandomItem;
import pixelmon.config.PixelmonEntityList.ClassType;

public class SpawnData extends WeightedRandomItem{
	public String name;
	public int rarity;
	public ClassType type;

	public SpawnData(String n, int r, ClassType t) {
		super(r);
		name = n;
		rarity = r;
		type = t;
	}
}
