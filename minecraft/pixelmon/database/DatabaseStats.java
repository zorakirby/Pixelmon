package pixelmon.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.stats.Aggression;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.entities.pixelmon.stats.EVsStore;
import pixelmon.entities.pixelmon.stats.Rarity;
import pixelmon.entities.pixelmon.stats.RidingOffsets;
import pixelmon.entities.pixelmon.stats.SwimmingParameters;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumEvolutionRock;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumType;

public class DatabaseStats {

	public static BaseStats GetBaseStats(String name) {
		Connection conn = null;
		BaseStats store = new BaseStats(name);
		try {
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from PIXELMON where PIXELMONFULLNAME='" + name + "'");

			String error = "";
			boolean hasError = false;
			if (rs.next()) {
				store.pokemon = EnumPokemon.get(name);
				store.id = rs.getInt("PIXELMONID");
				store.hp = rs.getInt("BASEHP");
				if (rs.wasNull()) {
					error += "[HP]";
					hasError = true;
				}
				store.attack = rs.getInt("BASEATK");
				if (rs.wasNull()) {
					error += "[ATTACK]";
					hasError = true;
				}
				store.defence = rs.getInt("BASEDEF");
				if (rs.wasNull()) {
					error += "[DEFENSE]";
					hasError = true;
				}
				store.speed = rs.getInt("BASESPD");
				if (rs.wasNull()) {
					error += "[SPEED]";
					hasError = true;
				}
				store.spAtt = rs.getInt("BASESPATK");
				if (rs.wasNull()) {
					error += "[SPATTACK]";
					hasError = true;
				}
				store.spDef = rs.getInt("BASESPDEF");
				if (rs.wasNull()) {
					error += "[SPDEFENSE]";
					hasError = true;
				}
				if (hasError) {
					if (PixelmonConfig.printErrors)
						System.out.println("Error in BaseStats " + "[" + error + "]" + " For Pokemon : " + name);
				}
				store.catchRate = rs.getInt("CATCHRATE");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in CatchRate" + " For Pokemon : " + name);

				store.malePercent = rs.getInt("MALEPERCENT");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in MalePercent" + " For Pokemon : " + name);

				store.canFly = rs.getBoolean("CANFLY");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in CanFly" + " For Pokemon : " + name);
				store.doesHover = rs.getBoolean("DOESHOVER");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in DoesHover" + " For Pokemon : " + name);
				if (store.doesHover)
					store.hoverHeight = 1;
				store.canSurf = rs.getBoolean("CANSWIM");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in CANSWIM" + " For Pokemon : " + name);
				store.height = rs.getFloat("POKEDEXHEIGHT");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in Height" + " For Pokemon : " + name);
				store.width = rs.getFloat("POKEDEXWIDTH");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in Width" + " For Pokemon : " + name);
				store.length = rs.getFloat("POKEDEXLENGTH");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in Length" + " For Pokemon : " + name);
				store.weight = rs.getFloat("POKEDEXWEIGHT");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in Weight" + " For Pokemon : " + name);
				store.type1 = EnumType.parseTypeFromDBID(rs.getInt("PIXELMONTYPE1ID"));
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in Type" + " For Pokemon : " + name);
				store.baseExp = rs.getInt("BASEEXP");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in BaseExp" + " For Pokemon : " + name);
				store.experienceGroup = ExperienceGroup.getExperienceGroup(rs.getString("EXPERIENCEGROUP"));
				if (rs.wasNull() || store.experienceGroup == null)
					if (PixelmonConfig.printErrors)
						System.out.println("Error in ExperienceGroup" + " For Pokemon : " + name);
				store.nationalPokedexNumber = rs.getInt("NATIONALPOKEDEXNUMBER");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in NationalPokedexNumber" + " For Pokemon : " + name);
				store.spawnLevel = rs.getInt("MINSPAWNLEVEL");
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in SpawnLevel" + " For Pokemon : " + name);
				store.spawnLevelRange = rs.getInt("MAXSPAWNLEVEL") - store.spawnLevel;
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in SpawnLevelRange" + " For Pokemon : " + name);
				store.isRideable = rs.getBoolean("ISRIDEABLE");
				store.giScale = rs.getFloat("MODELSCALE");
				rs.getInt("PIXELMONTYPE2ID");
				if (!rs.wasNull())
					store.type2 = EnumType.parseTypeFromDBID(rs.getInt("PIXELMONTYPE2ID"));
				store.aggression = new Aggression(rs.getInt("PERCENTTIMID"), rs.getInt("PERCENTAGRESSIVE"), name);
				if (rs.wasNull())
					if (PixelmonConfig.printErrors)
						System.out.println("Error in Aggression" + " For Pokemon : " + name);
				store.baseFriendship = rs.getInt("BASEFRIENDSHIP");
				store.rarity = new Rarity(rs.getInt("RARITYDAY"), rs.getInt("RARITYDAWNDUSK"), rs.getInt("RARITYNIGHT"));

				store.evGain = new EVsStore();
				store.evGain.HP = rs.getInt("EVGAINHP");
				store.evGain.Attack = rs.getInt("EVGAINATK");
				store.evGain.Defence = rs.getInt("EVGAINDEF");
				store.evGain.SpecialAttack = rs.getInt("EVGAINSPATK");
				store.evGain.SpecialDefence = rs.getInt("EVGAINSPDEF");
				store.evGain.Speed = rs.getInt("EVGAINSPD");

				store.maxGroupSize = rs.getInt("MAXGROUPSIZE");
				store.minGroupSize = rs.getInt("MINGROUPSIZE");

				getPixelmonDrops(store, stat);
				getPixelmonEvolutions(store, stat, name);
				getPixelmonSpawnConditions(store, stat);
				getPixelmonSpawnLocations(store, stat);
				getPixelmonSpawnBiomes(store, stat);
				getPixelmonSwimmingParameters(store, stat);
				getPixelmonRidingOffsets(store, stat);
			}
			rs.close();
			return store;
		} catch (Exception e) {
			System.out.println("Error retrieving stats for Pokemon : " + name);
		}
		return null;
	}

