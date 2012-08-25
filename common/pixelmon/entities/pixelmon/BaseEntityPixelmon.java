package pixelmon.entities.pixelmon;

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
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumType;
import pixelmon.storage.PixelmonStorage;

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

public abstract class BaseEntityPixelmon extends EntityTameable implements IHaveHelper {

	public String name;
	public ArrayList<EnumType> type = new ArrayList<EnumType>();
	public PixelmonEntityHelper helper = new PixelmonEntityHelper(this);
	public boolean litUp;
	public int litLevel;
	private RidingHelper ridingHelper;
	private float length = 1.0f;

	public BaseEntityPixelmon(World par1World) {
		super(par1World);
		helper.stats.IVs = PixelmonIVStore.CreateNewIVs();
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		dataWatcher.addObject(18, "");
		dataWatcher.addObject(19, -1); // pokemonId
		dataWatcher.addObject(20, (short) 0);
		getNavigator().setAvoidsWater(true);
		helper.aggression = rand.nextInt(11) - 5;
	}

	public void init() {
		if ((new Random()).nextFloat() < 1 / 8192f) {
			System.out.println("Shiny " + name + " spawned");
			dataWatcher.updateObject(20, (short) 1);
		}
		moveSpeed = getMoveSpeed();// + getMoveSpeed();
		helper.stats.BaseStats = DatabaseStats.GetBaseStats(name);
		helper.giScale = helper.stats.BaseStats.giScale;
		health = 11;
		if (rand.nextInt(100) < helper.stats.BaseStats.MalePercent)
			helper.isMale = true;
		else
			helper.isMale = false;
		type.add(helper.stats.BaseStats.Type1);
		if (helper.stats.BaseStats.Type2 != EnumType.Mystery)
			type.add(helper.stats.BaseStats.Type2);

		helper.getLvl();
		setSize(helper.stats.BaseStats.Width, helper.stats.BaseStats.Height + helper.hoverHeight);
		length = helper.stats.BaseStats.Length;
		if (helper.stats.BaseStats.IsRideable)
			ridingHelper = new RidingHelper(this, worldObj);

	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float halfWidth = this.width / 2.0F;
		float halfLength = this.length / 2.0F;
		if (helper != null)
			this.boundingBox.setBounds(par1 - (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize, par5 - (double) halfLength, par1 + (double) halfWidth, par3
					- (double) this.yOffset + (double) this.ySize + (double) height + helper.hoverHeight, par5 + (double) halfLength);
		else
			this.boundingBox.setBounds(par1 - (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize, par5 - (double) halfLength, par1 + (double) halfWidth, par3
					- (double) this.yOffset + (double) this.ySize + (double) height, par5 + (double) halfLength);
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and
	 * blocks. This enables the entity to be pushable on contact, like boats or
	 * minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return par1Entity.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	public abstract void loadAI();

	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture() {
		if (dataWatcher.getWatchableObjectShort(20) == 1)
			return "/pixelmon/texture/pokemon-shiny/shiny" + name.toLowerCase() + ".png";
		else
			return "/pixelmon/texture/pokemon/" + name.toLowerCase() + ".png";
	}

	public EntityTrainer trainer;

	public void onDeath(DamageSource damagesource) {
		if (worldObj.isRemote) {
			super.onDeath(damagesource);
			if (getOwner() != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).isIn(helper)) {
				String s = "Your " + getName() + " fainted!";
				ChatHandler.sendChat(getOwner(), s);
				helper.isFainted = true;
				helper.setHealth(0);
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

	// Random Crap I
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (health - par2 < 0) {
			par2 = health;
		}
		if (!worldObj.isRemote) {
			boolean flag = super.attackEntityFrom(par1DamageSource, par2);
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
		helper.aggression = 0;
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
		if (e instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon e1 = (BaseEntityPixelmon) e;
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
		if (currentPixelmon instanceof BaseEntityPixelmon) {
			this.posX = ((BaseEntityPixelmon) currentPixelmon).posX;
			this.posY = ((BaseEntityPixelmon) currentPixelmon).posY;
			this.posZ = ((BaseEntityPixelmon) currentPixelmon).posZ;
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
		if (helper.bc != null) {
			helper.bc.update();
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

	public void setLvlString(String string) {
		dataWatcher.updateObject(18, string);
	}

	public String getLvlString() {
		return dataWatcher.getWatchableObjectString(18);
	}

	public int getPokemonId() {
		return dataWatcher.getWatchableObjectInt(19);
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

	@Override
	public void jump() {
		super.jump();
	}

	@Override
	public double getMountedYOffset() {
		if (ridingHelper != null)
			return ridingHelper.getMountedYOffset();
		else
			return super.getMountedYOffset();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (ridingHelper != null)
			ridingHelper.onLivingUpdate();
	}

	@Override
	public void moveEntity(double d, double d1, double d2) {
		if (ridingHelper != null)
			ridingHelper.moveEntity(d, d1, d2);
		else
			super.moveEntity(d, d1, d2);
	}

	@Override
	public void updateRidden() {
		if (ridingHelper != null)
			ridingHelper.updateRidden();
		else
			super.updateRidden();
	}

	public void doMoveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
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
}
