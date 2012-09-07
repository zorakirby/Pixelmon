package pixelmon.enums;

public enum EnumEvolutionStone {
	
	Firestone,
	Thunderstone,
	Waterstone,
	Sunstone,
	Leafstone,
	Dawnstone,
	Duskstone,
	Moonstone,
	Shinystone;
	
	public static EnumEvolutionStone getEvolutionStone(String name){
		for (EnumEvolutionStone e: values())
			if (e.toString().equalsIgnoreCase(name)) return e;
		return null;
	}

}
