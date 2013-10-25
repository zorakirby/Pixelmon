package pixelmon.entities.pixelmon.stats;

import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumUpdateType;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FriendShip {
	private static final int maxFriendship = 255;
	private static final int minFriendship = 0;
	private int friendship = 0;
	private EntityPixelmon pixelmon;
	private Random rand = new Random();
	boolean luxuryBall = false;

	public FriendShip(EntityPixelmon pixelmon) {
		this.pixelmon = pixelmon;
	}

	public void initFromCapture() {
		friendship = pixelmon.baseStats.baseFriendship;
	}

	public void increaseFriendship(int amount) {
		if (pixelmon.getHeldItem() != null) {
			if (pixelmon.getHeldItem().itemID == PixelmonItemsHeld.sootheBell.itemID)
				friendship += amount * 1.5;
		} else
			friendship += amount;
		if (friendship > maxFriendship)
			friendship = maxFriendship;

		if (pixelmon.getOwner() != null)
			pixelmon.update(EnumUpdateType.Friendship);
	}

	public void decreaseFriendship(int amount) {
		friendship -= amount;
		if (friendship < minFriendship)
			friendship = minFriendship;
		pixelmon.update(EnumUpdateType.Friendship);
	}

	public int getFriendship() {
		return friendship;
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
		if (tickCounter >= 800) {
			if (pixelmon.getHeldItem() != null) {
				if (pixelmon.getHeldItem().itemID == PixelmonItemsHeld.sootheBell.itemID) {
					friendship = friendship + 2;
					tickCounter = 0;
				}
			}
			increaseFriendship(1 + luxuryBonus());
			tickCounter = 0;

		}
	}

	public void hurtByOwner() {
		decreaseFriendship(20);
	}

	public void onLevelUp() {
		increaseFriendship(rand.nextInt(2) + 5 + luxuryBonus());
	}

	public void onFaint() {
		decreaseFriendship(2);
	}

	public void captureLuxuryBall() {
		this.luxuryBall = true;
	}

	public void setFriendship(int i) {
		friendship = i;
	}
}
