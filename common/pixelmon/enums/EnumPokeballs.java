package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import net.minecraft.src.Item;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball", 0, 0, 4, 12, 4, 13), GreatBall(1, 1.5, "greatball", 0, 1, 5, 12, 5, 13), UltraBall(2, 2, "ultraball", 0, 2, 6, 12, 6, 13), MasterBall(
			3, 255, "masterball", 0, 3, 0, 0, 0, 0), LevelBall(4, 1, "levelball", 1, 8, 15, 12, 15, 13), MoonBall(5, 1, "moonball", 1, 15, 14, 12, 14, 13), FriendBall(
			6, 1, "friendball", 1, 3, 12, 12, 12, 13), LoveBall(7, 1, "loveball", 0, 6, 11, 12, 11, 13);

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix, int iconIndexX, int iconIndexY, int lidIconIndexX, int lidIconIndexY,
			int discIconIndexX, int discIconIndexY) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndexX + iconIndexY * 16;
		this.lidIconIndex = lidIconIndexX + lidIconIndexY * 16;
		this.discIconIndex = discIconIndexX + discIconIndexY * 16;
	}

	private double ballBonus;
	private int index;
	private String filenamePrefix;
	private int iconIndex;
	public int lidIconIndex;
	public int discIconIndex;

	public double getBallBonus() {
		return ballBonus;
	}

	public int getIndex() {
		return index;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public Item getItem() {
		return PixelmonItemsPokeballs.getItemFromEnum(this);
	}

	public String getTexture() {
		return filenamePrefix + ".png";
	}

	public String getFlashRedTexture() {
		return filenamePrefix + "flashing.png";
	}

	public String getCaptureTexture() {
		return filenamePrefix + "captured.png";
	}

	public static EnumPokeballs getFromIndex(int index) {
		for (EnumPokeballs b : values())
			if (b.index == index)
				return b;

		return EnumPokeballs.PokeBall;
	}

	public Object getLid() {
		return PixelmonItemsPokeballs.getLidFromEnum(this);
	}
}
