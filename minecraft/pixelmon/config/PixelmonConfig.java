package pixelmon.config;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import pixelmon.entities.npcs.EntityTrainer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import cpw.mods.fml.common.registry.EntityRegistry;

public class PixelmonConfig {

	public static boolean isInMetric = true;

	public static Configuration config;

	public static boolean allowNonPixelmonMobs;
	public static boolean allowCapturingOutsideBattle = true;
	public static boolean allowNicknames = true;
	public static boolean spawnStructures = true;

	public static int maxNumLandPokemon;
	public static int maxNumWaterPokemon;
	public static int maxNumUndergroundPokemon;
	public static int maxNumAirPokemon;

	public static int trainerRarityModifier;

	public static int nameplateRangeModifier;

	public static boolean scaleModelsUp;
	public static boolean pokemonDropsEnabled = true;
	public static boolean allowRiding = true;
	public static boolean allowPlanting = true;

	public static boolean removeVanillaMusic = true;

	public static int idTrainers = 199;
	public static int idPixelmon = 200;
	public static int idPokeball = 201;
	public static int idCamera = 202;

	public static boolean allowPVPExperience = true;
	public static boolean printErrors = false;

	public static boolean allowRareCandyCrafting = false;
	
	public static boolean Gen1 = true;
	public static boolean Gen2 = true;
	public static boolean Gen3 = true;
	public static boolean Gen4 = true;
	public static boolean Gen5 = true;

	public static void loadConfig(Configuration configuration) {
		config = configuration;
		PixelmonBlocks.load(config);
		PixelmonItems.load(config);
		spawnStructures = config.get("general", "Allow Structures to Spawn", true).getBoolean(true);
		
		allowNicknames = config.get("general", "Allow pokemon nicknames", true).getBoolean(true);
		allowNonPixelmonMobs = config.get("general", "Allow vanilla mobs",
				false).getBoolean(false);
		allowCapturingOutsideBattle = config.get("general",
				"Allow Capturing Outside of Battle", true).getBoolean(true);
		maxNumLandPokemon = config.get("general",
				"Max number of Land Pokemon (at one time)", 40).getInt(40);
		maxNumUndergroundPokemon = config.get("general",
				"Max number of Underground Pokemon (at one time)", 60).getInt(
				60);
		maxNumWaterPokemon = config.get("general",
				"Max number of Water Pokemon (at one time)", 20).getInt(20);
		maxNumAirPokemon = config.get("general",
				"Max number of Air Pokemon (at one time)", 20).getInt(5);
		trainerRarityModifier = config.get("general",
				"Trainer Rarity (percentage 0-200)", 100).getInt(100);
		nameplateRangeModifier = config
				.get("general",
						"Nameplate Visible Range Modifier (1=default, 2=farther, 3=far)",
						1).getInt(1);
		scaleModelsUp = config.get("general", "Scale Models Up", true)
				.getBoolean(true);
		pokemonDropsEnabled = config.get("general", "Pokemon Drops Enabled",
				true).getBoolean(true);
		removeVanillaMusic = config.get("general", "Remove Minecraft Music",
				true).getBoolean(true);
		printErrors = config.get("general", "Print Errors", false).getBoolean(
				false);
		allowRiding = config.get("general", "Allow Riding", true).getBoolean(
				true);
		allowPlanting = config.get("general", "Allow Planting", true)
				.getBoolean(true);
		allowPVPExperience = config
				.get("general", "Allow PVP Experience", true).getBoolean(true);
		allowRareCandyCrafting = config.get("general",
				"Allow Crafting of Rare Candy", true).getBoolean(true);
		idTrainers = config.get("IDs", "Trainer ID", 199).getInt(199);
		idPixelmon = config.get("IDs", "Pixelmon ID", 200).getInt(200);
		idPokeball = config.get("IDs", "Pokeball ID", 201).getInt(201);
		idCamera = config.get("IDs", "Camera ID", 202).getInt(202);
		Gen1 = config.get("Spawning", "Gen1", true).getBoolean(true);
		Gen2 = config.get("Spawning", "Gen2", true).getBoolean(true);
		Gen3 = config.get("Spawning", "Gen3", true).getBoolean(true);
		Gen4 = config.get("Spawning", "Gen4", true).getBoolean(true);
		Gen5 = config.get("Spawning", "Gen5", true).getBoolean(true);
		
		config.save();

		PixelmonItems.addNames();
		PixelmonBlocks.addNames();
		PixelmonBlocks.registerBlocks();
		PixelmonEntityList.registerEntities();
		PixelmonEntityList.addSpawns();
	}

	public static void removeSpawns() {
		if (!allowNonPixelmonMobs) {
			Iterator it = EntityList.IDtoClassMapping.keySet().iterator();
			while (it.hasNext()) {
				Integer i = (Integer) it.next();
				Class c1 = (Class) EntityList.IDtoClassMapping.get(i);
				if (EntityPixelmon.class.isAssignableFrom(c1)
						|| EntityTrainer.class.isAssignableFrom(c1)) {
					continue;
				}
				if (EntityLiving.class.isAssignableFrom(c1)
						&& !Modifier.isAbstract(c1.getModifiers())) {
					Class<? extends EntityLiving> c3 = c1;
					int maxNonNullBiomes = 0;
					for (BiomeGenBase biome : BiomeGenBase.biomeList) {
						if (biome != null) {
							maxNonNullBiomes++;
						}
					}
					int nextBiome = 0;
					BiomeGenBase[] biomes = new BiomeGenBase[maxNonNullBiomes];
					for (BiomeGenBase biome : BiomeGenBase.biomeList) {
						if (biome != null) {
							biomes[nextBiome++] = biome;
						}
					}
					for (EnumCreatureType type : EnumCreatureType.values()) {
						EntityRegistry.removeSpawn(c3, type, biomes);
					}
				}
			}
		}
	}
}
