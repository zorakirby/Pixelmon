package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItems;
import pixelmon.items.IEnumItem;
import pixelmon.items.ItemPotion;

public enum EnumPotions implements IEnumItem{
	Potion(2, 20, 0, "potion"), SuperPotion(18, 50, 0, "superpotion"),
	HyperPotion(34, 200, 0, "hyperpotion"), MaxPotion(50, 0, 100, "maxpotion");

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
	
	public ItemPotion getItem(int useless){
		return getItem();
	}

	public ItemPotion getItem() {
		Item result = null;
		switch(this){
		case HyperPotion: result = PixelmonItems.hyperPotion;break;
		case MaxPotion: result = PixelmonItems.maxPotion; break;
		case Potion: result = PixelmonItems.potion; break;
		case SuperPotion: result = PixelmonItems.superPotion; break;
		}
		return (ItemPotion) result;
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
		else
			return EnumPotions.Potion;
	}

	@Override
	public int numTypes() {
		return 1;
	}
}