package pixelmon.enums;

public enum EnumEvolutionStone {

	Firestone, Thunderstone, Waterstone, Sunstone, Leafstone, Dawnstone, Duskstone, Moonstone, Shinystone;

	private EnumEvolutionStone() {
	}

	public static EnumEvolutionStone getEvolutionStone(String name) {
		for (EnumEvolutionStone e : values())
			if (e.toString().equalsIgnoreCase(name))
				return e;
		return null;
	}

	public static boolean isEvolutionStone(String name) {
		for (EnumEvolutionStone e : values())
			if (e.toString().equalsIgnoreCase(name))
				return true;
		return false;
	}

}
