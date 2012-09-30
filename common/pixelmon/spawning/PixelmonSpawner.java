package pixelmon.spawning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumTrainers;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Chunk;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Material;
import net.minecraft.src.SpawnerAnimals;
import net.minecraft.src.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkEvent;

public class PixelmonSpawner {
	private static Random random = new Random();

	private class SpawnInfo {
		public String name;
		public int x;
		public int y;
		public int z;

		public SpawnInfo(String name, int x, int y, int z) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	private static ArrayList<SpawnInfo> spawnList = new ArrayList<SpawnInfo>();

	@ForgeSubscribe
	public void spawnOnChunkLoad(ChunkEvent.Load event) {
		List[] entityLists = event.getChunk().entityLists;
		int currentTotalNum = 0;
		for (List l : entityLists) {
			currentTotalNum += l.size();
		}
		int calculatedNum = ChunkDataEvents.getNumFromPos(event.getChunk().xPosition, event.getChunk().zPosition);
		if (calculatedNum < currentTotalNum) {
			ArrayList<List> filledLists = new ArrayList<List>();
			for (List l : entityLists)
				if (l.size() != 0)
					filledLists.add(l);
			while (calculatedNum < currentTotalNum) {
				int listIndex = random.nextInt(filledLists.size());
				int entityIndex = random.nextInt(filledLists.get(listIndex).size());
				if (!(((Entity) filledLists.get(listIndex).get(entityIndex)) instanceof EntityVillager))
					((Entity) filledLists.get(listIndex).get(entityIndex)).setDead();
				currentTotalNum--;
			}
		} else if (calculatedNum > currentTotalNum)
			performSpawningInChunk(event.getChunk(), event.getChunk().xPosition, event.getChunk().zPosition, calculatedNum - currentTotalNum, event.world);
	}

	private void performSpawningInChunk(Chunk chunk, int xPosition, int zPosition, int num, World world) {
		if (num <= 0)
			return;
		int x = xPosition * 16;
		int z = zPosition * 16;
		BiomeGenBase biome = world.getBiomeGenForCoords(x + 8, z + 8);
		List<SpawnData> creatureList = SpawnRegistry.getSpawnsForBiome(biome);

		int totRarityCount = 0;
		if (creatureList == null)
			return;
		for (SpawnData s : creatureList)
			totRarityCount += s.rarity;

		for (int i = 0; i < num; i++) {
			int xRand = x + random.nextInt(16);
			int zRand = z + random.nextInt(16);
			int y = getTopSolidOrLiquidBlock(chunk, xRand, zRand);
			int index = random.nextInt(totRarityCount);
			String creatureName = null;
			int tot = 0;
			for (SpawnData s : creatureList) {
				tot += s.rarity;
				if (index <= tot) {
					creatureName = s.name;
					break;
				}
			}
			spawnList.add(new SpawnInfo(creatureName, xRand, y, zRand));
		}
	}

	public static void spawnTick(World world) {
		if (spawnList.size() > 0) {
			if (EnumTrainers.has(spawnList.get(0).name)) {
				Entity trainer = PixelmonEntityList.createEntityByName(spawnList.get(0).name, world);
				trainer.setLocationAndAngles(spawnList.get(0).x, spawnList.get(0).y, spawnList.get(0).z, random.nextFloat() * 360.0F, 0.0F);
				if (((EntityLiving) trainer).getCanSpawnHere())
					world.spawnEntityInWorld(trainer);
			} else if (SpawnerAnimals.canCreatureTypeSpawnAtLocation(DatabaseStats.GetCreatureType(spawnList.get(0).name), world, spawnList.get(0).x, spawnList.get(0).y, spawnList.get(0).z)) {
				Entity pixelmon = PixelmonEntityList.createEntityByName(spawnList.get(0).name, world);
				pixelmon.setLocationAndAngles(spawnList.get(0).x, spawnList.get(0).y, spawnList.get(0).z, random.nextFloat() * 360.0F, 0.0F);
				if (((EntityLiving) pixelmon).getCanSpawnHere())
					world.spawnEntityInWorld(pixelmon);
			}
			spawnList.remove(0);
		}
	}

	private int getTopSolidOrLiquidBlock(Chunk chunk, int x, int z) {
		int var4 = chunk.getTopFilledSegment() + 15;
		x &= 15;

		for (z &= 15; var4 > 0; --var4) {
			int var5 = chunk.getBlockID(x, var4, z);

			if (var5 != 0 && Block.blocksList[var5].blockMaterial.blocksMovement() && Block.blocksList[var5].blockMaterial != Material.leaves) {
				return var4 + 1;
			}
		}

		return -1;
	}

	private static int getBlockId(int x, int y, int z, Chunk chunk) {
		return chunk.getBlockID(x & 15, y, z & 15);
	}

}
