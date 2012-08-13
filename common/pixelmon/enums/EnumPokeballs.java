package pixelmon.enums;

import pixelmon.items.PixelmonItems;
import net.minecraft.src.Item;
import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball"), GreatBall(1, 1.5, "greatball"), UltraBall(2, 2, "ultraball"), MasterBall(3, 255, "masterball");

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
	}

	private double ballBonus;
	private int index;
	private String filenamePrefix;

	public double getBallBonus() {
		return ballBonus;
	}

	public int getIndex() {
		return index;
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
		if (index ==0) return EnumPokeballs.PokeBall;
		if (index ==1) return EnumPokeballs.GreatBall;
		if (index ==2) return EnumPokeballs.UltraBall;
		if (index ==3) return EnumPokeballs.MasterBall;
		else return EnumPokeballs.PokeBall;
	}
}
