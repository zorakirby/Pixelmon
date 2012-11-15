package pixelmon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.entities.pixelmon.stats.BaseStats.Aggression;
import pixelmon.entities.pixelmon.stats.BaseStats.SwimmingParameters;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumType;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EnumCreatureType;

public class DatabaseStats {

	public static BaseStats GetBaseStats(Entity3HasStats entity) {
		Connection conn = null;
		String pixelmonName = entity.getName();
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");

			BaseStats stats = new BaseStats(entity);
			String error = "";
			boolean hasError = false;
			while (rs.next()) {
				stats.height = rs.getFloat("Height");
				if (rs.wasNull())
					System.out.println("Error in Height" + " For Pokemon : " + pixelmonName);
				stats.width = rs.getFloat("Width");
				if (rs.wasNull())
					System.out.println("Error in Width" + " For Pokemon : " + pixelmonName);
				stats.length = rs.getFloat("Length");
				if (rs.wasNull())
					System.out.println("Error in Length" + " For Pokemon : " + pixelmonName);
				stats.canFly = rs.getInt("CanFly") == 1;
				String type = rs.getString("CreatureType");
				if (type.equalsIgnoreCase("Land"))
					stats.creatureType = EnumCreatureType.creature;
				else
					stats.creatureType = EnumCreatureType.waterCreature;
				
				stats.giScale = rs.getFloat("GIScale");
				stats.spawnConditions = SpawnConditions.ParseSpawnConditions(rs.getString("SpawnConditions"));
				String sp = rs.getString("SwimmingParameters");
				if (!rs.wasNull())
					stats.swimmingParameters = stats.new SwimmingParameters(sp, pixelmonName);
			}
			conn.close();
			return stats;
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

	public static int GetRarity(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select Rarity from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("Rarity");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static float getWeight(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select Weight from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getFloat("Weight");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static int GetMinGroupSize(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("MinGroupSize");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static int GetMaxGroupSize(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("MaxGroupSize");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static EnumCreatureType GetCreatureType(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				String type = rs.getString("CreatureType");
				if (type.equalsIgnoreCase("Land"))
					return EnumCreatureType.creature;
				else
					return EnumCreatureType.waterCreature;
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

	public static ArrayList<EvolutionInfo> getEvolveList(String pixelmonName) {
		ArrayList<EvolutionInfo> list = new ArrayList<EvolutionInfo>();
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select EvolveStone from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				String type = rs.getString("EvolveStone");
				String[] strList = type.split(";");
				for (String s : strList) {
					String[] sSplit = s.split(":");
					EvolutionInfo i = new EvolutionInfo();
					if (EnumEvolutionStone.isEvolutionStone(sSplit[0])) {
						i.mode = InfoMode.stone;
						i.evolutionStone = EnumEvolutionStone.getEvolutionStone(sSplit[0]);
					} else if (sSplit[0].equalsIgnoreCase("Friendship")) {
						i.mode = InfoMode.friendship;
						if (sSplit.length > 2)
							i.extraParam = sSplit[1];
					} else {
						i.mode = InfoMode.biome;
						i.extraParam = sSplit[0];
					}
					i.pokemonName = sSplit[sSplit.length - 1];
					list.add(i);
				}
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return list;
	}

	public static int getNationalPokedexNumber(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select NationalPokedexNumber from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("NationalPokedexNumber");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static BiomeGenBase[] GetSpawnBiomes(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				BiomeGenBase[] biomes;
				String biomeString = rs.getString("SpawnBiomes");
				if (rs.wasNull()) {
					biomes = BiomeGenBase.biomeList;
					ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
					for (int i = 0; i < biomes.length; i++)
						if (biomes[i] != null)
							biomeList.add(biomes[i]);

					BiomeGenBase[] biomes2 = new BiomeGenBase[biomeList.size()];
					int j = 0;
					for (BiomeGenBase b : biomeList)
						biomes2[j++] = b;
					return biomes2;
				}
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

	public static Object getStat(String pixelmonName, String string) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select " + string + " from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getObject(string);
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		System.out.println("Error in " + string + " For Pokemon : " + pixelmonName);
		return null;
	}
}