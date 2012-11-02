package pixelmon.enums;

public enum EnumBadges {
	Balancebadge(0), Beaconbadge(16), Boulderbadge(2*16), Cascadebadge(3*16), Coalbadge(4*16), Cobblebadge(5*16), Dynamobadge(6*16), Earthbadge(7*16)
	,Featherbadge(8*16), Fenbadge(9*16), Fogbadge(10*16), Forestbadge(11*16), Glacierbadge(12*16), Heatbadge(13*16), Hivebadge(14*16), Iciclebadge(15*16)
	,Knucklebadge(0+1), Marshbadge(16+1), Mindbadge(2*16+1), Minebadge(3*16+1), Mineralbadge(4*16+1), Plainbadge(5*16+1), Rainbowbadge(6*16+1), Rainbadge(7*16+1)
	,Relicbadge(8*16+1), Risingbadge(9*16+1), Soulbadge(10*16+1), Stonebadge(11*16+1), Stormbadge(12*16+1), Thunderbadge(13*16+1), Volcanobadge(14*16+1), Zephyrbadge(15*16+1);

	public int iconIndex;

	private EnumBadges(int iconIndex) {
		this.iconIndex = iconIndex;
	}
}
