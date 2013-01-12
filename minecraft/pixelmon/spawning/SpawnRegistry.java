package pixelmon.spawning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.SpawnLocation;

public class SpawnRegistry {

	private static HashMap<BiomeGenBase, List<SpawnData>> biomeSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> undergroundSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> biomeWaterSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();

	public static void addSpawn(String name, int rarity, ClassType type) {
		BiomeGenBase[] biomes = null;
		if (type == ClassType.Pixelmon)
			biomes = DatabaseStats.GetSpawnBiomes(name);
		else if (type == ClassType.Trainer)
			biomes = DatabaseTrainers.GetSpawnBiomes(name);

		SpawnLocation[] spawnLocations = DatabaseStats.getSpawnLocations(name);

		if (biomes == null)
			return;
		if (spawnLocations == null) {
			for (BiomeGenBase b : biomes) {
				storeSpawnInfo(biomeSpawns, name, rarity, type, b);
			}
		} else {
			for (SpawnLocation s : spawnLocations) {
				for (BiomeGenBase b : biomes) {
					if (type == ClassType.Pixelmon) {
						if (s == SpawnLocation.Land) {
							storeSpawnInfo(biomeSpawns, name, rarity, type, b);
						} else if (s == SpawnLocation.UnderGround) {
							storeSpawnInfo(undergroundSpawns, name, rarity, type, b);
						} else if (s == SpawnLocation.Water || s == SpawnLocation.OnWater) {
							storeSpawnInfo(biomeWaterSpawns, name, rarity, type, b);
						}
					}
				}
			}
		}
	}

	private static void storeSpawnInfo(HashMap<BiomeGenBase, List<SpawnData>> hashmap, String name, int rarity, ClassType type, BiomeGenBase b) {
		List<SpawnData> spawnList;
		if (hashmap.containsKey(b))
			spawnList = hashmap.get(b);
		else
			spawnList = new ArrayList<SpawnData>();

		spawnList.add(new SpawnData(name, rarity, type));
		hashmap.put(b, spawnList);
	}

	public static List<SpawnData> getSpawnsForBiome(BiomeGenBase b) {
		return biomeSpawns.get(b);
	}

	public static List<SpawnData> getUndergroundSpawnsForBiome(BiomeGenBase b) {
		return undergroundSpawns.get(b);
	}

	public static List<SpawnData> getWaterSpawnsForBiome(BiomeGenBase b) {
		return biomeWaterSpawns.get(b);
	}
}
