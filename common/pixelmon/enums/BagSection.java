package pixelmon.enums;

public enum BagSection {
	Pokeballs(0, "Poke Balls"), HP(1,"HP/PP Restore"), BattleItems(2, "Battle Items"), StatusRestore(3, "Status Restore");

	private BagSection(int index, String displayString) {
		this.index = index;
		this.displayString = displayString;
	}

	public String displayString;
	public int index;

	public static BagSection getSection(int i) {
		for (BagSection b : values()) {
			if (b.index == i)
				return b;
		}
		return null;
	}
}
