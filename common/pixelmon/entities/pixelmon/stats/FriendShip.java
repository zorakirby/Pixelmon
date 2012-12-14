package pixelmon.entities.pixelmon.stats;

import java.util.Random;

import net.minecraft.src.NBTTagCompound;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FriendShip {
	private static final int maxFriendship = 255;
	public int friendship = 0;
	private EntityPixelmon pixelmon;
	private Random rand = new Random();
	boolean luxuryBall = false;

	public FriendShip(EntityPixelmon pixelmon) {
		this.pixelmon = pixelmon;
	}

	public void initFromCapture() {
		friendship = pixelmon.baseStats.baseFriendship;
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
		nbt.setBoolean("LuxuryBall", luxuryBall);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		friendship = nbt.getInteger("Friendship");
		luxuryBall = nbt.getBoolean("LuxuryBall");
	}

	private int luxuryBonus() {

		if (luxuryBall)
			return 1 + rand.nextInt(2);
		else
			return 0;
	}

	private int tickCounter = 0;

	public void tick() {
		tickCounter++;
		if (tickCounter == 1000) {
			friendship++;
			friendship += luxuryBonus();
			tickCounter = 0;
		}
	}

	public void hurtByOwner() {
		friendship -= 20;
	}

	public void onLevelUp() {
		friendship += rand.nextInt(2) + 5 + luxuryBonus();
	}

	public void onFaint() {
		friendship -= 2;
	}

	public void captureLuxuryBall() {
		this.luxuryBall = true;
	}
}
