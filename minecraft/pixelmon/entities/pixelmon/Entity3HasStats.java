package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import pixelmon.api.events.EventType;
import pixelmon.api.events.PixelmonEventHandler;
import pixelmon.comm.EnumUpdateType;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseStats;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.entities.pixelmon.stats.FriendShip;
import pixelmon.entities.pixelmon.stats.IVStore;
import pixelmon.entities.pixelmon.stats.Level;
import pixelmon.entities.pixelmon.stats.Stats;
import pixelmon.enums.EnumType;
import pixelmon.pokedex.Pokedex;
import pixelmon.pokedex.Pokedex.DexRegisterStatus;
import pixelmon.storage.PlayerNotLoadedException;

public abstract class Entity3HasStats extends Entity2HasModel {

	protected Level level;
	public Stats stats;
	public BaseStats baseStats;
	public FriendShip friendship;
	public ArrayList<EnumType> type = new ArrayList<EnumType>();
	public float length;
	public boolean doesLevel = true;
	private static BaseStats[] baseStatsStore = new BaseStats[650];

	public Entity3HasStats(World par1World) {
		super(par1World);
		dataWatcher.addObject(EntityPixelmon.dwScale, (short) 1000); // scale
		stats = new Stats();
		level = new Level((EntityPixelmon) this);
		friendship = new FriendShip((EntityPixelmon) this);
		dataWatcher.addObject(EntityPixelmon.dwMaxHP, (short) 10); // MaxHP
	}

	public int getCatchRate() {
		float c = baseStats.catchRate;
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
		baseStats = getBaseStats(name);
		if (baseStats == null) {
			setDead();
			return;
		}
		stats.IVs = IVStore.CreateNewIVs();
		setSize(baseStats.width, baseStats.height + baseStats.hoverHeight);
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
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(stats.HP);
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.65);
			setHealth(stats.HP);
		}
	}

	float getMoveSpeed() {
		return 0.3f + (1 - (200f - stats.Speed) / 200f) * 0.3f;
	}

	public static BaseStats getBaseStatsFromStore(String name) {
		for (int i = 0; i < baseStatsStore.length; i++) {
			if (baseStatsStore[i] == null)
				break;
			if (baseStatsStore[i].pixelmonName.equals(name)) {
				return baseStatsStore[i];
			}
		}
		return null;
	}

	public static BaseStats getBaseStats(int index) {
		for (BaseStats b : baseStatsStore) {
			if (b != null && b.nationalPokedexNumber == index)
				return b;
		}
		return null;
	}
	
	public static BaseStats getBaseStatsFromDBID(int id) {
		for (BaseStats b : baseStatsStore) {
			if (b != null && b.id == id)
				return b;
		}
		return null;		
	}

	public static BaseStats getBaseStats(String name) {
		BaseStats baseStats = getBaseStatsFromStore(name);
		if (baseStats != null)
			return baseStats;
		baseStats = DatabaseStats.GetBaseStats(name);

		for (int i = 0; i < baseStatsStore.length; i++)
			if (baseStatsStore[i] == null) {
				baseStatsStore[i] = baseStats;
				break;
			}
		return baseStats;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {
		if (getOwner() != null) {
			friendship.onFaint();
			PixelmonEventHandler.fireEvent(EventType.PokemonFaint, (EntityPlayer) getOwner());
		}
		super.onDeath(par1DamageSource);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
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
		try {
			Pokedex pokedex = getStorage().pokedex;

			pokedex.set(Pokedex.nameToID(this.getName()), DexRegisterStatus.caught);
			pokedex.sendToPlayer((EntityPlayerMP) pokedex.owner);
		} catch (PlayerNotLoadedException e) {
		}
		if (getOwner() != null)
			update(EnumUpdateType.Name, EnumUpdateType.Stats);
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

	@Override
	public void setHealth(float par1) {
		super.setHealth(par1);
		updateHealth();
	}

	public void healEntityBy(int i) {
		setHealth(getHealth() + i);
	}

	public void updateHealth() {
		if (stats != null) {
			if (getHealth() > getMaxHealth())
				setHealth(getMaxHealth());
		}
		if (getHealth() < 0)
			setHealth(0);
		if (getOwner() != null && !worldObj.isRemote)
			update(EnumUpdateType.HP);
	}

	public void setPixelmonScale(float scale) {
		dataWatcher.updateObject(EntityPixelmon.dwScale, (short) (scale * 1000));
	}

	public float getPixelmonScale() {
		return ((float) dataWatcher.getWatchableObjectShort(EntityPixelmon.dwScale)) / 1000.0f;
	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float scale = 1;
		float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
		if (isInitialised)
			scale = getPixelmonScale() * scaleFactor * getScaleFactor();
		float halfWidth = this.width * scale / 2.0F;
		float halfLength = this.length * scale / 2.0F;
		if (baseStats != null)
			this.boundingBox.setBounds(par1 - (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize, par5 - (double) halfLength, par1
					+ (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize + (double) height * scale + baseStats.hoverHeight, par5
					+ (double) halfLength);
		else
			this.boundingBox.setBounds(par1 - (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize, par5 - (double) halfLength, par1
					+ (double) halfWidth, par3 - (double) this.yOffset + (double) this.ySize + (double) height * scale, par5 + (double) halfLength);
	}

	public void updateStats() {
		stats.setLevelStats(getNature(), baseStats, level.getLevel());
		dataWatcher.updateObject(EntityPixelmon.dwMaxHP, (short) stats.HP);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(stats.HP);
		updateHealth();
	}

	public Level getLvl() {
		return level;
	}

	@Override
	public void onUpdate() {
		if (getOwner() != null)
			friendship.tick();
		if (hasOwner() && getOwner() == null)
			setDead();
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