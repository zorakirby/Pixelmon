package pixelmon.config;

import java.io.File;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEggInfo;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.Pixelmon;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.pokemon.*;
import pixelmon.entities.trainers.EntityTrainerBugCatcher;
import pixelmon.entities.trainers.EntityTrainerYoungster;
import pixelmon.entities.trainers.EntityTrainerYoungster02;
import pixelmon.enums.EnumPokemon;
import pixelmon.spawning.SpawnRegistry;

public class PixelmonEntityList {
	private static Map<String, ClassType> stringToTypeMapping = new HashMap<String, ClassType>();
	private static Map<Integer, ClassType> IDtoTypeMapping = new HashMap<Integer, ClassType>();

	/** Maps entity names to their numeric identifiers */
	public static Map<Integer, String> idToStringMapping = new HashMap<Integer, String>();

	/** This is a HashMap of the Creative Entity Eggs/Spawners. */
	public static HashMap<String, Integer[]> entityEggs = new HashMap<String, Integer[]>();

	/**
	 * adds a mapping between Entity classes and both a string representation
	 * and an ID
	 */
	private static void addMapping(String par1Str, int par2, ClassType type) {
		IDtoTypeMapping.put(Integer.valueOf(par2), type);
		idToStringMapping.put(Integer.valueOf(par2), par1Str);
		stringToTypeMapping.put(par1Str, type);
	}

	private static void addMapping(String par1Str, int par2, ClassType type, int par4, int par5) {
		IDtoTypeMapping.put(Integer.valueOf(par2), type);
		idToStringMapping.put(Integer.valueOf(par2), par1Str);
		stringToTypeMapping.put(par1Str, type);
		entityEggs.put(par1Str, new Integer[] { par4, par5 });
	}
	/**
	 * Create a new instance of an entity in the world by using the entity name.
	 */
	public static EntityLiving createEntityByName(String par0Str, World par1World) {
		EntityLiving var2 = null;

		try {
			ClassType type = null;
			for (EnumPokemon pokemon: EnumPokemon.values())
				if (pokemon.name== par0Str)
					type = pokemon.type;
			Class<?> var3 = null;
			if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon)
				var3 = EntityPixelmon.class;
			else if (type == null)
				var3 = (Class<?>) Class.forName("pixelmon.entities.trainers.EntityTrainer" + par0Str);
			if (var3 != null) {
				if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon) {
					var2 = new EntityPixelmon(par1World);
					((EntityPixelmon) var2).init(par0Str);
				} else {
					var2 = (EntityLiving) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
				}
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	/**
	 * Finds the class using IDtoClassMapping and classToStringMapping
	 */
	public static String getStringFromID(int par0) {
		return idToStringMapping.get(Integer.valueOf(par0));
	}

	public static ClassType getClassTypeFromID(int par0) {
		return (ClassType) IDtoTypeMapping.get(Integer.valueOf(par0));
	}

	private static ClassType getClassTypeFromString(String par0Str) {
		return (ClassType) stringToTypeMapping.get(par0Str);
	}
	
	/**
	 * create a new instance of an entity from NBT store
	 */
	public static Entity createEntityFromNBT(NBTTagCompound par0NBTTagCompound, World par1World) {
		EntityLiving var2 = null;

		try {
			Class<?> var3 = EntityPixelmon.class;

			if (var3 != null) {
				var2 = (EntityLiving) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
				((EntityPixelmon) var2).init(par0NBTTagCompound.getString("Name"));
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if (var2 != null) {
			var2.readFromNBT(par0NBTTagCompound);
		} else {
			System.out.println("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
		}

		return var2;
	}

	static {
		// Trainers
		addMapping("Youngster", IDListTrainer.trainerYoungsterId, ClassType.Trainer);
		addMapping("Youngster02", IDListTrainer.trainerYoungster2Id, ClassType.Trainer);
		addMapping("BugCatcher", IDListTrainer.trainerBugCatcherId, ClassType.Trainer);
	}

	public static void registerEntities() {
		Iterator it = idToStringMapping.keySet().iterator();
		while (it.hasNext()) {
			Integer i = (Integer) it.next();
			String name = idToStringMapping.get(i);
			ClassType type = getClassTypeFromID(i);
			// System.out.println(name + "[" + i.intValue() + ", " + type.name()
			// + "]");
			LanguageRegistry.instance().addStringLocalization("entity." + name + ".name", "en_US", name);
			Integer[] eggInfo = entityEggs.get(name);
			try {
				if (type == ClassType.Trainer)
					if (eggInfo != null)
						EntityRegistry.registerGlobalEntityID((Class<? extends EntityLiving>) Class.forName("pixelmon.entities.trainers.EntityTrainer" + name), name,
								EntityRegistry.findGlobalUniqueEntityId(), eggInfo[0], eggInfo[1]);
					else
						EntityRegistry.registerGlobalEntityID((Class<? extends EntityLiving>) Class.forName("pixelmon.entities.trainers.EntityTrainer" + name), name,
								EntityRegistry.findGlobalUniqueEntityId());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		//EntityRegistry.registerGlobalEntityID(EntityPixelmon.class, "Pixelmon", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityPixelmon.class, "Pixelmon", PixelmonConfig.idPixelmon , Pixelmon.instance, 80, 1, true);
	}

	public static void addSpawns() {
		System.out.println("[PIXELMON] Registering entity spawns");
		
		for (EnumPokemon pokemon: EnumPokemon.values()){
			String name = pokemon.name;
			ClassType type = pokemon.type;
			int rarity = DatabaseStats.GetRarity(name);
			if (rarity>0)
				SpawnRegistry.addSpawn(name, rarity, type);
		}
		
		Iterator i = idToStringMapping.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			String name = (String) entry.getValue();
			ClassType type = getClassTypeFromID((Integer) entry.getKey());
			int rarity = DatabaseStats.GetRarity(name);
			if (type == ClassType.Trainer)
				rarity = 20;
			if (rarity > 0)
				SpawnRegistry.addSpawn(name, rarity, type);
			// if (new File("resources/newsound/pixelmon/" +
			// name.toLowerCase() + ".ogg").exists())
			//
			// Minecraft.getMinecraft().installResource("newsound/pixelmon/"
			// + name.toLowerCase() + ".ogg", new
			// File("resources/newsound/pixelmon/" + name.toLowerCase() +
			// ".ogg"));
		}
	}

	public enum ClassType {
		Trainer, Pixelmon, WaterPixelmon
	}
}
