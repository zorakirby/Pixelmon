package pixelmon.enums;

import java.awt.Color;
import java.util.Random;

public enum EnumBossMode {
	Normal(0, -1, Color.WHITE, 1, 0, 1, 0), Uncommon(1, 60, Color.CYAN, 1.2f, 5, 0.75f, 1), Rare(2, 30, Color.RED, 1.4f, 10, 0.5f, 3), Legendary(3, 10, Color.YELLOW, 1.6f, 20,
			0.2f, 5);

	public int index;
	public int rarity;
	public Color colour;
	public float scaleFactor;
	public int extraLevels;
	public float catchRateModifier = 1;
	public int numDroppedItems = 0;

	private EnumBossMode(int index, int rarity, Color colour, float scaleFactor, int extraLevels, float catchRateModifier, int numDroppedItems) {
		this.index = index;
		this.catchRateModifier = catchRateModifier;
		this.rarity = rarity;
		this.colour = colour;
		this.scaleFactor = scaleFactor;
		this.extraLevels = extraLevels;
		this.numDroppedItems = numDroppedItems;
	}

	public static EnumBossMode getMode(int index) {
		for (EnumBossMode b : values())
			if (b.index == index)
				return b;
		return null;
	}

	public static EnumBossMode getRandomMode() {
		int val = new Random().nextInt(100);
		int total = 0;
		for (EnumBossMode b : values()) {
			if (b.rarity != -1) {
				total += b.rarity;
				if (val <= total)
					return b;
			}
		}
		return null;
	}

	public int getColourInt() {
		if (colour == Color.WHITE)
			return -1;
		if (colour == Color.CYAN)
			return 100000;
		if (colour == Color.RED)
			return -65280;
		if (colour == Color.YELLOW)
			return -255;
		return -1;
	}
}
