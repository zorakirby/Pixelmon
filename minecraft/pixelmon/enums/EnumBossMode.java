package pixelmon.enums;

import java.awt.Color;
import java.util.Random;

public enum EnumBossMode {
	Normal(0, -1, Color.WHITE), Uncommon(1, 60, Color.CYAN), Rare(2, 30, Color.RED), Legendary(3, 10, Color.MAGENTA);
	public int index;
	public int rarity;
	public Color colour;

	private EnumBossMode(int index, int rarity, Color colour) {
		this.index = index;
		this.rarity = rarity;
		this.colour = colour;
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
