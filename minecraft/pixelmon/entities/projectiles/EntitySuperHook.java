package pixelmon.entities.projectiles;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pixelmon.RandomHelper;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.spawning.SpawnData;
import pixelmon.spawning.SpawnRegistry;
import pixelmon.storage.PixelmonStorage;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.enums.EnumBiomes;

public class EntitySuperHook extends EntityFishHook implements IEntityAdditionalSpawnData {

	/** The tile this entity is on, X position */
	public int xTile;

	/** The tile this entity is on, Y position */
	public int yTile;

	/** The tile this entity is on, Z position */
	public int zTile;
	public int inTile;
	public boolean inGround;
	public int shake;
	public EntityPlayer angler;
	public int ticksInGround;
	public int ticksInAir;

	/** the number of ticks remaining until this fish can no longer be caught */
	public int ticksCatchable;

	/**
	 * The entity that the fishing rod is connected to, if any. When you right
	 * click on the fishing rod and the hook falls on to an entity, this it that
	 * entity.
	 */
	public Entity bobber;
	public int fishPosRotationIncrements;
	public double fishX;
	public double fishY;
	public double fishZ;
	public double fishYaw;
	public double fishPitch;
	@SideOnly(Side.CLIENT)
	public double velocityX;
	@SideOnly(Side.CLIENT)
	public double velocityY;
	@SideOnly(Side.CLIENT)
	public double velocityZ;

	public BattleController bc;
	public PlayerParticipant player1;
	public BiomeGenBase biome;
	public String rodType;

	public static HashMap<String, Integer> pixelmonRarity;

	public EntitySuperHook(World par1World, EntityPlayer par2EntityPlayer, String rodType) {
		super(par1World);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.shake = 0;
		this.ticksInAir = 0;
		this.ticksCatchable = 0;
		this.bobber = null;
		this.rodType = rodType;
		this.pixelmonRarity = new HashMap();
		this.ignoreFrustumCheck = true;
		this.angler = par2EntityPlayer;
		this.angler.fishEntity = this;
		this.setSize(0.25F, 0.25F);
		this.setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY + 1.62D - (double) par2EntityPlayer.yOffset, par2EntityPlayer.posZ,
				par2EntityPlayer.rotationYaw, par2EntityPlayer.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		float f = 0.4F;
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.calculateVelocity(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);

		this.rodType = rodType;
	}

