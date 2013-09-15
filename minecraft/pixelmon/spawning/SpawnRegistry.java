package pixelmon.spawning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.BaseStats;

public class SpawnRegistry {

	private static HashMap<BiomeGenBase, List<SpawnData>> biomeSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> undergroundSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> biomeWaterSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> airSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static DatabaseStats db;

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
				storeSpawnInfo(biomeSpawns, name, rarity, type, b, null);
			}
		} else {
			for (SpawnLocation s : spawnLocations) {
				for (BiomeGenBase b : biomes) {
					if (type == ClassType.Pixelmon) {
						if (s == SpawnLocation.Land || s == SpawnLocation.Air) {
							storeSpawnInfo(biomeSpawns, name, rarity, type, b, s);
						} else if (s == SpawnLocation.AirPersistent) {
							storeSpawnInfo(airSpawns, name, rarity, type, b, s);
						} else if (s == SpawnLocation.UnderGround) {
							storeSpawnInfo(undergroundSpawns, name, rarity, type, b, s);
						} else if (s == SpawnLocation.Water || s == SpawnLocation.OnWater) {
							storeSpawnInfo(biomeWaterSpawns, name, rarity, type, b, s);
						}
					}
				}
			}
		}

	}

	private static void storeSpawnInfo(HashMap<BiomeGenBase, List<SpawnData>> hashmap, String name, int rarity, ClassType type, BiomeGenBase b, SpawnLocation s) {
		List<SpawnData> spawnList;
		if (hashmap.containsKey(b))
			spawnList = hashmap.get(b);
		else
			spawnList = new ArrayList<SpawnData>();

		spawnList.add(new SpawnData(name, rarity, type, s));
		hashmap.put(b, spawnList);
	}

	public static void getGenerationInfo(HashMap<String, String> hashmap, String name) {
		List<SpawnData> spawnList = new ArrayList<SpawnData>();
		int ID = Entity3HasStats.getBaseStats(name).nationalPokedexNumber;
		ClassType type = ClassType.Pixelmon;
		int rare = Entity3HasStats.getBaseStats(name).rarity;
		if (!hashmap.containsKey(name)) {
			if (rare > 0)
				if (ID <= 151) {
					if (PixelmonConfig.Gen1 == true) {
						//System.out.println("Generation 1: " + name);
						SpawnRegistry.addSpawn(name, rare, type);
						hashmap.put(name, "Gen1");
					}
				} else if (ID > 151 && ID <= 251) {
					if (PixelmonConfig.Gen2 == true) {
						//System.out.println("Generation 2: " + name);
						SpawnRegistry.addSpawn(name, rare, type);
						hashmap.put(name, "Gen2");
					}
				} else if (ID > 251 && ID <= 386) {
					if (PixelmonConfig.Gen3 == true) {
						//System.out.println("Generation 3: " + name);
						SpawnRegistry.addSpawn(name, rare, type);
						hashmap.put(name, "Gen3");
					}
				} else if (ID > 386 && ID <= 493) {
					if (PixelmonConfig.Gen4 == true) {
						//System.out.println("Generation 4: " + name);
						SpawnRegistry.addSpawn(name, rare, type);
						hashmap.put(name, "Gen4");
					}
				} else if (ID > 493 && ID <= 649) {
					if (PixelmonConfig.Gen5 == true) {
						//System.out.println("Generation 5: " + name);
						SpawnRegistry.addSpawn(name, rare, type);
						hashmap.put(name, "Gen5");
					}
				} else {
					System.out.println("[Pixelmon]" + name + " does not have a valid id number");
				}
		}

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

	public static List<SpawnData> getAirSpawnsForBiome(BiomeGenBase b) {
		return airSpawns.get(b);
	}
}
