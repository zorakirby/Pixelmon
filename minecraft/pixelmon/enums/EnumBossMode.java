package pixelmon.enums;

import java.awt.Color;
import java.util.Random;

public enum EnumBossMode {
	Normal(0, -1, Color.WHITE, 1, 0), 
	Uncommon(1, 60, Color.CYAN, 1.2f, 5), 
	Rare(2, 30, Color.RED, 1.4f, 10), 
	Legendary(3, 10, Color.MAGENTA, 1.6f, 20);
	
	public int index;
	public int rarity;
	public Color colour;
	public float scaleFactor;
	public int extraLevels;

	private EnumBossMode(int index, int rarity, Color colour, float scaleFactor, int extraLevels) {
		this.index = index;
		this.rarity = rarity;
		this.colour = colour;
		this.scaleFactor = scaleFactor;
		this.extraLevels = extraLevels;
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
}
