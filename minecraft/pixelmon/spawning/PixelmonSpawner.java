package pixelmon.spawning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumBossMode;
import pixelmon.spawning.spawners.*;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PixelmonSpawner implements ITickHandler {
	private enum spawnType {
		Land, Underground, Water
	}

	/** The 17x17 area around the player where mobs can spawn */
	private static HashMap eligibleChunksForSpawning = new HashMap();

	private static Random rand = new Random();

	private static ArrayList<SpawnerBase> spawners = new ArrayList<SpawnerBase>();
	static {
		spawners.add(new SpawnerLand());
		spawners.add(new SpawnerUnderground());
		spawners.add(new SpawnerUnderWater());
	}

	/**
	 * Given a chunk, find a random position in it.
	 */
	protected static ChunkPosition getRandomSpawningPointInChunk(World world, int x, int z) {
		Chunk chunk = world.getChunkFromChunkCoords(x, z);
		int xCoord = x * 16 + world.rand.nextInt(16);
		int zCoord = z * 16 + world.rand.nextInt(16);
		int yCoord = world.rand.nextInt(chunk == null ? world.getActualHeight() : chunk.getTopFilledSegment() + 16 - 1);
		return new ChunkPosition(xCoord, yCoord, zCoord);
	}

	/**
	 * adds all chunks within the spawn radius of the players to
	 * eligibleChunksForSpawning. pars: the world, hostileCreatures,
	 * passiveCreatures. returns number of eligible chunks.
	 */
	public static final void findChunksForSpawning(WorldServer world) {
		eligibleChunksForSpawning.clear();
		for (int i = 0; i < world.playerEntities.size(); ++i) {
			EntityPlayer player = (EntityPlayer) world.playerEntities.get(i);
			int x = MathHelper.floor_double(player.posX / 16.0D);
			int z = MathHelper.floor_double(player.posZ / 16.0D);
			byte var7 = 8;

			for (int j = -var7; j <= var7; ++j) {
				for (int k = -var7; k <= var7; ++k) {
					boolean var10 = j == -var7 || j == var7 || k == -var7 || k == var7;
					ChunkCoordIntPair var11 = new ChunkCoordIntPair(j + x, k + z);

					if (!var10) {
						eligibleChunksForSpawning.put(var11, Boolean.valueOf(false));
					} else if (!eligibleChunksForSpawning.containsKey(var11)) {
						eligibleChunksForSpawning.put(var11, Boolean.valueOf(true));
					}
				}
			}
		}
	}

	public static void doSpawning(WorldServer world) {
		if (countEntities(world))
			return;

		ChunkCoordinates chunkCoords = world.getSpawnPoint();
		Iterator chunkIterator = eligibleChunksForSpawning.keySet().iterator();
		ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
		Collections.shuffle(tmp);
		chunkIterator = tmp.iterator();

		while (chunkIterator.hasNext()) {
			ChunkCoordIntPair ccIntPair = (ChunkCoordIntPair) chunkIterator.next();

			if (!((Boolean) eligibleChunksForSpawning.get(ccIntPair)).booleanValue()) {
				ChunkPosition chunkPos = getRandomSpawningPointInChunk(world, ccIntPair.chunkXPos, ccIntPair.chunkZPos);
				int cpX = chunkPos.x;
				int cpY = chunkPos.y;
				int cpZ = chunkPos.z;
				for (SpawnerBase s : spawners) {
					if (s.count <= s.maxNum * eligibleChunksForSpawning.size() / 256) {
						if (s.getSpawnCondition(world, cpX, cpY, cpZ)) {
							s.count += doSpawn(s, s.spawnLocation, world, cpX, cpY, cpZ, chunkCoords);
						}
					}
				}
			}
		}

	}

	private static boolean countEntities(WorldServer world) {
		for (SpawnerBase s : spawners)
			s.count = 0;
		for (int var3 = 0; var3 < world.loadedEntityList.size(); ++var3) {
			Entity var4 = (Entity) world.loadedEntityList.get(var3);
			for (SpawnerBase b : spawners) {
				if (var4 instanceof EntityPixelmon) {
					if (((EntityPixelmon) var4).pokemonLocation == b.spawnLocation)
						b.count++;
				} else if (var4 instanceof EntityTrainer) {
					if (((EntityTrainer) var4).trainerLocation == b.spawnLocation)
						b.count++;
				}
			}
		}
		boolean isFull = true;
		for (SpawnerBase s : spawners) {
			if (isFull && s.count <= s.maxNum * eligibleChunksForSpawning.size() / 256)
				isFull = false;
		}
		return isFull;
	}

	private static int doSpawn(SpawnerBase s, SpawnLocation spawnLocation, World world, int cpX, int cpY, int cpZ, ChunkCoordinates chunkCoords) {

		int numInChunk = 0;
		int count = 0;
		boolean isBoss = false;
		ArrayList<String> preEvolutions = null;

		while (count < 3) {
			int cpXtmp = cpX;
			int cpYtmp = cpY;
			int cpZtmp = cpZ;
			byte rndmMax = 6;
			String pokemonName = null;
			int count2 = 0;
			int maxInChunk = -1;

			while (true) {
				if (count2 < 4) {
					outerWhile: {
						cpXtmp += world.rand.nextInt(rndmMax) - world.rand.nextInt(rndmMax);
						cpYtmp += world.rand.nextInt(1) - world.rand.nextInt(1);
						cpZtmp += world.rand.nextInt(rndmMax) - world.rand.nextInt(rndmMax);

						if (s.canPokemonSpawnHere(world, cpXtmp, cpYtmp, cpZtmp)) {
							float x = (float) cpXtmp + 0.5F;
							float y = (float) cpYtmp;
							float z = (float) cpZtmp + 0.5F;

							if (world.getClosestPlayer((double) x, (double) y, (double) z, 24.0D) == null) {
								float xd = x - (float) chunkCoords.posX;
								float yd = y - (float) chunkCoords.posY;
								float zd = z - (float) chunkCoords.posZ;
								float d = xd * xd + yd * yd + zd * zd;

								if (d >= 576.0F) {
									if (pokemonName == null) {
										pokemonName = s.getRandomPokemon(world.provider, rand, cpXtmp, cpYtmp, cpZtmp);

										if (pokemonName == null) {
											break outerWhile;
										}
									}

									if (isBoss)
										pokemonName = preEvolutions.get(rand.nextInt(preEvolutions.size()));
									EntityLiving pokemon;

									try {
										pokemon = PixelmonEntityList.createEntityByName(pokemonName, world);
									} catch (Exception e) {
										e.printStackTrace();
										return 0;
									}

									pokemon.setLocationAndAngles((double) x, (double) y, (double) z, world.rand.nextFloat() * 360.0F, 0.0F);
									if (pokemon instanceof EntityPixelmon)
										((EntityPixelmon) pokemon).pokemonLocation = spawnLocation;
									if (pokemon instanceof EntityTrainer)
										((EntityTrainer) pokemon).trainerLocation = spawnLocation;
									if (pokemon.getCanSpawnHere()) {
										++numInChunk;
										world.spawnEntityInWorld(pokemon);

										if (maxInChunk == -1) {
											if (world.rand.nextInt(300) == 0 && pokemon instanceof EntityPixelmon) {
												EntityPixelmon pixelmon = (EntityPixelmon) pokemon;
												preEvolutions = pixelmon.getPreEvolutions();
												preEvolutions.add(pokemonName);
												pixelmon.setBoss(EnumBossMode.getRandomMode());
												isBoss = true;
											}
											maxInChunk = pokemon.getMaxSpawnedInChunk();
											if (isBoss)
												maxInChunk *= 2;
										}
										if (numInChunk >= maxInChunk) {
											return numInChunk;
										}
									}
								}
							}
						}

						++count2;
						continue;
					}
				}

				++count;
				break;
			}
		}
		return 0;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		findChunksForSpawning(MinecraftServer.getServer().worldServerForDimension(0));
		doSpawning(MinecraftServer.getServer().worldServerForDimension(0));
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return null;
	}

}
