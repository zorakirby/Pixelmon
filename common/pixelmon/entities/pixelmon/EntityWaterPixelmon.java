package pixelmon.entities.pixelmon;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.Pixelmon;
import pixelmon.WorldHelper;
import pixelmon.battles.BattleController;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.TrainerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.entities.pixelmon.stats.BattleStats;
import pixelmon.entities.pixelmon.stats.Level;
import pixelmon.entities.pixelmon.stats.IVStore;
import pixelmon.entities.pixelmon.stats.Moveset;
import pixelmon.entities.pixelmon.stats.Stats;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumType;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PokeballManager;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityWaterMob;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class EntityWaterPixelmon extends EntityPixelmon{
	public String name;
	public ArrayList<EnumType> type = new ArrayList<EnumType>();
	private float field_21080_l;
	public float swimSpeed = 1.5f;
	public float decayRate = 0.99f;
	public int refreshRate = 100;
	protected int depthRangeStart = 0;
	protected int depthRangeEnd = 100;
	public boolean isSwimming = true;
	private RidingHelper ridingHelper;
	private float length = 1.0f;

	public EntityWaterPixelmon(World par1World) {
		super(par1World);
	}

	float randomMotionSpeed;
	float randomMotionVecX;
	float randomMotionVecY;
	float randomMotionVecZ;
	private float field_21085_g;

	public void onLivingUpdate() {
		super.onLivingUpdate();
		// if (ridingHelper != null)
		// ridingHelper.onLivingUpdate();
		if (worldObj.isRemote) {
			motionX = motionY = motionZ = 0;
			return;
		}
		this.field_21085_g += this.field_21080_l;
		if (this.field_21085_g > ((float) Math.PI * 2F)) {
			this.field_21085_g -= ((float) Math.PI * 2F);
		}
		if (this.isInWater() && !isSwimming) {
			if (randomMotionSpeed <= swimSpeed / 2)
				randomMotionSpeed = swimSpeed;
			randomMotionSpeed *= decayRate;

			if (!worldObj.isRemote) {
				motionX = randomMotionVecX * randomMotionSpeed;
				motionY = randomMotionVecY * randomMotionSpeed * 0.05;
				motionZ = randomMotionVecZ * randomMotionSpeed;
			}

			renderYawOffset += ((-(float) Math.atan2(motionX, motionZ) * 180F) / (float) Math.PI - renderYawOffset) * 0.1F;
			rotationYaw = renderYawOffset;
		} else {
			surfaceHeight = this.posY;
			this.motionX = 0.0D;
			this.motionY -= 0.08D;
			this.motionY *= 0.9800000190734863D;
			this.motionZ = 0.0D;
		}
	}

	double surfaceHeight = 1000;

	protected void fall(float f) {
		if (!this.isInWater()) {
			super.fall(f);
		}
	}

	public void moveEntityWithHeading(float par1, float par2) {
		super.moveEntityWithHeading((float) motionX, (float) motionZ);
		// moveEntity(motionX, motionY, motionZ);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	int count = 0;
	public EntityTrainer trainer;

	protected void updateEntityActionState() {
		if (count == 0) {
			int wdepth = WorldHelper.getWaterDepth((int) posX, (int) posY, (int) posZ, worldObj);
			// if (rand.nextInt(90) == 0 || randomMotionVecX == 0.0F
			// && randomMotionVecY == 0.0F && randomMotionVecZ == 0.0F) {
			count = refreshRate - refreshRate / 2 + rand.nextInt(refreshRate / 2);
			randomMotionVecX = (-0.5f + rand.nextFloat()) * 0.2F;
			if (wdepth >= depthRangeEnd)
				randomMotionVecY = (0.02F + rand.nextFloat()) * 0.1F;
			else if (wdepth <= depthRangeStart)
				randomMotionVecY = (-0.02F + rand.nextFloat()) * 0.1F;
			else
				randomMotionVecY = rand.nextFloat() * 0.1F;
			randomMotionVecZ = (-0.5f + rand.nextFloat()) * 0.2F;
		}
		count--;

		despawnEntity();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this
	 * entity.
	 */
	public boolean getCanSpawnHere() {
		int wdepth = WorldHelper.getWaterDepth((int) posX, (int) posY, (int) posZ, worldObj);
		if (wdepth >= depthRangeStart && wdepth < depthRangeEnd)
			return true;
		return false;
	}
}
