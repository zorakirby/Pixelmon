package pixelmon.entities.pixelmon.stats;

import java.util.Random;

import net.minecraft.src.NBTTagCompound;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FriendShip {
	private static final int maxFriendship = 255;
	public int friendship = 0;
	private EntityPixelmon pixelmon;
	private Random rand = new Random();

	public FriendShip(EntityPixelmon pixelmon) {
		this.pixelmon = pixelmon;
	}

	public void initFromCapture() {
		friendship = pixelmon.baseStats.friendshipBase;
	}

	public void initFromEgg() {
		friendship = 120;
	}

	public void resetFromTrade() {
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

	private int tickCounter = 0;

	public void tick() {
		tickCounter++;
		if (tickCounter == 1000) {
			friendship++;
			tickCounter = 0;
		}
	}

	public void hurtByOwner() {
		friendship -= 20;
	}

	public void onLevelUp() {
		friendship += rand.nextInt(2) + 5;
	}

	public void onFaint() {
		friendship -= 2;
	}
}
