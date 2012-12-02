package pixelmon.enums;

public enum EnumEvolutionStone {

	Firestone(0), Thunderstone(3), Waterstone(4), Sunstone(-1), Leafstone(1), Dawnstone(-1), Duskstone(-1), Moonstone(2), Shinystone(-1);
	public int textureIndex;

	private EnumEvolutionStone(int textureIndex) {
		this.textureIndex = textureIndex;
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
