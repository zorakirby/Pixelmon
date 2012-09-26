package pixelmon.enums;

public enum EnumApricorns {
	Black(15 * 16 + 9), White(15 * 16 + 10), Pink(15 * 16 + 11), Green(15 * 16 + 12), Blue(15 * 16 + 13), Yellow(15 * 16 + 14), Red(15 * 16 + 15);

	public int iconIndex;

	private EnumApricorns(int iconIndex) {
		this.iconIndex = iconIndex;
	}
}
