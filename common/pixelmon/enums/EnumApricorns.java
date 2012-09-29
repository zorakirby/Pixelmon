package pixelmon.enums;

public enum EnumApricorns {
	Black(15 * 16 + 9, 14 * 16 + 9), White(15 * 16 + 10, 14 * 16 + 10), Pink(15 * 16 + 11, 14 * 16 + 11), Green(15 * 16 + 12, 14 * 16 + 12), Blue(15 * 16 + 13,
			14 * 16 + 13), Yellow(15 * 16 + 14, 14 * 16 + 14), Red(15 * 16 + 15, 14 * 16 + 15);

	public int iconIndex;
	public int meltedIconIndex;

	private EnumApricorns(int iconIndex, int meltedIconIndex) {
		this.iconIndex = iconIndex;
		this.meltedIconIndex = meltedIconIndex;
	}
}
