package pixelmon.database;

import pixelmon.enums.EnumEvolutionStone;

public class EvolutionInfo {
	public enum InfoMode {
		biome, stone, friendship
	}

	public InfoMode mode;
	public EnumEvolutionStone evolutionStone;
	public String extraParam;
	public String pokemonName;
}
