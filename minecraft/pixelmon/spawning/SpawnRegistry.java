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
import pixelmon.entities.pixelmon.stats.Rarity;

public class SpawnRegistry {

	private static HashMap<BiomeGenBase, List<SpawnData>> biomeSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> undergroundSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> biomeWaterSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static HashMap<BiomeGenBase, List<SpawnData>> airSpawns = new HashMap<BiomeGenBase, List<SpawnData>>();
	private static DatabaseStats db;

	public static void addPixelmonSpawn(BaseStats stats) {
		BiomeGenBase[] biomes = stats.biomes;
		if (biomes == null)
			return;
		if (stats.spawnLocations == null) {
			for (BiomeGenBase b : biomes) {
				storeSpawnInfo(biomeSpawns, stats.pixelmonName, stats.rarity, ClassType.Pixelmon, b, null);
			}
		} else {
			for (SpawnLocation s : stats.spawnLocations) {
				for (BiomeGenBase b : biomes) {
					if (s == SpawnLocation.Land || s == SpawnLocation.Air) {
						storeSpawnInfo(biomeSpawns, stats.pixelmonName, stats.rarity, ClassType.Pixelmon, b, s);
					} else if (s == SpawnLocation.AirPersistent) {
						storeSpawnInfo(airSpawns, stats.pixelmonName, stats.rarity, ClassType.Pixelmon, b, s);
					} else if (s == SpawnLocation.UnderGround) {
						storeSpawnInfo(undergroundSpawns, stats.pixelmonName, stats.rarity, ClassType.Pixelmon, b, s);
					} else if (s == SpawnLocation.Water || s == SpawnLocation.OnWater) {
						storeSpawnInfo(biomeWaterSpawns, stats.pixelmonName, stats.rarity, ClassType.Pixelmon, b, s);
					}
				}
			}
		}

	}

	public static void addNPCSpawn(String name, int rarity, ClassType type) {
		BiomeGenBase[] biomes = DatabaseTrainers.GetSpawnBiomes(name);

		if (biomes == null)
			return;
		for (BiomeGenBase b : biomes) {
			storeSpawnInfo(biomeSpawns, name, new Rarity(rarity, rarity, rarity), type, b, null);
		}

	}

	private static void storeSpawnInfo(HashMap<BiomeGenBase, List<SpawnData>> hashmap, String name, Rarity rarity, ClassType type, BiomeGenBase b,
			SpawnLocation s) {
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
		BaseStats stats = Entity3HasStats.getBaseStats(name);
		int ID = stats.nationalPokedexNumber;
		ClassType type = ClassType.Pixelmon;
		if (!hashmap.containsKey(name)) {
			if (stats.rarity != null && (stats.rarity.day > 0 || stats.rarity.night > 0 || stats.rarity.dawndusk > 0))
				if (ID <= 151) {
					if (PixelmonConfig.Gen1 == true) {
						// System.out.println("Generation 1: " + name);
						SpawnRegistry.addPixelmonSpawn(stats);
						hashmap.put(name, "Gen1");
					}
				} else if (ID > 151 && ID <= 251) {
					if (PixelmonConfig.Gen2 == true) {
						// System.out.println("Generation 2: " + name);
						SpawnRegistry.addPixelmonSpawn(stats);
						hashmap.put(name, "Gen2");
					}
				} else if (ID > 251 && ID <= 386) {
					if (PixelmonConfig.Gen3 == true) {
						// System.out.println("Generation 3: " + name);
						SpawnRegistry.addPixelmonSpawn(stats);
						hashmap.put(name, "Gen3");
					}
				} else if (ID > 386 && ID <= 493) {
					if (PixelmonConfig.Gen4 == true) {
						// System.out.println("Generation 4: " + name);
						SpawnRegistry.addPixelmonSpawn(stats);
						hashmap.put(name, "Gen4");
					}
				} else if (ID > 493 && ID <= 649) {
					if (PixelmonConfig.Gen5 == true) {
						// System.out.println("Generation 5: " + name);
						SpawnRegistry.addPixelmonSpawn(stats);
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
