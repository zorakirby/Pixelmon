package pixelmon.config;

import pixelmon.enums.EnumPokemon;

public class StarterList {
	public static EnumPokemon[] StarterList = { EnumPokemon.Bulbasaur, EnumPokemon.Squirtle, EnumPokemon.Charmander, EnumPokemon.Chikorita,
			EnumPokemon.Totodile, EnumPokemon.Cyndaquil, EnumPokemon.Treecko, EnumPokemon.Mudkip, null, EnumPokemon.Turtwig, EnumPokemon.Piplup, null };

	public static void setStarterList(EnumPokemon[] starterList) {
		StarterList = starterList;
	}
}
