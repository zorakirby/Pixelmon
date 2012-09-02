package pixelmon.entities.pixelmon;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.*;
import pixelmon.battles.*;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.TrainerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.database.*;
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.LevelHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper.Aggression;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumType;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtTarget;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class EntityPixelmon extends Entity8HoldsItems{


	public EntityPixelmon(World par1World) {
		super(par1World);
		
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		getNavigator().setAvoidsWater(true);
	}

	protected void init(String name) {
		super.init(name);
		moveSpeed = getMoveSpeed();
		health = 11;
	}

	public void onDeath(DamageSource damagesource) {
		if (worldObj.isRemote) {
			super.onDeath(damagesource);
			if (getOwner() != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).isIn(this)) {
				String s = "Your " + getName() + " fainted!";
				ChatHandler.sendChat(getOwner(), s);
				isFainted = true;
				setHealth(0);
				catchInPokeball();
			} else {
				super.onDeath(damagesource);
				this.setDead();
			}
		}
	}

	// Getters and Setters
	public float getMoveSpeed() {
		return 0.3f;
	}

	public int getMaxHealth() {
		if (helper == null || helper.stats == null)
			return 1;
		return helper.stats.HP;
	}

	public String getName() {
		return name;
	}

	public void setHealth(int i) {
		health = i;
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected boolean isValidTarget(Entity entity) {
		return helper.isValidTarget(entity);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (!worldObj.isRemote) {
			boolean flag = super.attackEntityFrom(par1DamageSource, par2);
			if (health - par2 < 0) {
				par2 = health;
				this.onDeath(par1DamageSource);
			}
			if (par1DamageSource.fireDamage())
				dataWatcher.updateObject(21, (short) 1);
			Entity entity = par1DamageSource.getEntity();
			if (getOwner() != null)
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT(helper);
			if (isValidTarget(entity)) {
				setAttackTarget((EntityLiving) entity);
				setTarget(entity);
			}
			helper.lvl.updateEntityString();
			return flag;
		}
		return false;
	}

	public boolean interact(EntityPlayer entity) {
		if (!worldObj.isRemote)
			return helper.interact(entity);
		else
			return false;
	}

	public void catchInPokeball() {
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT(helper);
		helper.isInBall = true;
		unloadEntity();
	}

	public void releaseFromPokeball() {
		helper.aggression = Aggression.passive;
		worldObj.spawnEntityInWorld(this);
		helper.isInBall = false;
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}

	public abstract void evolve();

	protected void evolve(PixelmonEntityHelper entity) {
		helper.evolve(entity);
	}

	public void clearAttackTarget() {
		setTarget(null);
		setAttackTarget(null);
	}

	public void setAttackTarget(EntityLiving e) {
		super.setAttackTarget(e);
		super.setTarget(e);
		if (e instanceof EntityPixelmon) {
			EntityPixelmon e1 = (EntityPixelmon) e;
			if (e1.getAttackTarget() == null)
				e1.setAttackTarget(this);
		}
	}

	public boolean hasOwner() {
		return !MathHelper.stringNullOrLengthZero(getOwnerName());
	}

	public World getWorldObj() {
		return worldObj;
	}

	// public void renderLevelUpEffects() {
	// EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this,
	// "magicCrit");
	// Minecraft.getMinecraft().effectRenderer.addEffect(entitycrit2fx);
	// }

	public boolean LearnsAttackAtLevel(int level) {
		return DatabaseMoves.LearnsAttackAtLevel(name, level);
	}

	public ArrayList<Attack> getAttacksAtLevel(int level) {
		return DatabaseMoves.getAttacksAtLevel(name, level);
	}

	public void LearnMove() {
		if (helper.moveset.size() >= 4) {
			ArrayList<Attack> attacks = getAttacksAtLevel(helper.lvl.getLevel());
			for (int i = 0; i < attacks.size(); i++)
				getOwner().openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), worldObj, dataWatcher.getWatchableObjectInt(19), attacks.get(i).attackIndex, 0);
		}
	}

	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && this.worldObj.getFullBlockLightValue(var1, var2, var3) > 8 && super.getCanSpawnHere();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		if (trainer != null && !isStorage)
			return;
		super.writeEntityToNBT(nbt);
		helper.writeToNBT(nbt);
		if (getOwner() != null)
			nbt.setString("pixelmonOwner", getOwnerName());
	}

	boolean isStorage = false;

	public void writeEntityToStorageNBT(NBTTagCompound var1) {
		isStorage = true;
		writeEntityToNBT(var1);
		isStorage = false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		helper.readFromNBT(nbt);
		if (nbt.hasKey("pixelmonOwner"))
			super.setOwner("pixelmonOwner");
	}

	public void setMotion(int i, int j, int k) {
		motionX = i;
		motionY = j;
		motionZ = k;
	}

	public void setLocationAndAngles(IHaveHelper currentPixelmon) {
		if (currentPixelmon instanceof EntityPixelmon) {
			this.posX = ((EntityPixelmon) currentPixelmon).posX;
			this.posY = ((EntityPixelmon) currentPixelmon).posY;
			this.posZ = ((EntityPixelmon) currentPixelmon).posZ;
		} else if (currentPixelmon instanceof EntityWaterPixelmon) {
			this.posX = ((EntityWaterPixelmon) currentPixelmon).posX;
			this.posY = ((EntityWaterPixelmon) currentPixelmon).posY;
			this.posZ = ((EntityWaterPixelmon) currentPixelmon).posZ;
		}
	}

	public PixelmonEntityHelper getHelper() {
		return helper;
	}

	public ItemStack getHeldItem() {
		return getHelper().getHeldItem();
	}

	public void onUpdate() {
		if (helper != null) {
			helper.onUpdate();
		}
		if (litUp = true) {
			double po11 = this.lastTickPosX;
			double po22 = this.lastTickPosY;
			double po33 = this.lastTickPosZ;
			int par11 = (int) po11;
			int par22 = (int) po22;
			int par33 = (int) po33;
			double par44 = po11 + po22 + po33;
			int par444 = worldObj.getBlockLightValue(par11, par22, par33);
			double po1 = this.posX;
			double po2 = this.posY;
			double po3 = this.posZ;
			int par1 = (int) po1;
			int par2 = (int) po2;
			int par3 = (int) po3;
			double par4 = po1 + po2 + po3;
			if (par44 != par4) {
				worldObj.setLightValue(EnumSkyBlock.Block, par11, par22, par33, par444);
			} else {
				if (par444 < litLevel)
					worldObj.setLightValue(EnumSkyBlock.Block, par11, par22, par33, litLevel);
			}
		}
		super.onUpdate();
	}

	public EntityPlayer getOwner() {
		if (super.getOwner() instanceof EntityPlayer)
			return (EntityPlayer) super.getOwner();
		return null;
	}

	public void setOwner(EntityPlayer owner) {
		super.setOwner(owner.username);
	}

	public void unloadEntity() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.add(this);
		worldObj.unloadEntities(list);
		clearAttackTarget();
		if (helper.bc != null) {
			helper.bc = null;
		}
	}





	public void setPokemonId(int id) {
		dataWatcher.updateObject(19, id);
	}

	public boolean getIsShiny() {
		return dataWatcher.getWatchableObjectShort(20) == 1;
	}

	public void setIsShiny(boolean isShiny) {
		if (isShiny)
			dataWatcher.updateObject(20, (short) 1);
		else
			dataWatcher.updateObject(20, (short) 0);
	}


	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return ("pixelmon." + name.toLowerCase());
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	// @Override
	// protected String getHurtSound()
	// {
	// return "mob.cowhurt";
	// }
	//
	// /**
	// * Returns the sound this mob makes on death.
	// */
	// @Override
	// protected String getDeathSound()
	// {
	// return "mob.cowhurt";
	// }

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	public void setTrainer(EntityTrainer entityTrainer) {
		// TODO Auto-generated method stub
		
	}
}
