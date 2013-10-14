package pixelmon.enums;

import java.awt.Color;
import java.util.Random;

public enum EnumBossMode {
	Normal(0, -1, Color.WHITE, 1, 0, 0), Uncommon(1, 40, Color.GREEN, 1.2f, 5, 2), Rare(2, 30, Color.CYAN, 1.4f, 10, 3), Legendary(3, 20, Color.RED, 1.6f, 20,
			5), Ultimate(4, 10, Color.YELLOW, 1.8f, 40, 8);

	public int index;
	public int rarity;
	public Color colour;
	public float scaleFactor;
	public int extraLevels;
	public int numDroppedItems = 0;
	public float r;
	public float g;
	public float b;

	private EnumBossMode(int index, int rarity, Color colour, float scaleFactor, int extraLevels, int numDroppedItems) {
		this.index = index;
		this.rarity = rarity;
		this.colour = colour;
		this.scaleFactor = scaleFactor;
		this.extraLevels = extraLevels;
		this.numDroppedItems = numDroppedItems;
		if (colour == Color.CYAN) {
			r = 0.5f;
			g = 1f;
			b = 1f;
		} else if (colour == Color.GREEN) {
			r = 0.5f;
			g = 1f;
			b = 0.5f;
		} else if (colour == Color.RED) {
			r = 1f;
			g = 0.5f;
			b = 0.5f;
		} else if (colour == Color.YELLOW) {
			r = 1f;
			g = 1f;
			b = 0.5f;
		}
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
		if (colour == Color.GREEN)
			return 500000;
		if (colour == Color.CYAN)
			return 100000;
		if (colour == Color.RED)
			return -65280;
		if (colour == Color.YELLOW)
			return -255;
		return -1;
	}
}
