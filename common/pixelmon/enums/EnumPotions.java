package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import net.minecraft.src.Item;

public enum EnumPotions {
	Potion(2, 20, 0, "potion", 0), SuperPotion(18, 50, 0, "superpotion", 16), 
	HyperPotion(34, 200, 0, "hyperpotion", 32), MaxPotion(50, 0, 100, "maxpotion", 48);

	private EnumPotions(int index, int healAmount, int healPercent, String filenamePrefix,
			int iconIndex) {
		this.healAmount = healAmount;
		this.healPercent = healPercent;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndex;
	}

	private int healAmount;
	private int healPercent;
	private int index;
	private String filenamePrefix;
	private int iconIndex;

	public int getHealAmount() {
		return healAmount;
	}

	public int getHealPercent() {
		return healPercent;
	}

	public int getIndex() {
		return index;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public Item getItem() {
		if (index == 2)
			return PixelmonItems.potion;
		if (index == 18)
			return PixelmonItems.superPotion;
		if (index == 34)
			return PixelmonItems.hyperPotion;
		if (index == 50)
			return PixelmonItems.maxPotion;
		return PixelmonItems.potion;
	}

	public String getTexture() {
		return filenamePrefix + ".png";
	}

	public static EnumPotions getFromIndex(int index) {
		if (index == 2)
			return EnumPotions.Potion;
		if (index == 18)
			return EnumPotions.SuperPotion;
		if (index == 34)
			return EnumPotions.HyperPotion;
		if (index == 50)
			return EnumPotions.MaxPotion;
		else
			return EnumPotions.Potion;
	}
}
