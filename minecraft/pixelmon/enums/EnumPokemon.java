package pixelmon.enums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.database.DatabaseHelper;

public enum EnumPokemon {
	Abra("Abra"),
	Aerodactyl("Aerodactyl"),
	Alakazam("Alakazam"),
	Anorith("Anorith"),
	Arbok("Arbok"),
	Arcanine("Arcanine"),
	Archen("Archen"),
	Archeops("Archeops"),
	Armaldo("Armaldo"),
	Aron("Aron"),
	Articuno("Articuno"),
	Bastiodon("Bastiodon"),
	Bayleef("Bayleef"),
	Beedrill("Beedrill"),
	Beldum("Beldum"),
	Bellsprout("Bellsprout"),
	Blastoise("Blastoise"),
	Bulbasaur("Bulbasaur"),
	Butterfree("Butterfree"),
	Camerupt("Camerupt"),
	Carracosta("Carracosta"),
	Caterpie("Caterpie"),
	Chansey("Chansey"),
	Charizard("Charizard"),
	Charmander("Charmander"),
	Charmeleon("Charmeleon"),
	Chikorita("Chikorita"),
	Chinchou("Chinchou"),
	Clefable("Clefable"),
	Clefairy("Clefairy"),
	Cloyster("Cloyster"),
	Cranidos("Cranidos"),
	Croconaw("Croconaw"),
	Cubone("Cubone"),
	Cyndaquil("Cyndaquil"),
	Dewgong("Dewgong"),
	Diglett("Diglett"),
	Ditto("Ditto"),
	Dodrio("Dodrio"),
	Doduo("Doduo"),
	Dragonair("Dragonair"),
	Dragonite("Dragonite"),
	Dratini("Dratini"),
	Drifblim("Drifblim"),
	Drifloon("Drifloon"),
	Drowzee("Drowzee"),
	Dugtrio("Dugtrio"),
	Eevee("Eevee"),
	Ekans("Ekans"),
	Electabuzz("Electabuzz"),
	Electrode("Electrode"),
	Elekid("Elekid"),
	Espeon("Espeon"),
	Exeggcute("Exeggcute"),
	Exeggutor("Exeggutor"),
	Farfetchd("Farfetchd"),
	Fearow("Fearow"),
	Feraligatr("Feraligatr"),
	Flaaffy("Flaaffy"),
	Flareon("Flareon"),
	Flygon("Flygon"),
	Gastly("Gastly"),
	Gengar("Gengar"),
	Girafarig("Girafarig"),
	Geodude("Geodude"),
	Glaceon("Glaceon"),
	Gloom("Gloom"),
	Golbat("Golbat"),
	Goldeen("Goldeen"),
	Golduck("Golduck"),
	Golem("Golem"),
	Graveler("Graveler"),
	Grimer("Grimer"),
	Grotle("Grotle"),
	Grovyle("Grovyle"),
	Growlithe("Growlithe"),
	Gyarados("Gyarados"),
	Haunter("Haunter"),
	Hitmonchan("Hitmonchan"),
	Hitmonlee("Hitmonlee"),
	Horsea("Horsea"),
	Hypno("Hypno"),
	Ivysaur("Ivysaur"),
	Jigglypuff("Jigglypuff"),
	Jolteon("Jolteon"),
	Jynx("Jynx"),
	Kabuto("Kabuto"),
	Kadabra("Kadabra"),
	Kabutops("Kabutops"),
	Kakuna("Kakuna"),
	Kangaskhan("Kangaskhan"),
	Kingler("Kingler"),
	Koffing("Koffing"),
	Krabby("Krabby"),
	Krokorok("Krokorok"),
	Lanturn("Lanturn"),
	Lapras("Lapras"),
	Leafeon("Leafeon"),
	Lickitung("Lickitung"),
	Lunatone("Lunatone"),
	Machamp("Machamp"),
	Machoke("Machoke"),
	Machop("Machop"),
	Magikarp("Magikarp"),
	Magmar("Magmar"),
	Magnemite("Magnemite"),
	Magneton("Magneton"),
	Mankey("Mankey"),
	Mareep("Mareep"),
	Marowak("Marowak"),
	Meganium("Meganium"),
	Meowth("Meowth"),
	Metang("Metang"),
	Metapod("Metapod"),
	Mew("Mew"),
	Mewtwo("Mewtwo"),
	Miltank("Miltank"),
	Moltres("Moltres"),
	MrMime("MrMime"),
	Muk("Muk"),
	Nidoking("Nidoking"),
	Nidoqueen("Nidoqueen"),
	Nidoranfemale("Nidoranfemale"),
	Nidoranmale("Nidoranmale"),
	Nidorina("Nidorina"),
	Nidorino("Nidorino"),
	Ninetales("Ninetales"),
	Numel("Numel"),
	Oddish("Oddish"),
	Omanyte("Omanyte"),
	Omastar("Omastar"),
	Onix("Onix"),
	Paras("Paras"),
	Parasect("Parasect"),
	Persian("Persian"),
	Pichu("Pichu"),
	Pidgey("Pidgey"),
	Pikachu("Pikachu"),
	Pidgeotto("Pidgeotto"),
	Pidgeot("Pidgeot"),
	Piloswine("Piloswine"),
	Pinsir("Pinsir"),
	Poliwag("Poliwag"),
	Poliwhirl("Poliwhirl"),
	Poliwrath("Poliwrath"),
	Ponyta("Ponyta"),
	Porygon("Porygon"),
	Primeape("Primeape"),
	Psyduck("Psyduck"),
	Quilava("Quilava"),
	Raichu("Raichu"),
	Rampardos("Rampardos"),
	Rapidash("Rapidash"),
	Raticate("Raticate"),
	Rattata("Rattata"),
	Rayquaza("Rayquaza"),
	Rhydon("Rhydon"),
	Rhyhorn("Rhyhorn"),
	Sandile("Sandile"),
	Sandshrew("Sandshrew"),
	Sandslash("Sandslash"),
	Sceptile("Sceptile"),
	Scyther("Scyther"),
	Seadra("Seadra"),
	Seaking("Seaking"),
	Seel("Seel"),
	Shellder("Shellder"),
	Shieldon("Shieldon"),
	Slowbro("Slowbro"),
	Slowpoke("Slowpoke"),
	Snorlax("Snorlax"),
	Solrock("Solrock"),
	Spearow("Spearow"),
	Squirtle("Squirtle"),
	Staryu("Staryu"),
	Starmie("Starmie"),
	Swinub("Swinub"),
	Tangela("Tangela"),
	Tauros("Tauros"),
	Tentacool("Tentacool"),
	Tentacruel("Tentacruel"),
	Tirtouga("Tirtouga"),
	Torterra("Torterra"),
	Totodile("Totodile"),
	Trapinch("Trapinch"),
	Treecko("Treecko"),
	Turtwig("Turtwig"),
	Typhlosion("Typhlosion"),
	Umbreon("Umbreon"),
	Venomoth("Venomoth"),
	Venonat("Venonat"),
	Venusaur("Venusaur"),
	Vaporeon("Vaporeon"),
	Vibrava("Vibrava"),
	Victreebel("Victreebel"),
	Vileplume("Vileplume"),
	Voltorb("Voltorb"),
	Vulpix("Vulpix"),
	Wartortle("Wartortle"),
	Weedle("Weedle"),
	Weepinbell("Weepinbell"),
	Weezing("Weezing"),
	Wigglytuff("Wigglytuff"),
	Wynaut("Wynaut"),
	Zapdos("Zapdos"),
	Zubat("Zubat");

	private EnumPokemon(String name) {
		this.name = name;
	}

	public String name;

	public static boolean hasPokemon(String evolveTo) {
		for (EnumPokemon e : values()) {
			if (e.name.equalsIgnoreCase(evolveTo))
				return true;
		}
		return false;
	}

	public static EnumPokemon get(String name) {
		for (EnumPokemon e : values()) {
			if (e.name.equalsIgnoreCase(name))
				return e;
		}
		return null;
	}

	public static EnumPokemon getFromDBID(int id) {
		Connection con = DatabaseHelper.getConnection();
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from PIXELMON where PIXELMONID=" + id);
			while (rs.next()) {
				return EnumPokemon.get(rs.getString("PIXELMONFULLNAME"));
			}
		} catch (Exception e) {

		}
		return null;
	}
}
