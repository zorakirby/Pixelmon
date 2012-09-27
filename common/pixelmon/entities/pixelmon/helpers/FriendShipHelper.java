package pixelmon.entities.pixelmon.helpers;

import net.minecraft.src.NBTTagCompound;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FriendShipHelper {
	private static final int maxFriendship = 255;
	public int friendship = 0;
	private EntityPixelmon pixelmon;

	public FriendShipHelper(EntityPixelmon pixelmon) {
		this.pixelmon = pixelmon;
	}

	public void initFromCapture() {
		friendship = 70;  //load baseFriendhsip
	}

	public void initFromEgg() {
		friendship = 120;
	}
	
	public void resetFromTrade(){
		friendship = 70;
	}

	public boolean isFriendshipHighEnoughToEvolve() {
		return friendship >= 220;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Friendship", friendship);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		friendship = nbt.getInteger("Friendship");		
	}
}
