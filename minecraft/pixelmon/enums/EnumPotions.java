package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItems;

public enum EnumPotions {
	Potion(2, 20, 0, "potion"), SuperPotion(18, 50, 0, "superpotion"),
	HyperPotion(34, 200, 0, "hyperpotion"), MaxPotion(50, 0, 100, "maxpotion"), Revive(2, 20, 0, "Revive");

	private EnumPotions(int index, int healAmount, int healPercent, String filenamePrefix) {
		this.healAmount = healAmount;
		this.healPercent = healPercent;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
	}

	private int healAmount;
	private int healPercent;
	private int index;
	private String filenamePrefix;

	public int getHealAmount() {
		return healAmount;
	}

	public int getHealPercent() {
		return healPercent;
	}

	public int getIndex() {
		return index;
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
		if (index == 2)
			return PixelmonItems.revive;
		return PixelmonItems.potion;
	}

	public String getTexture() {
		return filenamePrefix;
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
		if(index == 2)
			return EnumPotions.Revive;
		else
			return EnumPotions.Potion;
	}
}