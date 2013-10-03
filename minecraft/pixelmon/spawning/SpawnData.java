package pixelmon.spawning;

import net.minecraft.util.WeightedRandomItem;
import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.stats.Rarity;

public class SpawnData{
	public String name;
	public Rarity rarity;
	public ClassType type;
	public SpawnLocation spawnLocation;

	public SpawnData(String n, Rarity rarity2, ClassType t, SpawnLocation s) {
		name = n;
		rarity = rarity2;
		type = t;
		spawnLocation = s;
	}
}
