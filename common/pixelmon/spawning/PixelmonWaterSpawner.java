package pixelmon.spawning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WeightedRandom;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpecialSpawnEvent;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PixelmonWaterSpawner implements ITickHandler {
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
	public static final int findChunksForSpawning(WorldServer par0WorldServer) {
		eligibleChunksForSpawning.clear();
		int var3;
		int var6;

		for (var3 = 0; var3 < par0WorldServer.playerEntities.size(); ++var3) {
			EntityPlayer var4 = (EntityPlayer) par0WorldServer.playerEntities.get(var3);
			int var5 = MathHelper.floor_double(var4.posX / 16.0D);
			var6 = MathHelper.floor_double(var4.posZ / 16.0D);
			byte var7 = 8;

			for (int var8 = -var7; var8 <= var7; ++var8) {
				for (int var9 = -var7; var9 <= var7; ++var9) {
					boolean var10 = var8 == -var7 || var8 == var7 || var9 == -var7 || var9 == var7;
					ChunkCoordIntPair var11 = new ChunkCoordIntPair(var8 + var5, var9 + var6);

					if (!var10) {
						eligibleChunksForSpawning.put(var11, Boolean.valueOf(false));
					} else if (!eligibleChunksForSpawning.containsKey(var11)) {
						eligibleChunksForSpawning.put(var11, Boolean.valueOf(true));
					}
				}
			}
		}

		var3 = 0;
		ChunkCoordinates var31 = par0WorldServer.getSpawnPoint();

		if (countWaterPokemonEntities(par0WorldServer) <= EnumCreatureType.waterCreature.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256) {
			Iterator var35 = eligibleChunksForSpawning.keySet().iterator();
			ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
			Collections.shuffle(tmp);
			var35 = tmp.iterator();
			label108:

			while (var35.hasNext()) {
				ChunkCoordIntPair var37 = (ChunkCoordIntPair) var35.next();

				if (!((Boolean) eligibleChunksForSpawning.get(var37)).booleanValue()) {
					ChunkPosition var36 = getRandomSpawningPointInChunk(par0WorldServer, var37.chunkXPos, var37.chunkZPos);
					int var12 = var36.x;
					int var13 = var36.y;
					int var14 = var36.z;

					if (!par0WorldServer.isBlockNormalCube(var12, var13, var14) && par0WorldServer.getBlockMaterial(var12, var13, var14) == Material.water) {
						int var15 = 0;
						int var16 = 0;

						while (var16 < 3) {
							int var17 = var12;
							int var18 = var13;
							int var19 = var14;
							byte var20 = 6;
							String pokemonName = null;
							int var22 = 0;

							while (true) {
								if (var22 < 4) {
									label101: {
										var17 += par0WorldServer.rand.nextInt(var20) - par0WorldServer.rand.nextInt(var20);
										var18 += par0WorldServer.rand.nextInt(1) - par0WorldServer.rand.nextInt(1);
										var19 += par0WorldServer.rand.nextInt(var20) - par0WorldServer.rand.nextInt(var20);

										if (canWaterPokemonSpawnHere(par0WorldServer, var17, var18, var19)) {
											float var23 = (float) var17 + 0.5F;
											float var24 = (float) var18;
											float var25 = (float) var19 + 0.5F;

											if (par0WorldServer.getClosestPlayer((double) var23, (double) var24, (double) var25, 24.0D) == null) {
												float var26 = var23 - (float) var31.posX;
												float var27 = var24 - (float) var31.posY;
												float var28 = var25 - (float) var31.posZ;
												float var29 = var26 * var26 + var27 * var27 + var28 * var28;

												if (var29 >= 576.0F) {
													if (pokemonName == null) {
														pokemonName = getRandomCreatureName(par0WorldServer.provider, var17, var18, var19);

														if (pokemonName == null) {
															break label101;
														}
													}

													EntityLiving var38;

													try {
														var38 = PixelmonEntityList.createEntityByName(pokemonName, par0WorldServer);
													} catch (Exception var30) {
														var30.printStackTrace();
														return var3;
													}

													var38.setLocationAndAngles((double) var23, (double) var24, (double) var25,
															par0WorldServer.rand.nextFloat() * 360.0F, 0.0F);

													if (var38.getCanSpawnHere()) {
														++var15;
														par0WorldServer.spawnEntityInWorld(var38);

														if (var15 >= var38.getMaxSpawnedInChunk()) {
															continue label108;
														}
													}

													var3 += var15;
												}
											}
										}

										++var22;
										continue;
									}
								}

								++var16;
								break;
							}
						}
					}
				}
			}
		}
		return var3;

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
	public static String getRandomCreatureName(WorldProvider worldProvider, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getWaterSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? ((SpawnData) WeightedRandom.getRandomItem(rand, spawnData)).name : null;
	}

	/**
	 * Returns whether or not the specified creature type can spawn at the
	 * specified location.
	 */
	public static boolean canWaterPokemonSpawnHere(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMaterial(par2, par3, par4).isLiquid() && !par1World.isBlockNormalCube(par2, par3 + 1, par4);
	}

	/**
	 * determines if a skeleton spawns on a spider, and if a sheep is a
	 * different color
	 */
	private static void creatureSpecificInit(EntityLiving par0EntityLiving, World par1World, float par2, float par3, float par4) {
		LivingSpecialSpawnEvent event = new LivingSpecialSpawnEvent(par0EntityLiving, par1World, par2, par3, par4);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return;
		}

		if (par0EntityLiving instanceof EntitySpider && par1World.rand.nextInt(100) == 0) {
			EntitySkeleton var7 = new EntitySkeleton(par1World);
			var7.setLocationAndAngles((double) par2, (double) par3, (double) par4, par0EntityLiving.rotationYaw, 0.0F);
			par1World.spawnEntityInWorld(var7);
			var7.mountEntity(par0EntityLiving);
		} else if (par0EntityLiving instanceof EntitySheep) {
			((EntitySheep) par0EntityLiving).setFleeceColor(EntitySheep.getRandomFleeceColor(par1World.rand));
		} else if (par0EntityLiving instanceof EntityOcelot && par1World.rand.nextInt(7) == 0) {
			for (int var5 = 0; var5 < 2; ++var5) {
				EntityOcelot var6 = new EntityOcelot(par1World);
				var6.setLocationAndAngles((double) par2, (double) par3, (double) par4, par0EntityLiving.rotationYaw, 0.0F);
				var6.setGrowingAge(-24000);
				par1World.spawnEntityInWorld(var6);
			}
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		PixelmonSpawner.spawnTick(MinecraftServer.getServer().worldServerForDimension(0));
		findChunksForSpawning(MinecraftServer.getServer().worldServerForDimension(0));
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
