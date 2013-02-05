package pixelmon.enums;

import java.util.Random;

public enum EnumBossMode {
	Normal(0, -1), Uncommon(1, 60), Rare(2, 30), Legendary(3, 10);
	public int index;
	public int rarity;

	private EnumBossMode(int index, int rarity) {
		this.index = index;
		this.rarity = rarity;
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
