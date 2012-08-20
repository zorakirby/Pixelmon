package pixelmon.entities.pixelmon.helpers;

import pixelmon.battles.BattleController;
import pixelmon.entities.EntityTrainer;
import net.minecraft.src.EntityLookHelper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public interface IHaveHelper {

	void catchInPokeball();

	void writeEntityToNBT(NBTTagCompound nbt);

	void setLocationAndAngles(double posX, double posY, double posZ, float rotationYaw, float f);

	void releaseFromPokeball();

	void setPositionAndRotation(double posX, double posY, double posZ, float rotationYaw, float rotationPitch);

	void evolve();

	PixelmonEntityHelper getHelper();

	EntityPlayer getOwner();

	void setOwner(EntityPlayer player);

	void unloadEntity();

	void writeEntityToStorageNBT(NBTTagCompound nbt);

	int getPokemonId();
	
	void setPokemonId(int uniqueEntityId);
	
	void jump();

	void doMoveEntity(double motionX, double motionY, double motionZ);

	World getWorldObj();

	String getLvlString();
}
