package pixelmon.spawning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpecialSpawnEvent;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PixelmonSpawner implements ITickHandler {
	/** The 17x17 area around the player where mobs can spawn */
	private static HashMap eligibleChunksForSpawning = new HashMap();

	private static Random rand = new Random();

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

	public static void doLandSpawning(WorldServer world) {
		if (countLandPokemonEntities(world) <= PixelmonConfig.maxNumLandPokemon * eligibleChunksForSpawning.size() / 256) {
			ChunkCoordinates chunkCoords = world.getSpawnPoint();
			Iterator chunkIterator = eligibleChunksForSpawning.keySet().iterator();
			ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
			Collections.shuffle(tmp);
			chunkIterator = tmp.iterator();
			label110:

			while (chunkIterator.hasNext()) {
				ChunkCoordIntPair ccIntPair = (ChunkCoordIntPair) chunkIterator.next();

				if (!((Boolean) eligibleChunksForSpawning.get(ccIntPair)).booleanValue()) {
					ChunkPosition chunkPos = getRandomSpawningPointInChunk(world, ccIntPair.chunkXPos, ccIntPair.chunkZPos);
					int cpX = chunkPos.x;
					int cpY = chunkPos.y;
					int cpZ = chunkPos.z;

					if (!world.isBlockNormalCube(cpX, cpY, cpZ) && world.getBlockMaterial(cpX, cpY, cpZ) == Material.air) {
						int numInChunk = 0;
						int count = 0;

						while (count < 3) {
							int cpXtmp = cpX;
							int cpYtmp = cpY;
							int cpZtmp = cpZ;
							byte rndmMax = 6;
							String pokemonName = null;
							int count2 = 0;

							while (true) {
								if (count2 < 4) {
									label102: {
										cpXtmp += world.rand.nextInt(rndmMax) - world.rand.nextInt(rndmMax);
										cpYtmp += world.rand.nextInt(1) - world.rand.nextInt(1);
										cpZtmp += world.rand.nextInt(rndmMax) - world.rand.nextInt(rndmMax);

										if (canLandPokemonSpawnHere(world, cpXtmp, cpYtmp, cpZtmp)) {
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
														pokemonName = getRandomLandPokemonName(world.provider, cpXtmp, cpYtmp, cpZtmp);

														if (pokemonName == null) {
															break label102;
														}
													}

													EntityLiving pokemon;

													try {
														pokemon = PixelmonEntityList.createEntityByName(pokemonName, world);
													} catch (Exception e) {
														e.printStackTrace();
														return;
													}

													pokemon.setLocationAndAngles((double) x, (double) y, (double) z, world.rand.nextFloat() * 360.0F, 0.0F);

													if (pokemon.getCanSpawnHere()) {
														++numInChunk;
														world.spawnEntityInWorld(pokemon);

														if (numInChunk >= pokemon.getMaxSpawnedInChunk()) {
															continue label110;
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
					}
				}
			}
		}

	}

	public static void doWaterSpawning(WorldServer world) {
		ChunkCoordinates chunkCoords = world.getSpawnPoint();

		if (countWaterPokemonEntities(world) <= PixelmonConfig.maxNumWaterPokemon * eligibleChunksForSpawning.size() / 256) {
			Iterator chunkIterator = eligibleChunksForSpawning.keySet().iterator();
			ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
			Collections.shuffle(tmp);
			chunkIterator = tmp.iterator();
			label108:

			while (chunkIterator.hasNext()) {
				ChunkCoordIntPair ccIntPair = (ChunkCoordIntPair) chunkIterator.next();

				if (!((Boolean) eligibleChunksForSpawning.get(ccIntPair)).booleanValue()) {
					ChunkPosition chunkPos = getRandomSpawningPointInChunk(world, ccIntPair.chunkXPos, ccIntPair.chunkZPos);
					int cpX = chunkPos.x;
					int cpY = chunkPos.y;
					int cpZ = chunkPos.z;

					if (!world.isBlockNormalCube(cpX, cpY, cpZ) && world.getBlockMaterial(cpX, cpY, cpZ) == Material.water) {
						int numInChunk = 0;
						int count = 0;

						while (count < 3) {
							int cpXtmp = cpX;
							int cpYtmp = cpY;
							int cpZtmp = cpZ;
							byte rndmMax = 6;
							String pokemonName = null;
							int count2 = 0;

							while (true) {
								if (count2 < 4) {
									label101: {
										cpXtmp += world.rand.nextInt(rndmMax) - world.rand.nextInt(rndmMax);
										cpYtmp += world.rand.nextInt(1) - world.rand.nextInt(1);
										cpZtmp += world.rand.nextInt(rndmMax) - world.rand.nextInt(rndmMax);

										if (canWaterPokemonSpawnHere(world, cpXtmp, cpYtmp, cpZtmp)) {
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
														pokemonName = getRandomWaterPokemonName(world.provider, cpXtmp, cpYtmp, cpZtmp);

														if (pokemonName == null) {
															break label101;
														}
													}

													EntityLiving pokemon;

													try {
														pokemon = PixelmonEntityList.createEntityByName(pokemonName, world);
													} catch (Exception e) {
														e.printStackTrace();
														return;
													}

													pokemon.setLocationAndAngles((double) x, (double) y, (double) z, world.rand.nextFloat() * 360.0F, 0.0F);

													if (pokemon.getCanSpawnHere()) {
														++numInChunk;
														world.spawnEntityInWorld(pokemon);

														if (numInChunk >= pokemon.getMaxSpawnedInChunk()) {
															continue label108;
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
					}
				}
			}
		}

	}

	/**
	 * Counts how many entities of an entity class exist in the world. Args:
	 * entityClass
	 */
	public static int countLandPokemonEntities(World world) {
		int var2 = 0;

		for (int var3 = 0; var3 < world.loadedEntityList.size(); ++var3) {
			Entity var4 = (Entity) world.loadedEntityList.get(var3);

			if (var4 instanceof EntityPixelmon) {
				if (((EntityPixelmon) var4).baseStats.creatureType == EnumCreatureType.creature)
					++var2;
			}
		}

		return var2;
	}

	/**
	 * Counts how many entities of an entity class exist in the world. Args:
	 * entityClass
	 */
	public static int countWaterPokemonEntities(World world) {
		int var2 = 0;

		for (int var3 = 0; var3 < world.loadedEntityList.size(); ++var3) {
			Entity var4 = (Entity) world.loadedEntityList.get(var3);

			if (var4 instanceof EntityPixelmon) {
				if (((EntityPixelmon) var4).baseStats.creatureType == EnumCreatureType.waterCreature)
					++var2;
			}
		}

		return var2;
	}

	/**
	 * only spawns creatures allowed by the chunkProvider
	 */
	public static String getRandomWaterPokemonName(WorldProvider worldProvider, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getWaterSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? ((SpawnData) WeightedRandom.getRandomItem(rand, spawnData)).name : null;
	}

	/**
	 * only spawns creatures allowed by the chunkProvider
	 */
	public static String getRandomLandPokemonName(WorldProvider worldProvider, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? ((SpawnData) WeightedRandom.getRandomItem(rand, spawnData)).name : null;
	}

	/**
	 * Returns whether or not the specified creature type can spawn at the
	 * specified location.
	 */
	public static boolean canWaterPokemonSpawnHere(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMaterial(par2, par3, par4).isLiquid() && !par1World.isBlockNormalCube(par2, par3 + 1, par4);
	}

	public static boolean canLandPokemonSpawnHere(World par1World, int par2, int par3, int par4) {
		if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)) {
			return false;
		} else {
			int var5 = par1World.getBlockId(par2, par3 - 1, par4);
			boolean spawnBlock = (Block.blocksList[var5] != null && Block.blocksList[var5].canCreatureSpawn(EnumCreatureType.creature, par1World, par2, par3 - 1, par4));
			return spawnBlock && var5 != Block.bedrock.blockID && !par1World.isBlockNormalCube(par2, par3, par4) && !par1World.getBlockMaterial(par2, par3, par4).isLiquid()
					&& !par1World.isBlockNormalCube(par2, par3 + 1, par4);
		}
	}


	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		findChunksForSpawning(MinecraftServer.getServer().worldServerForDimension(0));
		doWaterSpawning(MinecraftServer.getServer().worldServerForDimension(0));
		doLandSpawning(MinecraftServer.getServer().worldServerForDimension(0));
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
