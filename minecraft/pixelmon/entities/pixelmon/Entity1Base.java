package pixelmon.entities.pixelmon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumGrowth;
import pixelmon.enums.EnumNature;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;

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
		dataWatcher.addObject(21, (short) -1); // BossMode
		dataWatcher.addObject(22, (short) -1); // Nature
		dataWatcher.addObject(23, (short) -1); // Growth
	}

	protected void init(String name) {
		setName(name);
		isInitialised = true;
		if (getBossMode() == null)
			setBoss(EnumBossMode.Normal);
		setBoss(EnumBossMode.Legendary);
		if (getNature() == null)
			setNature(EnumNature.getRandomNature());
		if (getGrowth() == null)
			setGrowth(EnumGrowth.getRandomGrowth());
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

	public void setNature(EnumNature nature) {
		dataWatcher.updateObject(22, (short) nature.index);
	}

	public void setGrowth(EnumGrowth growth) {
		dataWatcher.updateObject(23, (short) growth.index);
	}

	public EnumNature getNature() {
		return EnumNature.getNatureFromIndex(dataWatcher.getWatchableObjectShort(22));
	}

	public EnumGrowth getGrowth() {
		return EnumGrowth.getGrowthFromIndex(dataWatcher.getWatchableObjectShort(23));
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
		nbt.setShort("BossMode", dataWatcher.getWatchableObjectShort(21));
		nbt.setShort("Nature", dataWatcher.getWatchableObjectShort(22));
		nbt.setShort("Growth", dataWatcher.getWatchableObjectShort(23));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(4, nbt.getInteger("pixelmonID"));
		dataWatcher.updateObject(2, nbt.getString("Name"));
		if (!isInitialised)
			init(getName());
		if (nbt.hasKey("Nickname"))
			dataWatcher.updateObject(3, nbt.getString("Nickname"));

		if (nbt.hasKey("CaughtBall"))
			caughtBall = EnumPokeballs.getFromIndex(nbt.getInteger("CaughtBall"));
		isMale = nbt.getBoolean("IsMale");
		isInBall = nbt.getBoolean("IsInBall");
		isFainted = nbt.getBoolean("IsFainted");
		if (nbt.hasKey("BossMode"))
			dataWatcher.updateObject(21, nbt.getShort("BossMode"));
		else
			setBoss(EnumBossMode.Normal);
		if (nbt.hasKey("Nature"))
			dataWatcher.updateObject(22, nbt.getShort("Nature"));
		else
			setNature(EnumNature.getRandomNature());
		if (nbt.hasKey("Growth"))
			dataWatcher.updateObject(23, nbt.getShort("Growth"));
		else
			setGrowth(EnumGrowth.Ordinary);
	}

	@Override
	public int getAge() {
		return 0;
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}
	
	public boolean belongsTo(EntityPlayer player) {
		if (getOwner() == player)
			return true;

		return false;
	}

	public void updateNBT() {
		try {
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT((EntityPixelmon)this);
		} catch (PlayerNotLoadedException e) {

		}
	}
	
	public PlayerStorage getStorage() throws PlayerNotLoadedException{
		return PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner());
	}
}
