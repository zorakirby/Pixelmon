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
import pixelmon.enums.EnumPokemon;
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
		dataWatcher.addObject(EntityPixelmon.dwName, ""); // Name
		dataWatcher.addObject(EntityPixelmon.dwNickname, ""); // NickName
		dataWatcher.addObject(EntityPixelmon.dwPokemonID, -1); // pokemonId
		dataWatcher.addObject(EntityPixelmon.dwBossMode, (short) -1); // BossMode
		dataWatcher.addObject(EntityPixelmon.dwNature, (short) -1); // Nature
		dataWatcher.addObject(EntityPixelmon.dwGrowth, (short) -1); // Growth
	}

	protected void init(String name) {
		setName(name);
		isInitialised = true;
		if (getBossMode() == null)
			setBoss(EnumBossMode.Normal);
		if (getNature() == null)
			setNature(EnumNature.getRandomNature());
		if (getGrowth() == null)
			setGrowth(EnumGrowth.getRandomGrowth());
	}

	public String getName() {
		return dataWatcher.getWatchableObjectString(EntityPixelmon.dwName);
	}

	public void setName(String name) {
		dataWatcher.updateObject(EntityPixelmon.dwName, name);
	}

	@Override
	public String getEntityName() {
		return getName();
	}

	public void setBoss(EnumBossMode mode) {
		dataWatcher.updateObject(EntityPixelmon.dwBossMode, (short) mode.index);
	}

	public EnumBossMode getBossMode() {
		return EnumBossMode.getMode(dataWatcher.getWatchableObjectShort(EntityPixelmon.dwBossMode));
	}

	public void setNature(EnumNature nature) {
		dataWatcher.updateObject(EntityPixelmon.dwNature, (short) nature.index);
	}

	public void setGrowth(EnumGrowth growth) {
		dataWatcher.updateObject(EntityPixelmon.dwGrowth, (short) growth.index);
	}

	public EnumNature getNature() {
		return EnumNature.getNatureFromIndex(dataWatcher.getWatchableObjectShort(EntityPixelmon.dwNature));
	}

	public EnumGrowth getGrowth() {
		return EnumGrowth.getGrowthFromIndex(dataWatcher.getWatchableObjectShort(EntityPixelmon.dwGrowth));
	}

	public String getNickname() {
		if (dataWatcher.getWatchableObjectString(EntityPixelmon.dwNickname).equals(""))
			return EnumPokemon.getDisplayName(getName());
		return dataWatcher.getWatchableObjectString(EntityPixelmon.dwNickname);
	}

	public void setNickname(String nickname) {
		dataWatcher.updateObject(EntityPixelmon.dwNickname, nickname);
	}

	public int getPokemonId() {
		return dataWatcher.getWatchableObjectInt(EntityPixelmon.dwPokemonID);
	}

	public void setPokemonId(int id) {
		dataWatcher.updateObject(EntityPixelmon.dwPokemonID, id);
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
		nbt.setInteger("pixelmonID", dataWatcher.getWatchableObjectInt(EntityPixelmon.dwPokemonID));
		nbt.setString("Name", dataWatcher.getWatchableObjectString(EntityPixelmon.dwName));
		nbt.setString("Nickname", dataWatcher.getWatchableObjectString(EntityPixelmon.dwNickname));
		if (caughtBall != null)
			nbt.setInteger("CaughtBall", caughtBall.ordinal());
		nbt.setBoolean("IsMale", isMale);
		nbt.setBoolean("IsInBall", isInBall);
		nbt.setBoolean("IsFainted", isFainted);
		nbt.setShort("BossMode", dataWatcher.getWatchableObjectShort(EntityPixelmon.dwBossMode));
		nbt.setShort("Nature", dataWatcher.getWatchableObjectShort(EntityPixelmon.dwNature));
		nbt.setShort("Growth", dataWatcher.getWatchableObjectShort(EntityPixelmon.dwGrowth));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(EntityPixelmon.dwPokemonID, nbt.getInteger("pixelmonID"));
		dataWatcher.updateObject(EntityPixelmon.dwName, nbt.getString("Name"));
		if (!isInitialised)
			init(getName());
		if (nbt.hasKey("Nickname"))
			dataWatcher.updateObject(EntityPixelmon.dwNickname, nbt.getString("Nickname"));

		if (nbt.hasKey("CaughtBall"))
			caughtBall = EnumPokeballs.getFromIndex(nbt.getInteger("CaughtBall"));
		isMale = nbt.getBoolean("IsMale");
		isInBall = nbt.getBoolean("IsInBall");
		isFainted = nbt.getBoolean("IsFainted");
		if (nbt.hasKey("BossMode"))
			dataWatcher.updateObject(EntityPixelmon.dwBossMode, nbt.getShort("BossMode"));
		else
			setBoss(EnumBossMode.Normal);
		if (nbt.hasKey("Nature"))
			dataWatcher.updateObject(EntityPixelmon.dwNature, nbt.getShort("Nature"));
		else
			setNature(EnumNature.getRandomNature());
		if (nbt.hasKey("Growth"))
			dataWatcher.updateObject(EntityPixelmon.dwGrowth, nbt.getShort("Growth"));
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
	
	public boolean hasOwner() {
		if (getOwnerName().equals(""))
			return false;
		else
			return true;
	}
}
