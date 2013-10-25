package pixelmon.database;

import java.util.ArrayList;

import pixelmon.enums.EnumPokemon;

public class TrainerInfo {
	public String name;
	public ArrayList<EnumPokemon> partypokemon = new ArrayList<EnumPokemon>();
	public int level;
	public String greeting;
	public String winMessage;
	public String loseMessage;
	public String model;
	public int rarity;
	public int id;
}
