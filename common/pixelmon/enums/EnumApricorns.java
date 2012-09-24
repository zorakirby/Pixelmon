package pixelmon.enums;

public enum EnumApricorns {
	Black(11000, 15 * 16 + 9), White(11001, 15 * 16 + 10), Orange(11002, 15 * 16 + 11), Green(11003, 15 * 16 + 12), Blue(11004, 15 * 16 + 13), Yellow(11005, 15 * 16 + 14), Red(11006, 15 * 16 + 15);

	public int itemIndex;
	public int iconIndex;

	private EnumApricorns(int index, int iconIndex) {
		itemIndex = index;
		this.iconIndex = iconIndex;
	}
}
