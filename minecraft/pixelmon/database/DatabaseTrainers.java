package pixelmon.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.RandomHelper;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumPokemon;

public class DatabaseTrainers {
	public static TrainerInfo GetTrainerInfo(String trainerName) {
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * from TRAINER where TRAINERTYPEID=SELECT TRAINERTYPEID FROM TRAINERTYPES WHERE TYPENAME='" + trainerName
					+ "'");

			TrainerInfo info = new TrainerInfo();
			Random rand = new Random();
			if (rs.next()) {
				info.id = rs.getInt("TRAINERTYPEID");

				getNameAndModelForID(stat, info);
				getPartyPokemon(stat, info);
				getTrainerTypeInfo(stat, info);
				getMessages(stat, info);
			}
			return info;
		} catch (Exception e) {
			System.out.println("Error in database for trainer " + trainerName);
		}
		return null;
	}

	private static void getMessages(Statement stat, TrainerInfo info) throws SQLException {
		ResultSet rs = stat.executeQuery("SELECT MESSAGE from TRAINERMESSAGES where TRAINERTYPEID=" + info.id + " AND MESSAGETYPE='Greeting'");
		ArrayList<String> greetings = new ArrayList<String>();
		if (rs.next()) {
			greetings.add(rs.getString("MESSAGE"));
		}
		rs = stat.executeQuery("SELECT MESSAGE from TRAINERMESSAGES where TRAINERTYPEID=" + info.id + " AND MESSAGETYPE='Victory'");
		ArrayList<String> victoryMessages = new ArrayList<String>();
		if (rs.next()) {
			victoryMessages.add(rs.getString("MESSAGE"));
		}
		rs = stat.executeQuery("SELECT MESSAGE from TRAINERMESSAGES where TRAINERTYPEID=" + info.id + " AND MESSAGETYPE='Defeat'");
		ArrayList<String> defeatMessages = new ArrayList<String>();
		if (rs.next()) {
			defeatMessages.add(rs.getString("MESSAGE"));
		}

		int ind = RandomHelper.getRandomNumberBetween(0, greetings.size() - 1);
		info.greeting = greetings.get(ind);
		ind = RandomHelper.getRandomNumberBetween(0, victoryMessages.size() - 1);
		info.winMessage = victoryMessages.get(ind);
		ind = RandomHelper.getRandomNumberBetween(0, defeatMessages.size() - 1);
		info.loseMessage = defeatMessages.get(ind);

	}

	private static void getTrainerTypeInfo(Statement stat, TrainerInfo info) throws SQLException {
		ResultSet rs = stat.executeQuery("SELECT * from TRAINERTYPES where TRAINERTYPEID=" + info.id);
		if (rs.next()) {
			int baseLevel = rs.getInt("MINPARTYLEVEL");
			int topLevel = rs.getInt("MAXPARTYLEVEL");
			info.rarity = rs.getInt("RARITY");
			info.level = RandomHelper.getRandomNumberBetween(baseLevel, topLevel + 1);
		}
	}

	private static void getPartyPokemon(Statement stat, TrainerInfo info) throws SQLException {
		ResultSet rs = stat.executeQuery("SELECT * from TRAINERPIXELMONPOOL where TRAINERTYPEID=" + info.id);
		ArrayList<EnumPokemon> pokemonList = new ArrayList<EnumPokemon>();
		while (rs.next()) {
			pokemonList.add(Entity3HasStats.getBaseStatsFromDBID(rs.getInt("PIXELMONID")).pokemon);
		}
		int numPokemon = RandomHelper.getRandomNumberBetween(1, 6);
		for (int i = 0; i < numPokemon; i++) {
			info.partypokemon.add(pokemonList.get(RandomHelper.getRandomNumberBetween(0, pokemonList.size() - 1)));
		}

	}

	private static void getNameAndModelForID(Statement stat, TrainerInfo info) throws SQLException {
		ResultSet rs = stat.executeQuery("SELECT * from TRAINER where TRAINERTYPEID=" + info.id);
		String[] result;
		ArrayList<String[]> nameList = new ArrayList<String[]>();
		while (rs.next()) {
			nameList.add(new String[] { rs.getString("TRAINERNAME"), rs.getString("TRAINERMODEL") });
		}
		result = nameList.get(RandomHelper.getRandomNumberBetween(0, nameList.size() - 1));
		info.name = result[0];
		info.model = result[1];
	}

	public static BiomeGenBase[] GetSpawnBiomes(String trainerName) {
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select BIOMEID from TRAINERBIOMES where TRAINERTYPEID= "
					+ "SELECT TRAINERTYPEID FROM TRAINERTYPES WHERE TYPENAME='" + trainerName + "'");
			ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
			while (rs.next()) {
				biomes.add(DatabaseStats.biomeMasterList[rs.getInt("BIOMEID")]);
			}
			BiomeGenBase[] biomeArray = new BiomeGenBase[biomes.size()];
			for (int i = 0; i < biomes.size(); i++) {
				biomeArray[i] = biomes.get(i);
			}
			return biomeArray;
		} catch (Exception e) {
			System.out.println("Error in biomes for trainer " + trainerName);
		}
		return null;
	}

	public static int getRarity(String name) {
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select RARITY from TRAINERTYPES WHERE TYPENAME='" + name + "'");
			int rarity = 0;
			while (rs.next()) {
				return rs.getInt("RARITY");
			}
		} catch (Exception e) {
			System.out.println("Error in rarity for trainer " + name);
		}
		return 0;
	}
}
