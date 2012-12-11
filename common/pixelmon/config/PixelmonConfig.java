package pixelmon.config;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;

import cpw.mods.fml.common.registry.EntityRegistry;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.trainers.EntityTrainer;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumCreatureType;
import net.minecraftforge.common.Configuration;

public class PixelmonConfig {

	public static boolean isInMetric = true;

	public static Configuration config;

	public static boolean allowNonPixelmonMobs;

	public static int maxPokemonPerChunk = 3;

	public static int chanceOfNoPokemon;

	public static boolean scaleModelsUp;

	public static int idTrainers = 199;
	public static int idPixelmon = 200;
	public static int idPokeball = 201;
	public static int idCamera = 202;

	public static void loadConfig(Configuration configuration) {
		config = configuration;
		config.load();
		PixelmonBlocks.load(config);
		PixelmonItems.load(config);
		allowNonPixelmonMobs = config.get("general", "Allow vanilla mobs", false).getBoolean(false);
		maxPokemonPerChunk = config.get("general", "Max number of pokemon per chunk", 4).getInt(4);
		chanceOfNoPokemon = config.get("general", "Percent chance of no pokemon in chunk", 30).getInt(20);
		scaleModelsUp = config.get("general", "Scale Models Up", true).getBoolean(true);
		idTrainers = config.get("IDs", "Trainer ID", 199).getInt(199);
		idPixelmon = config.get("IDs", "Pixelmon ID", 200).getInt(200);
		idPokeball = config.get("IDs", "Pokeball ID", 201).getInt(201);
		idCamera = config.get("IDs", "Camera ID", 202).getInt(202);
		config.save();

		PixelmonItems.addNames();
		PixelmonBlocks.addNames();
		PixelmonBlocks.registerBlocks();
		PixelmonEntityList.registerEntities();
		PixelmonEntityList.addSpawns();
	}

	public static void removeSpawns() {
		if (!allowNonPixelmonMobs) {
			ArrayList list = new ArrayList();
			Iterator it = EntityList.IDtoClassMapping.keySet().iterator();
			while (it.hasNext()) {
				Integer i = (Integer) it.next();
				Class c1 = (Class) EntityList.IDtoClassMapping.get(i);
				if (EntityPixelmon.class.isAssignableFrom(c1) || EntityTrainer.class.isAssignableFrom(c1)) {
					continue;
				}
				if (EntityLiving.class.isAssignableFrom(c1) && !Modifier.isAbstract(c1.getModifiers())) {
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
