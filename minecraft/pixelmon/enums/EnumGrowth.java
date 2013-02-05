package pixelmon.enums;

import java.util.Random;

public enum EnumGrowth {
	Pygmy(0, 0.5f, 5), Runt(1, 0.75f, 10), Small(2, 0.9f, 20), Ordinary(3, 1f, 30), Huge(4, 1.1f, 20), Giant(5, 1.25f, 10), Enormous(6, 1.5f, 5);

	public float scaleValue;
	public int rarity;
	public int index;

	private EnumGrowth(int index, float scaleValue, int rarity) {
		this.index = index;
		this.scaleValue = scaleValue;
		this.rarity = rarity;
	}

	public static EnumGrowth getRandomGrowth() {
		int tot = 0;
		int rndm = new Random().nextInt(100);
		for (EnumGrowth g : values()) {
			tot += g.rarity;
			if (rndm < tot)
				return g;
		}
		return EnumGrowth.Ordinary;
	}

	public static EnumGrowth getGrowthFromIndex(int index) {
		for (EnumGrowth g : values()) {
			if (g.index == index)
				return g;
		}
		return null;
	}

}
