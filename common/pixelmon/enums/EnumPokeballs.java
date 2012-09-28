package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import net.minecraft.src.Item;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball", 0, 0, 5, 13, 5, 14), GreatBall(1, 1.5, "greatball", 0, 1, 6, 13, 6, 14), UltraBall(2, 2, "ultraball", 0, 2, 7, 13, 7, 14), MasterBall(3, 255, "masterball", 0, 3, 0, 0, 0, 0), LevelBall(
			4, 1, "levelball", 1, 8, 16, 13, 16, 14), MoonBall(5, 1, "moonball", 1, 15, 15, 13, 15, 14);

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
		if (index == 0)
			return PixelmonItemsPokeballs.pokeBall;
		if (index == 1)
			return PixelmonItemsPokeballs.greatBall;
		if (index == 2)
			return PixelmonItemsPokeballs.ultraBall;
		if (index == 3)
			return PixelmonItemsPokeballs.masterBall;
		if (index == 4)
			return PixelmonItemsPokeballs.levelBall;
		if (index == 5)
			return PixelmonItemsPokeballs.moonBall;
		return PixelmonItemsPokeballs.pokeBall;
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
}
