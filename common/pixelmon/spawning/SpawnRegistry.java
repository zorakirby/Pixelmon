package pixelmon.spawning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.src.BiomeGenBase;

import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;

public class SpawnRegistry {

	private static HashMap<BiomeGenBase, List<SpawnData>> biomeSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	
	public static void addSpawn(Entry entry, String name, int rarity, ClassType type) {
		BiomeGenBase[] biomes =null;
		if (type == ClassType.Pixelmon) 
			biomes = DatabaseStats.GetSpawnBiomes(name);
		else if (type == ClassType.Trainer)
			biomes = DatabaseTrainers.GetSpawnBiomes(name);
		
		if (biomes ==null) return;
		for (BiomeGenBase b: biomes){
			List<SpawnData> spawnList;
			if (biomeSpawns.containsKey(b))
				spawnList = biomeSpawns.get(b);
			else
				spawnList = new ArrayList<SpawnData>();
			
			spawnList.add(new SpawnData(name, rarity, type));
			biomeSpawns.put(b, spawnList);
		}
	}
	
	public static List<SpawnData> getSpawnsForBiome(BiomeGenBase b){
		return biomeSpawns.get(b);
	}
}
