package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.helpers.*;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.entities.pixelmon.stats.LevelHelper;
import pixelmon.entities.pixelmon.stats.IVStore;
import pixelmon.entities.pixelmon.stats.Stats;
import pixelmon.enums.EnumType;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ModelBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity3HasStats extends Entity2HasModel {

	protected LevelHelper level;
	public Stats stats;
	public BaseStats baseStats;
	public ArrayList<EnumType> type = new ArrayList<EnumType>();
	public boolean doesHover = false;
	public float hoverHeight = 0f;
	public float length;

	public Entity3HasStats(World par1World) {
		super(par1World);
		dataWatcher.addObject(14, (short) 1000); // scale
		stats = new Stats();
		level = new LevelHelper((EntityPixelmon) this);
		dataWatcher.addObject(10, (short) 10); // MaxHP
		dataWatcher.addObject(7, (short) health);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		baseStats = DatabaseStats.GetBaseStats(name);
		stats.IVs = IVStore.CreateNewIVs();
		setSize(baseStats.Width, baseStats.Height + hoverHeight);
		setType();
		length = baseStats.Length;

		if (rand.nextInt(100) < baseStats.MalePercent)
			isMale = true;
		else
			isMale = false;
		isImmuneToFire = type.contains(EnumType.Fire);

		if (level.getLevel() == -1) {
			if (baseStats.SpawnLevelRange <= 0)
				level.setLevel(baseStats.SpawnLevel);
			else
				level.setLevel(baseStats.SpawnLevel + rand.nextInt(baseStats.SpawnLevelRange));
			setEntityHealth(stats.HP);
		}
	}

	private void setType() {
		type.add(baseStats.Type1);
		if (baseStats.Type2 != EnumType.Mystery)
			type.add(baseStats.Type2);
	}

	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		if (baseStats != null) {
			if (baseStats.creatureType == EnumCreatureType.waterCreature && isInWater()) {
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
				return;
			}
		}
		super.moveEntityWithHeading(par1, par2);
	}

	@Override
	public void evolve(String evolveTo) {
		super.evolve(evolveTo);
		baseStats = DatabaseStats.GetBaseStats(getName());
		type.clear();
		setType();
		if (getOwner() != null)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT((EntityPixelmon) this);
	}

	@Override
	protected boolean canDespawn() {
		if (getOwner() != null)
			return false;
		if (baseStats.creatureType == EnumCreatureType.creature)
			return false;
		return true;
	}

	protected void fall(float f) {
		if (baseStats != null) {
			if (baseStats.creatureType == EnumCreatureType.waterCreature)
				return;
			if (baseStats.CanFly)
				return;
		}
		super.fall(f);
	}

	@Override
	public boolean canBreatheUnderwater() {
		if (baseStats != null) {
			if (baseStats.creatureType == EnumCreatureType.waterCreature)
				return true;
			else
				return false;
		}
		return true;
	}

	public int getMaxHealth() {
		if (isInitialised)
			return dataWatcher.getWatchableObjectShort(10);
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
			scale = getScale() * scaleFactor;
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
		stats.setLevelStats(baseStats, level.getLevel());
		dataWatcher.updateObject(10, (short) stats.HP);
		updateHealth();
	}

	public LevelHelper getLvl() {
		return level;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		stats.writeToNBT(nbt);
		level.writeToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		stats.readFromNBT(nbt);
	}
}
