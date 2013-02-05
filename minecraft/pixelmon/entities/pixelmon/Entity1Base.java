package pixelmon.entities.pixelmon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumPokeballs;

public abstract class Entity1Base extends EntityTameable {
	public EnumPokeballs caughtBall;
	public boolean hitByPokeball = false;
	public boolean isMale;
	public boolean isInBall = false;
	public boolean isFainted = false;
	protected boolean isInitialised = false;

	public Entity1Base(World par1World) {
		super(par1World);
		dataWatcher.addObject(2, ""); // Name
		dataWatcher.addObject(3, ""); // NickName
		dataWatcher.addObject(4, -1); // pokemonId
		dataWatcher.addObject(21, (short) -1);
	}

	protected void init(String name) {
		setName(name);
		isInitialised = true;
		setBoss(EnumBossMode.Normal);
	}

	public String getName() {
		return dataWatcher.getWatchableObjectString(2);
	}

	public void setName(String name) {
		dataWatcher.updateObject(2, name);
	}

	@Override
	public String getEntityName() {
		return getName();
	}

	public void setBoss(EnumBossMode mode) {
		dataWatcher.updateObject(21, (short) mode.index);
	}

	public EnumBossMode getBossMode() {
		return EnumBossMode.getMode(dataWatcher.getWatchableObjectShort(21));
	}

	public String getNickname() {
		if (dataWatcher.getWatchableObjectString(3).equals(""))
			return getName();
		return dataWatcher.getWatchableObjectString(3);
	}

	public void setNickname(String nickname) {
		dataWatcher.updateObject(3, nickname);
	}

	public int getPokemonId() {
		return dataWatcher.getWatchableObjectInt(4);
	}

	public void setPokemonId(int id) {
		dataWatcher.updateObject(4, id);
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

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!isInitialised) {
			init(getName());
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("pixelmonID", dataWatcher.getWatchableObjectInt(4));
		nbt.setString("Name", dataWatcher.getWatchableObjectString(2));
		nbt.setString("Nickname", dataWatcher.getWatchableObjectString(3));
		if (caughtBall != null)
			nbt.setInteger("CaughtBall", caughtBall.getIndex());
		nbt.setBoolean("IsMale", isMale);
		nbt.setBoolean("IsInBall", isInBall);
		nbt.setBoolean("IsFainted", isFainted);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(4, nbt.getInteger("pixelmonID"));
		dataWatcher.updateObject(2, nbt.getString("Name"));
		init(getName());
		if (nbt.hasKey("Nickname"))
			dataWatcher.updateObject(3, nbt.getString("Nickname"));

		if (nbt.hasKey("CaughtBall"))
			caughtBall = EnumPokeballs.getFromIndex(nbt.getInteger("CaughtBall"));
		isMale = nbt.getBoolean("IsMale");
		isInBall = nbt.getBoolean("IsInBall");
		isFainted = nbt.getBoolean("IsFainted");
	}

	@Override
	public int getAge() {
		return 0;
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}
}
