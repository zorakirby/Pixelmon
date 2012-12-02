package pixelmon.enums;

public enum EnumApricorns {
	Black(4 * 16 + 8, 5 * 16 + 8), White(4 * 16 + 9, 5 * 16 + 9), Pink(4 * 16 + 10, 5* 16 + 10), Green(4 * 16 + 11, 5 * 16 + 11), Blue(4 * 16 + 12,
			5 * 16 + 12), Yellow(4 * 16 + 13, 5 * 16 + 13), Red(4 * 16 + 14, 5 * 16 + 14);

	public int iconIndex;
	public int meltedIconIndex;

	private EnumApricorns(int iconIndex, int meltedIconIndex) {
		this.iconIndex = iconIndex;
		this.meltedIconIndex = meltedIconIndex;
	}
}