	private static void getPixelmonRidingOffsets(BaseStats store, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from PIXELMONRIDE where PIXELMONID='" + store.id + "'");
		if (rs.next()) {
			rs.getDouble("STANDINGOFFSETX");
			if (!rs.wasNull()) {
				store.ridingOffsets = new RidingOffsets();
				store.ridingOffsets.setStandingOffsets(rs.getDouble("STANDINGOFFSETX"), rs.getDouble("STANDINGOFFSETY"), rs.getDouble("STANDINGOFFSETZ"));
			}
			rs.getDouble("MOVINGOFFSETX");
			if (!rs.wasNull()) {
				if (store.ridingOffsets == null)
					store.ridingOffsets = new RidingOffsets();
				store.ridingOffsets.setStandingOffsets(rs.getDouble("MOVINGOFFSETX"), rs.getDouble("MOVINGOFFSETY"), rs.getDouble("MOVINGOFFSETZ"));
			}
		}
		rs.close();
	}

	private static void getPixelmonSwimmingParameters(BaseStats store, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from PIXELMONSWIM where PIXELMONID='" + store.id + "'");
		if (rs.next()) {
			store.swimmingParameters = new SwimmingParameters(rs.getInt("SWIMDEPTHMIN"), rs.getInt("SWIMDEPTHMAX"), rs.getDouble("SWIMSPEED"),
					rs.getDouble("SWIMSPEEDDECAY"), rs.getInt("SWIMREFRESHRATE"));
		}
		rs.close();
	}

