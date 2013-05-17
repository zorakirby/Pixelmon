package pixelmon.database;

import pixelmon.enums.EnumEvolutionRock;
import pixelmon.enums.EnumEvolutionStone;

public class EvolutionInfo {
	public enum InfoMode {
		biome, stone, friendship, trade, evolutionRock
	}

	public InfoMode mode;
	public EnumEvolutionStone evolutionStone;
	public String extraParam;
	public String pokemonName;
	public String extraParam2;
	public EnumEvolutionRock evolutionRock;
}
