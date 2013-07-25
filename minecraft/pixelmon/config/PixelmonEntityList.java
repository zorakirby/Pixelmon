package pixelmon.config;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;
import pixelmon.entities.npcs.EntityDoctor;
import pixelmon.entities.npcs.EntityNPC;
import pixelmon.entities.npcs.EntityTrainer;
import pixelmon.entities.npcs.NPCType;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.spawning.SpawnRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class PixelmonEntityList {
	/**
	 * Create a new instance of an entity in the world by using the entity name.
	 */
	public static EntityLiving createEntityByName(String par0Str, World par1World) {
		EntityLiving var2 = null;

		try {
			ClassType type = null;
			for (EnumPokemon pokemon : EnumPokemon.values())
				if (pokemon.name.equalsIgnoreCase(par0Str))
					type = ClassType.Pixelmon;

			if (type == ClassType.Pixelmon) {
				var2 = new EntityPixelmon(par1World);
				((EntityPixelmon) var2).init(par0Str);
			} else if (EnumTrainers.has(par0Str)) {
				var2 = new EntityTrainer(par1World);
				((EntityTrainer) var2).init(par0Str);
			}else {
				NPCType npcType = NPCType.get(par0Str);
				if (npcType == NPCType.Doctor)
					var2 = new EntityDoctor(par1World);
				((EntityNPC)var2).init(par0Str);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
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
			if (PixelmonConfig.printErrors)
				System.out.println("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
		}

		return var2;
	}

	public static void registerEntities() {
		EntityRegistry.registerModEntity(EntityTrainer.class, "Trainer", PixelmonConfig.idTrainers, Pixelmon.instance, 100, 1, true);
		EntityRegistry.registerModEntity(EntityPixelmon.class, "Pixelmon", PixelmonConfig.idPixelmon, Pixelmon.instance, 100, 1, true);
	}

	public static void addSpawns() {
		System.out.println("[PIXELMON] Registering entity spawns");

		for (EnumPokemon pokemon : EnumPokemon.values()) {
			String name = pokemon.name;
			int rarity = DatabaseStats.GetRarity(name);
			if (rarity > 0)
				SpawnRegistry.addSpawn(name, rarity, ClassType.Pixelmon);
		}

		for (EnumTrainers trainer : EnumTrainers.values()) {
			String name = trainer.toString();
			int rarity = DatabaseTrainers.getRarity(name);
			double rardbl = (double) rarity;
			rardbl *= ((double) PixelmonConfig.trainerRarityModifier) / 100.0;
			rarity = (int) rardbl;
			if (rarity > 0)
				SpawnRegistry.addSpawn(name, rarity, ClassType.Trainer);
		}
	}

	public enum ClassType {
		Trainer, Pixelmon
	}
}