	private static void getPixelmonSpawnLocations(BaseStats store, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from PIXELMONSPAWNLOCATIONS where PIXELMONID='" + store.id + "'");
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString("LOCATION"));
		}
		rs.close();
		store.spawnLocations = SpawnLocation.getSpawnLocations(list);
		list.clear();
	}

	private static void getPixelmonSpawnConditions(BaseStats store, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from PIXELMONSPAWNRULES where PIXELMONID='" + store.id + "'");
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString("SPAWNRULE"));
		}
		rs.close();
		store.spawnConditions = SpawnConditions.ParseSpawnConditions(list);
		list.clear();
	}

	private static void getPixelmonDrops(BaseStats store, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from PIXELMONDROPS where PIXELMONID='" + store.id + "'");
		if (rs.next()) {
			store.droppedItem = rs.getString("DROPITEM");
		}
		rs.close();
	}

	private static void getPixelmonEvolutions(BaseStats store, Statement stat, String name) throws SQLException {
		ResultSet evrs = stat.executeQuery("select * from PIXELMONEVOLUTIONS where PIXELMONFROMID='" + store.id + "' AND EVOLVELEVEL IS NOT NULL");
		if (evrs.next()) {
			store.evolveLevel = evrs.getInt("EVOLVELEVEL");
			if (evrs.wasNull())
				if (PixelmonConfig.printErrors)
					System.out.println("Error in EvolveLevel" + " For Pokemon : " + name);
			store.evolveInto = EnumPokemon.getFromDBID(evrs.getInt("PIXELMONTOID"));
		}
		evrs.close();
		ArrayList<String> list = new ArrayList<String>();
		String p;
		while ((p = getPreEvolution(name, stat)) != null) {
			list.add(p);
			name = p;
		}
		store.preEvolutions = new EnumPokemon[list.size()];
		int i = 0;
		for (String poke : list) {
			store.preEvolutions[i++] = EnumPokemon.get(poke);
		}
		list.clear();
	}

	private static String getPreEvolution(String name, Statement stat) throws SQLException {
		int id = getPokemonDBIDFromName(name, stat);
		ResultSet rs = stat.executeQuery("select PIXELMONFROMID from PIXELMONEVOLUTIONS where PIXELMONTOID='" + id + "'");
		while (rs.next()) {
			int fromId = rs.getInt("PIXELMONFROMID");
			if (id == fromId)
				return null;
			rs.close();
			return getPokemonNameFromDBID(fromId, stat);
		}
		return null;
	}

	private static int getPokemonDBIDFromName(String name, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select PIXELMONID from PIXELMON where PIXELMONFULLNAME='" + name + "'");
		if (rs.next()) {
			int id = rs.getInt("PIXELMONID");
			rs.close();
			return id;
		}
		return -1;
	}

	private static String getPokemonNameFromDBID(int id, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select PIXELMONFULLNAME from PIXELMON where PIXELMONID='" + id + "'");
		if (rs.next()) {
			String name = rs.getString("PIXELMONFULLNAME");
			rs.close();
			return name;
		}
		return null;
	}

	static BiomeGenBase[] biomeMasterList;

	private static void getPixelmonSpawnBiomes(BaseStats store, Statement stat) throws SQLException {
		if (biomeMasterList == null) {
			fillBiomeMasterList(stat);
		}
		ResultSet rs = stat.executeQuery("select * from PIXELMONSPAWNBIOMES where PIXELMONID='" + store.id + "'");
		ArrayList<Integer> biomeIds = new ArrayList<Integer>();
		while (rs.next()) {
			biomeIds.add(rs.getInt("BIOMEID"));
		}
		rs.close();
		if (biomeIds.size() > 0) {
			BiomeGenBase[] biomes = new BiomeGenBase[biomeIds.size()];
			int i = 0;
			for (int id : biomeIds) {
				biomes[i] = biomeMasterList[id];
				i++;
			}
			store.biomes = biomes;
			return;
		}

		BiomeGenBase[] biomes = BiomeGenBase.biomeList;
		ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
		for (int i = 0; i < biomes.length; i++)
			if (biomes[i] != null)
				biomeList.add(biomes[i]);

		BiomeGenBase[] biomes2 = new BiomeGenBase[biomeList.size()];
		int j = 0;
		for (BiomeGenBase b : biomeList)
			biomes2[j++] = b;
		store.biomes = biomes2;
		// return biomes2;

	}

	private static void fillBiomeMasterList(Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from BIOMES");
		ArrayList<BiomeID> list = new ArrayList<BiomeID>();
		while (rs.next()) {
			BiomeID b = new BiomeID();
			b.biome = EnumBiomes.parseBiome(rs.getString("BIOMEALIAS")).getBiome();
			b.id = rs.getInt("BIOMEID");
			list.add(b);
		}
		rs.close();
		biomeMasterList = new BiomeGenBase[list.size()];
		for (BiomeID b : list)
			biomeMasterList[b.id] = b.biome;
	}

	public static ArrayList<EvolutionInfo> getEvolveList(int id) {
		ArrayList<EvolutionInfo> list = new ArrayList<EvolutionInfo>();
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from PIXELMONEVOLUTIONS where PIXELMONFROMID='" + id + "' AND EVOLVECONDITION IS NOT NULL");
			while (rs.next()) {
				String type = rs.getString("EVOLVECONDITION");
				String[] strList = type.split(";");
				for (String s : strList) {
					String[] sSplit = s.split(":");
					EvolutionInfo i = new EvolutionInfo();
					if (EnumEvolutionStone.isEvolutionStone(sSplit[0])) {
						i.mode = InfoMode.stone;
						i.evolutionStone = EnumEvolutionStone.getEvolutionStone(sSplit[0]);
					} else if (EnumEvolutionRock.isEvolutionRock(sSplit[0])) {
						i.mode = InfoMode.evolutionRock;
						i.evolutionRock = EnumEvolutionRock.getEvolutionRock(sSplit[0]);
					} else if (sSplit[0].equalsIgnoreCase("Friendship")) {
						i.mode = InfoMode.friendship;
						if (sSplit.length > 1)
							i.extraParam = sSplit[1];
					} else if (sSplit[0].equalsIgnoreCase("Trade")) {
						i.mode = InfoMode.trade;
						if (sSplit.length > 1)
							i.extraParam = sSplit[1];
						if (sSplit.length > 2)
							i.extraParam2 = sSplit[2];
					} else {
						i.mode = InfoMode.biome;
						i.extraParam = sSplit[0];
					}
					i.pokemonName = sSplit[sSplit.length - 1];
					list.add(i);
				}
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error in evolution conditions for " + id);
		}
		return list;
	}

	public static String getDescription(String name) {
		try {
			Connection con = DatabaseHelper.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select POKEDEXDESCRIPTION from PIXELMON where PIXELMONFULLNAME='" + name + "'");
			if (rs.next()) {
				String description = rs.getString("POKEDEXDESCRIPTION");
				rs.close();
				return description;
			}
		} catch (Exception e) {
		}
		return null;
	}
}