package pixelmon.spawning;

import net.minecraft.util.WeightedRandomItem;
import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.database.SpawnLocation;

public class SpawnData extends WeightedRandomItem{
	public String name;
	public int rarity;
	public ClassType type;
	public SpawnLocation spawnLocation;

	public SpawnData(String n, int r, ClassType t, SpawnLocation s) {
		super(r);
		name = n;
		rarity = r;
		type = t;
		spawnLocation = s;
	}
}
