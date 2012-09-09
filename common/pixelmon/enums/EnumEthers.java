package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import net.minecraft.src.Item;

public enum EnumEthers {
	Ether(178, false, false, "ether", 176), MaxEther(194, true, false, "maxether", 192),
	Elixir(210, false, true, "elixir", 208), MaxElixir(226, true, true, "maxelixir", 224);

	private EnumEthers(int index, boolean allPP, boolean allMoves, String filenamePrefix,
			int iconIndex) {
		this.allPP = allPP;
		this.allMoves = allMoves;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndex;
	}

	private boolean allPP;
	private boolean allMoves;
	private int index;
	private String filenamePrefix;
	private int iconIndex;

	public boolean restoresAllPP() {
		return allPP;
	}

	public boolean restoresAllMoves() {
		return allMoves;
	}

	public int getIndex() {
		return index;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public Item getItem() {
		if (index == 178)
			return PixelmonItems.potion;
		if (index == 194)
			return PixelmonItems.superPotion;
		if (index == 210)
			return PixelmonItems.hyperPotion;
		if (index == 226)
			return PixelmonItems.maxPotion;
		return PixelmonItems.potion;
	}

	public String getTexture() {
		return filenamePrefix + ".png";
	}

	public static EnumEthers getFromIndex(int index) {
		if (index == 178)
			return EnumEthers.Ether;
		if (index == 194)
			return EnumEthers.MaxEther;
		if (index == 210)
			return EnumEthers.Elixir;
		if (index == 226)
			return EnumEthers.MaxElixir;
		else
			return EnumEthers.Ether;
	}
}