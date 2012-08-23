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

	private static HashMap<BiomeGenBase, List<String>> biomeSpawns = new HashMap<BiomeGenBase, List<String>>();
	
	public static void addSpawn(Entry entry, String name, ClassType type) {
		BiomeGenBase[] biomes =null;
		if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon) 
			biomes = DatabaseStats.GetSpawnBiomes(name);
		else if (type == ClassType.Trainer)
			biomes = DatabaseTrainers.GetSpawnBiomes(name);
		
		if (biomes ==null) return;
		for (BiomeGenBase b: biomes){
			List<String> spawnList;
			if (biomeSpawns.containsKey(b))
				spawnList = biomeSpawns.get(b);
			else
				spawnList = new ArrayList<String>();
			
			spawnList.add(name);
			biomeSpawns.put(b, spawnList);
		}
	}
	
	public static List<String> getSpawnsForBiome(BiomeGenBase b){
		return biomeSpawns.get(b);
	}
}
