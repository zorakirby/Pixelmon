package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItems;

public enum EnumEthers {
	Ether(178, false, false, "ether"), MaxEther(194, true, false, "maxether"),
	Elixir(210, false, true, "elixir"), MaxElixir(226, true, true, "maxelixir");

	private EnumEthers(int index, boolean allPP, boolean allMoves, String filenamePrefix) {
		this.allPP = allPP;
		this.allMoves = allMoves;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
	}

	private boolean allPP;
	private boolean allMoves;
	private int index;
	private String filenamePrefix;

	public boolean restoresAllPP() {
		return allPP;
	}

	public boolean restoresAllMoves() {
		return allMoves;
	}

	public int getIndex() {
		return index;
	}

	public Item getItem() {
		if (index == 178)
			return PixelmonItems.ether;
		if (index == 194)
			return PixelmonItems.maxEther;
		if (index == 210)
			return PixelmonItems.elixir;
		if (index == 226)
			return PixelmonItems.maxElixir;
		return PixelmonItems.potion;
	}

	public String getTexture() {
		return filenamePrefix;
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