package pixelmon.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumPokemon;

public class DatabaseTrainers {
	 public static TrainerInfo GetTrainerInfo(String trainerName) {
         Connection conn = null;
         try {
                 Class.forName("org.sqlite.JDBC");
                 conn = DatabaseHelper.getConnection();
                 Statement stat = conn.createStatement();
                 ResultSet rs = stat.executeQuery("select * from Trainers where TrainerType='" + trainerName + "'");

                 TrainerInfo info = new TrainerInfo();
                 Random rand = new Random();
                 while (rs.next()) {
                         String nameListString = rs.getString("NameList");
                         String[] nameListSplits = nameListString.split(";");
                         info.name = nameListSplits[rand.nextInt(nameListSplits.length)];
                         String pokemonListString = rs.getString("UsablePokemon");
                         String[] pokemonListSplits = pokemonListString.split(";");
                         ArrayList<String> newList = new ArrayList<String>();
                         for (String s : pokemonListSplits)
                                 if (EnumPokemon.hasPokemon(s))
                                         newList.add(s);

                         int numPokemon = rand.nextInt(6) + 1;
                         for (int i = 0; i < numPokemon; i++) {
                                 info.partypokemon.add(newList.get(rand.nextInt(newList.size())));
                         }
                         int baseLevel = rs.getInt("BaseLevel");
                         int topLevel = rs.getInt("TopLevel");
                         info.level = baseLevel + rand.nextInt(topLevel + 1 - baseLevel);
                         String greetingListString = rs.getString("Greetings");
                         String[] greetingListSplits = greetingListString.split(";");
                         int ind = rand.nextInt(greetingListSplits.length);
                         info.greeting = greetingListSplits[ind];
                         String winningListString = rs.getString("VictoryMessages");
                         String defeatListString = rs.getString("DefeatMessages");
                         if (winningListString == null)
                                 info.winMessage = "NO WINNING MESSAGE!";
                         else {
                                 String[] winningListSplits = winningListString.split(";");
                                 if (winningListSplits.length < ind - 1)
                                         info.winMessage = "NO WINNING MESSAGE!";
                                 else
                                         info.winMessage = winningListSplits[ind];
                         }
                         if (defeatListString == null)
                                 info.loseMessage = "NO DEFEAT MESSAGE!";
                         else {
                                 String[] defeatListSplits = defeatListString.split(";");
                                 if (defeatListSplits.length < ind - 1)
                                         info.loseMessage = "NO DEFEAT MESSAGE!";
                                 else
                                         info.loseMessage = defeatListSplits[ind];
                         }
                         info.rarity = rs.getInt("Rarity");
                         String modelStrings = rs.getString("Models");
                         String[] modelSplits = modelStrings.split(";");
                         info.model = modelSplits[rand.nextInt(modelSplits.length)];
                 }
                 conn.close();
                 return info;
         } catch (Exception e) {
                 if (conn != null)
                         try {
                                 conn.close();
                         } catch (SQLException e1) {
                                 e1.printStackTrace();
                         }
         }
         return null;
 }

	public static BiomeGenBase[] GetSpawnBiomes(String trainerName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Trainers where TrainerType='" + trainerName + "'");
			while (rs.next()) {
				BiomeGenBase[] biomes;
				String biomeString = rs.getString("SpawnBiome");
				if (rs.wasNull())
					return null;
				String[] biomeList = biomeString.split(";");
				biomes = new BiomeGenBase[biomeList.length];
				int i = 0;
				for (String biomeName : biomeList) {
					EnumBiomes e = EnumBiomes.parseBiome(biomeName);
					biomes[i] = e.getBiome();
					i++;
				}
				return biomes;
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return null;
	}

	public static int getRarity(String name) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Trainers where TrainerType='" + name + "'");
			int rarity = 0;
			while (rs.next()) {
				rarity = rs.getInt("Rarity");
			}
			conn.close();
			return rarity;
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return 0;
	}
}
