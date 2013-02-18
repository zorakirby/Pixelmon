package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseStats;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.entities.pixelmon.stats.FriendShip;
import pixelmon.entities.pixelmon.stats.IVStore;
import pixelmon.entities.pixelmon.stats.Level;
import pixelmon.entities.pixelmon.stats.Stats;
import pixelmon.enums.EnumType;
import pixelmon.storage.PixelmonStorage;

public abstract class Entity3HasStats extends Entity2HasModel {

	protected Level level;
	public Stats stats;
	public BaseStats baseStats;
	public FriendShip friendship;
	public ArrayList<EnumType> type = new ArrayList<EnumType>();
	public boolean doesHover = false;
	public float hoverHeight = 0f;
	public float length;
	public boolean doesLevel = true;
	private static BaseStats[] baseStatsStore = new BaseStats[650];

	public Entity3HasStats(World par1World) {
		super(par1World);
		dataWatcher.addObject(14, (short) 1000); // scale
		stats = new Stats();
		level = new Level((EntityPixelmon) this);
		friendship = new FriendShip((EntityPixelmon) this);
		dataWatcher.addObject(20, (short) 10); // MaxHP
		dataWatcher.addObject(7, (short) health);
	}

	public int getCatchRate() {
		float c = baseStats.catchRate;
		c *= getBossMode().catchRateModifier;
		return (int) c;
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {
		if (super.isEntityInsideOpaqueBlock())
			if (worldObj.getBlockMaterial((int) posX + 1, (int) posY, (int) posZ) == Material.air)
				setPosition(posX + 1, posY, posZ);
			else if (worldObj.getBlockMaterial((int) posX - 1, (int) posY, (int) posZ) == Material.air)
				setPosition(posX - 1, posY, posZ);
			else if (worldObj.getBlockMaterial((int) posX, (int) posY + 1, (int) posZ) == Material.air)
				setPosition(posX, posY + 1, posZ);
			else if (worldObj.getBlockMaterial((int) posX, (int) posY - 1, (int) posZ) == Material.air)
				setPosition(posX, posY - 1, posZ);
			else if (worldObj.getBlockMaterial((int) posX, (int) posY, (int) posZ + 1) == Material.air)
				setPosition(posX, posY, posZ + 1);
			else if (worldObj.getBlockMaterial((int) posX, (int) posY, (int) posZ - 1) == Material.air)
				setPosition(posX, posY, posZ - 1);

		return super.isEntityInsideOpaqueBlock();
	}

	@Override
	protected void init(String name) {
		super.init(name);
		getBaseStats(name);

		stats.IVs = IVStore.CreateNewIVs();
		setSize(baseStats.width, baseStats.height + hoverHeight);
		setType();
		length = baseStats.length;

		if (rand.nextInt(100) < baseStats.malePercent)
			isMale = true;
		else
			isMale = false;
		isImmuneToFire = type.contains(EnumType.Fire);

		if (level.getLevel() == -1) {
			int spawnLevelRange = baseStats.spawnLevelRange;
			int spawnLevel = baseStats.spawnLevel;
			if (spawnLevelRange <= 0)
				level.setLevel(spawnLevel);
			else
				level.setLevel(spawnLevel + rand.nextInt(spawnLevelRange));
			setEntityHealth(stats.HP);
		}
	}

	void getBaseStats(String name) {
		boolean has = false;
		for (int i = 0; i < baseStatsStore.length; i++) {
			if (baseStatsStore[i] == null)
				break;
			if (baseStatsStore[i].pixelmonName == name) {
				has = true;
				baseStats = baseStatsStore[i];
				break;
			}
		}
		if (!has) {
			baseStats = loadBaseStats(getName());
			for (int i = 0; i < baseStatsStore.length; i++)
				if (baseStatsStore[i] == null)
					baseStatsStore[i] = (baseStats);
		}
	}

	private BaseStats loadBaseStats(String name) {
		BaseStats store = DatabaseStats.GetBaseStats(name);
		return store;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {
		if (getOwner() != null)
			friendship.onFaint();
		super.onDeath(par1DamageSource);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (par1DamageSource.getSourceOfDamage() == getOwner())
			friendship.hurtByOwner();
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	private void setType() {
		type.add(baseStats.type1);
		if (baseStats.type2 != EnumType.Mystery)
			type.add(baseStats.type2);
	}

	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		if (baseStats != null) {
			if (((EntityPixelmon) this).pokemonLocation == SpawnLocation.Water && isInWater()) {
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
				return;
			}
		}
		super.moveEntityWithHeading(par1, par2);
	}

	@Override
	public void evolve(String evolveTo) {
		super.evolve(evolveTo);
		getBaseStats(evolveTo);
		type.clear();
		setType();
		updateStats();

		if (getOwner() != null)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT((EntityPixelmon) this);
	}

	@Override
	protected boolean canDespawn() {
		if (getOwner() != null)
			return false;
		return true;
	}

	protected void fall(float f) {
		if (baseStats != null) {
			if (((EntityPixelmon) this).pokemonLocation == SpawnLocation.Water)
				return;
			if (baseStats.canFly)
				return;
		}
		super.fall(f);
	}

	@Override
	public boolean canBreatheUnderwater() {
		if (baseStats != null) {
			if (((EntityPixelmon) this).pokemonLocation == SpawnLocation.Water || baseStats.type1 == EnumType.Water || baseStats.type2 == EnumType.Water)
				return true;
			else
				return false;
		}
		return true;
	}

	public int getMaxHealth() {
		if (isInitialised)
			return dataWatcher.getWatchableObjectShort(20);
		else
			return 10;
	}

	@Override
	public void setEntityHealth(int par1) {
		super.setEntityHealth(par1);
		updateHealth();
	}

	public void updateHealth() {
		if (health > stats.HP)
			health = stats.HP;
		if (health < 0)
			health = 0;
		dataWatcher.updateObject(7, (short) health);
		if (getOwner() != null)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT((EntityPixelmon) this);
	}

	public void setScale(float scale) {
		dataWatcher.updateObject(14, (short) (scale * 1000));
	}

	public float getScale() {
		return ((float) dataWatcher.getWatchableObjectShort(14)) / 1000.0f;
	}

	public float getMoveSpeed() {
		return 0.3f;
	}

	@Override
	public int getHealth() {
		if (dataWatcher.getWatchableObjectShort(7) != health)
			health = dataWatcher.getWatchableObjectShort(7);
		return super.getHealth();
	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float scale = 1;
		float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
		if (isInitialised)
			scale = getScale() * scaleFactor * getScaleFactor();
		float halfWidth = this.width * scale / 2.0F;
		float halfLength = this.length * scale / 2.0F;
		if (baseStats != null)
			this.boundingBox.setBounds(par1 - (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize, par5 - (double) halfLength, par1 + (double) halfWidth, par3
					- (double) this.yOffset + (double) this.ySize + (double) height * scale + hoverHeight, par5 + (double) halfLength);
		else
			this.boundingBox.setBounds(par1 - (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize, par5 - (double) halfLength, par1 + (double) halfWidth, par3
					- (double) this.yOffset + (double) this.ySize + (double) height * scale, par5 + (double) halfLength);
	}

	public void updateStats() {
		stats.setLevelStats(getNature(), baseStats, level.getLevel());
		dataWatcher.updateObject(20, (short) stats.HP);
		updateHealth();
	}

	public Level getLvl() {
		return level;
	}

	@Override
	public void onUpdate() {
		if (getOwner() != null)
			friendship.tick();
		super.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		stats.writeToNBT(nbt);
		level.writeToNBT(nbt);
		friendship.writeToNBT(nbt);
		nbt.setBoolean("DoesLevel", doesLevel);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		stats.readFromNBT(nbt);
		friendship.readFromNBT(nbt);
		if (nbt.hasKey("DoesLevel"))
			doesLevel = nbt.getBoolean("DoesLevel");
		else
			doesLevel = true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return rand.nextInt(baseStats.maxGroupSize - baseStats.minGroupSize + 1) + baseStats.minGroupSize;
	}
}