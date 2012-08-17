package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import net.minecraft.src.Item;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball", 0), GreatBall(1, 1.5, "greatball", 16), 
	UltraBall(2, 2, "ultraball", 32), MasterBall(3, 255, "masterball", 48);

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix,
			int iconIndex) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndex;
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
			return PixelmonItems.pokeBall;
		if (index == 1)
			return PixelmonItems.greatBall;
		if (index == 2)
			return PixelmonItems.ultraBall;
		if (index == 3)
			return PixelmonItems.masterBall;
		return PixelmonItems.pokeBall;
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
		if (index == 0)
			return EnumPokeballs.PokeBall;
		if (index == 1)
			return EnumPokeballs.GreatBall;
		if (index == 2)
			return EnumPokeballs.UltraBall;
		if (index == 3)
			return EnumPokeballs.MasterBall;
		else
			return EnumPokeballs.PokeBall;
	}
}