	@Override
	public void onUpdate() {
		if (this.fishPosRotationIncrements > 0) {
			double d0 = this.posX + (this.fishX - this.posX) / (double) this.fishPosRotationIncrements;
			double d1 = this.posY + (this.fishY - this.posY) / (double) this.fishPosRotationIncrements;
			double d2 = this.posZ + (this.fishZ - this.posZ) / (double) this.fishPosRotationIncrements;
			double d3 = MathHelper.wrapAngleTo180_double(this.fishYaw - (double) this.rotationYaw);
			this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.fishPosRotationIncrements);
			this.rotationPitch = (float) ((double) this.rotationPitch + (this.fishPitch - (double) this.rotationPitch)
					/ (double) this.fishPosRotationIncrements);
			--this.fishPosRotationIncrements;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		} else {
			if (!this.worldObj.isRemote) {
				ItemStack itemstack = this.angler.getCurrentEquippedItem();

				if (this.angler.isDead || !this.angler.isEntityAlive() || this.angler.getCurrentEquippedItem() == null
						|| this.angler.getCurrentEquippedItem().getItem() != PixelmonItems.superRod || this.getDistanceSqToEntity(this.angler) > 1024.0D) {
					this.setDead();
					this.angler.fishEntity = null;
					return;
				}

				if (this.bobber != null) {
					if (!this.bobber.isDead) {
						this.posX = this.bobber.posX;
						this.posY = this.bobber.boundingBox.minY + (double) this.bobber.height * 0.8D;
						this.posZ = this.bobber.posZ;
						return;
					}

					this.bobber = null;
				}
			}

			if (this.shake > 0) {
				--this.shake;
			}

			if (this.inGround) {
				int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

				if (i == this.inTile) {
					++this.ticksInGround;

					if (this.ticksInGround == 1200) {
						this.setDead();
					}

					return;
				}

				this.inGround = false;
				this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			} else {
				++this.ticksInAir;
			}

			Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
			vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null) {
				vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord,
						movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
					this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d4 = 0.0D;
			double d5;

			for (int j = 0; j < list.size(); ++j) {
				Entity entity1 = (Entity) list.get(j);

				if (entity1.canBeCollidedWith() && (entity1 != this.angler || this.ticksInAir >= 5)) {
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double) f, (double) f, (double) f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null) {
						d5 = vec3.distanceTo(movingobjectposition1.hitVec);

						if (d5 < d4 || d4 == 0.0D) {
							entity = entity1;
							d4 = d5;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null) {
					if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.angler), 0)) {
						this.bobber = movingobjectposition.entityHit;
					}
				} else {
					this.inGround = true;
				}
			}

			if (!this.inGround) {
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
				float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
				this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

				for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
					;
				}

				while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
					this.prevRotationPitch += 360.0F;
				}

				while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
					this.prevRotationYaw -= 360.0F;
				}

				while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
					this.prevRotationYaw += 360.0F;
				}

				this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
				this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
				float f2 = 0.92F;

				if (this.onGround || this.isCollidedHorizontally) {
					f2 = 0.5F;
				}

				byte b0 = 5;
				double d6 = 0.0D;

				for (int k = 0; k < b0; ++k) {
					double d7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (k + 0) / (double) b0 - 0.125D + 0.125D;
					double d8 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (k + 1) / (double) b0 - 0.125D + 0.125D;
					AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d7, this.boundingBox.minZ, this.boundingBox.maxX,
							d8, this.boundingBox.maxZ);

					if (this.worldObj.isAABBInMaterial(axisalignedbb1, Material.water)) {
						d6 += 1.0D / (double) b0;
					}
				}

				if (d6 > 0.0D) {
					if (this.ticksCatchable > 0) {
						--this.ticksCatchable;
					} else {
						short short1 = 500;

						if (this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1,
								MathHelper.floor_double(this.posZ))) {
							short1 = 300;
						}

						if (this.rand.nextInt(short1) == 0) {
							this.ticksCatchable = this.rand.nextInt(30) + 10;
							this.motionY -= 0.20000000298023224D;
							this.playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
							float f3 = (float) MathHelper.floor_double(this.boundingBox.minY);
							int l;
							float f4;
							float f5;

							for (l = 0; (float) l < 1.0F + this.width * 20.0F; ++l) {
								f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
								f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
								this.worldObj.spawnParticle("bubble", this.posX + (double) f5, (double) (f3 + 1.0F), this.posZ + (double) f4, this.motionX,
										this.motionY - (double) (this.rand.nextFloat() * 0.2F), this.motionZ);
							}

							for (l = 0; (float) l < 1.0F + this.width * 20.0F; ++l) {
								f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
								f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
								this.worldObj.spawnParticle("splash", this.posX + (double) f5, (double) (f3 + 1.0F), this.posZ + (double) f4, this.motionX,
										this.motionY, this.motionZ);
							}
						}
					}
				}

				if (this.ticksCatchable > 0) {
					this.motionY -= (double) (this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2D;
				}

				d5 = d6 * 2.0D - 1.0D;
				this.motionY += 0.03999999910593033D * d5;

				if (d6 > 0.0D) {
					f2 = (float) ((double) f2 * 0.9D);
					this.motionY *= 0.8D;
				}

				this.motionX *= (double) f2;
				this.motionY *= (double) f2;
				this.motionZ *= (double) f2;
				this.setPosition(this.posX, this.posY, this.posZ);
			}
		}
	}

	@Override
	public int catchFish() {

		if (this.worldObj.isRemote) {
			return 0;
		} else {
			byte b0 = 0;

			if (this.bobber != null) {
				double d0 = this.angler.posX - this.posX;
				double d1 = this.angler.posY - this.posY;
				double d2 = this.angler.posZ - this.posZ;
				double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
				double d4 = 0.1D;
				this.bobber.motionX += d0 * d4;
				this.bobber.motionY += d1 * d4 + (double) MathHelper.sqrt_double(d3) * 0.08D;
				this.bobber.motionZ += d2 * d4;
				b0 = 3;
			} else if (this.ticksCatchable > 0) {
				ChunkCoordinates cc = angler.getPlayerCoordinates();

				getListOfValidWaterPixelmon();

				this.angler.addStat(StatList.fishCaughtStat, 1);
				this.angler.addChatMessage("Oh, a bite!");

				int intTotalRarity = 0;
				for (Integer rarity : this.pixelmonRarity.values()) {
					intTotalRarity += rarity;
				}

				int number = RandomHelper.getRandomNumberBetween(0, intTotalRarity);

				int intCurrentRarityTotal = 0;
				for (String pixelmon : this.pixelmonRarity.keySet()) {
					intCurrentRarityTotal += this.pixelmonRarity.get(pixelmon);
					if (intCurrentRarityTotal >= number) {
						try {
							EntityPixelmon pixelmonEntity = (EntityPixelmon) PixelmonEntityList.createEntityByName(pixelmon, this.worldObj);
							WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pixelmonEntity);
							pixelmonEntity.setPosition(cc.posX, cc.posY + 2, cc.posZ);
							EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) angler).getFirstAblePokemon(
									angler.worldObj);
							bc = new BattleController(new PlayerParticipant((EntityPlayerMP) angler, player1firstPokemon), wildPixelmon);
							wildPixelmon.StartBattle(bc, wildPixelmon);

							this.worldObj.spawnEntityInWorld(pixelmonEntity);
						} catch (Exception e) {
							System.out.println("Error in Spawning Pixelmon caught by rod. " + e.toString());
						}

						break;
					}
				}

				b0 = 1;
			} else {
				angler.sendChatToPlayer("Not even a nibble...");
			}

			if (this.inGround) {
				b0 = 2;
			}

			this.setDead();
			this.angler.fishEntity = null;
			return b0;
		}
	}

	public void getListOfValidWaterPixelmon() {

		// Always have magikarp on list
		pixelmonRarity.clear();
		pixelmonRarity.put("Squirtle", 10);
		pixelmonRarity.put("Wartortle", 5);
		pixelmonRarity.put("Psyduck", 150);
		pixelmonRarity.put("Golduck", 90);
		pixelmonRarity.put("Poliwag", 180);
		pixelmonRarity.put("Poliwhirl", 90);
		pixelmonRarity.put("Tentacool", 190);
		pixelmonRarity.put("Slowpoke", 80);
		pixelmonRarity.put("Slowbro", 40);
		pixelmonRarity.put("Seel", 80);
		pixelmonRarity.put("Dewgong", 40);
		pixelmonRarity.put("Shelder", 100);
		pixelmonRarity.put("Krabby", 110);
		pixelmonRarity.put("Kingler", 55);
		pixelmonRarity.put("Horsea", 100);
		pixelmonRarity.put("Seadra", 50);
		pixelmonRarity.put("Goldeen", 100);
		pixelmonRarity.put("Seaking", 50);
		pixelmonRarity.put("Staryu", 100);
		pixelmonRarity.put("Magikarp", 200);
		pixelmonRarity.put("Gyarados", 1);
		pixelmonRarity.put("Lapras", 10);
		pixelmonRarity.put("Dratini", 5);
		pixelmonRarity.put("Dragonair", 3);

		// Set Rarity Threshold
		int rarityThreshold = 170;
		if (this.angler.getCurrentEquippedItem() == new ItemStack(PixelmonItems.superRod)) {
			rarityThreshold = 0;

		}
		this.biome = this.worldObj.getWorldChunkManager().getBiomeGenAt((int) this.posX, (int) this.posZ);
		List<SpawnData> spawnDataList = SpawnRegistry.getWaterSpawnsForBiome(this.biome);
		for (SpawnData pixelmon : spawnDataList) {
			if (!(pixelmon.name.equals("Magikarp"))) {
				if (pixelmon.rarity >= rarityThreshold) {
				}

			}
		}

	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeInt(this.angler != null ? this.angler.entityId : 0);

	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		this.angler = (EntityPlayer) this.worldObj.getEntityByID(data.readInt());

	}
}
