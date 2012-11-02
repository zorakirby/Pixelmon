package pixelmon.enums;

public enum EnumBadges {
	Balancebadge(0), Beaconbadge(16+1), Boulderbadge(2*16+1), Cascadebadge(3*16+1), Coalbadge(4*16+1);

	public int iconIndex;

	private EnumBadges(int iconIndex) {
		this.iconIndex = iconIndex;
	}
}
