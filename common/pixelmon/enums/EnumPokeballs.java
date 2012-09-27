package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import net.minecraft.src.Item;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball", 0, 0), GreatBall(1, 1.5, "greatball", 0, 1), UltraBall(2, 2, "ultraball", 0, 2), MasterBall(3, 255, "masterball", 0, 3), LevelBall(
			4, 1, "levelball", 1, 8);

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix, int iconIndexX, int iconIndexY) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndexX + iconIndexY * 16;
	}

	private double ballBonus;
	private int index;
	private String filenamePrefix;
	private int iconIndex;

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
		for (EnumPokeballs b: values())
			if (b.index == index) return b;

		return EnumPokeballs.PokeBall;
	}
}
