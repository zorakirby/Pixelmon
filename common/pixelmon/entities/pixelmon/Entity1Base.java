package pixelmon.entities.pixelmon;

import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity1Base extends EntityTameable {
	public EnumPokeballs caughtBall;
	public boolean hitByPokeball;
	public boolean isMale;
	public boolean isInBall = false;
	public boolean isFainted = false;

	public Entity1Base(World par1World) {
		super(par1World);
		dataWatcher.addObject(2, ""); // Name
		dataWatcher.addObject(3, ""); // NickName
		dataWatcher.addObject(19, -1); // pokemonId
	}
	
	protected void init(String name){
		dataWatcher.updateObject(2, name);
	}

	public String getName(){
		return dataWatcher.getWatchableObjectString(2);
	}
	
	public void setName(String name){
		dataWatcher.updateObject(2, name);
	}
	
	public String getNickname(){
		if (dataWatcher.getWatchableObjectString(3).equals(""))
			return getName();
		return dataWatcher.getWatchableObjectString(3);
	}
	
	public void setNickname(String nickname){
		dataWatcher.updateObject(3, nickname);
	}
	
	public int getPokemonId() {
		return dataWatcher.getWatchableObjectInt(19);
	}
	
	public void setPokemonId(int id) {
		dataWatcher.updateObject(19, id);
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
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("PixelmonID", dataWatcher.getWatchableObjectInt(19));
		nbt.setString("PixelmonName", dataWatcher.getWatchableObjectString(2));
		nbt.setString("PixelmonNickname", dataWatcher.getWatchableObjectString(3));
		nbt.setBoolean("IsMale", isMale);
		nbt.setBoolean("IsInBall", isInBall);
		nbt.setBoolean("IsFainted", isFainted);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(19, nbt.getInteger("PixelmonID"));
		dataWatcher.updateObject(2, nbt.getString("PixelmonName"));
		dataWatcher.updateObject(3, nbt.getString("PixelmonNickname"));
		isMale = nbt.getBoolean("IsMale");
		isInBall = nbt.getBoolean("IsInBall");
		isFainted = nbt.getBoolean("IsFainted");
	}
	
	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}
}
