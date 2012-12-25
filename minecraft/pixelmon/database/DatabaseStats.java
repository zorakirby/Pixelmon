package pixelmon.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.stats.Aggression;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.entities.pixelmon.stats.EVsStore;
import pixelmon.entities.pixelmon.stats.SwimmingParameters;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumType;

public class DatabaseStats {

	public static BaseStats GetBaseStats(String name) {
		Connection conn = null;
		BaseStats store = new BaseStats(name);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + name + "'");

			String error = "";
			boolean hasError = false;
			while (rs.next()) {
				store.hp = rs.getInt("BaseHP");
				if (rs.wasNull()) {
					error += "[HP]";
					hasError = true;
				}
				store.attack = rs.getInt("BaseAttack");
				if (rs.wasNull()) {
					error += "[ATTACK]";
					hasError = true;
				}
				store.defence = rs.getInt("BaseDefence");
				if (rs.wasNull()) {
					error += "[DEFENSE]";
					hasError = true;
				}
				store.speed = rs.getInt("BaseSpeed");
				if (rs.wasNull()) {
					error += "[SPEED]";
					hasError = true;
				}
				store.spAtt = rs.getInt("BaseSpAttack");
				if (rs.wasNull()) {
					error += "[SPATTACK]";
					hasError = true;
				}
				store.spDef = rs.getInt("BaseSpDefence");
				if (rs.wasNull()) {
					error += "[SPDEFENSE]";
					hasError = true;
				}
				if (hasError) {
					System.out.println("Error in BaseStats " + "[" + error + "]" + " For Pokemon : " + name);
				}
				store.catchRate = rs.getInt("CatchRate");
				if (rs.wasNull())
					System.out.println("Error in CatchRate" + " For Pokemon : " + name);

				store.malePercent = rs.getInt("MalePercent");
				if (rs.wasNull())
					System.out.println("Error in MalePercent" + " For Pokemon : " + name);

				store.evolveLevel = rs.getInt("EvolveLevel");
				if (rs.wasNull())
					System.out.println("Error in EvolveLevel" + " For Pokemon : " + name);
				store.evolveInto = EnumPokemon.get(rs.getString("EvolveInto"));
				store.canFly = rs.getInt("CanFly") == 1;
				if (rs.wasNull())
					System.out.println("Error in CanFly" + " For Pokemon : " + name);
				store.height = rs.getFloat("Height");
				if (rs.wasNull())
					System.out.println("Error in Height" + " For Pokemon : " + name);
				store.width = rs.getFloat("Width");
				if (rs.wasNull())
					System.out.println("Error in Width" + " For Pokemon : " + name);
				store.length = rs.getFloat("Length");
				if (rs.wasNull())
					System.out.println("Error in Length" + " For Pokemon : " + name);
				store.type1 = EnumType.parseType(rs.getString("Type1"));
				if (rs.wasNull())
					System.out.println("Error in Type" + " For Pokemon : " + name);
				store.baseExp = rs.getInt("BaseExp");
				if (rs.wasNull())
					System.out.println("Error in BaseExp" + " For Pokemon : " + name);
				store.experienceGroup = ExperienceGroup.getExperienceGroup(rs.getString("ExperienceGroup"));
				if (rs.wasNull() || store.experienceGroup == null)
					System.out.println("Error in ExperienceGroup" + " For Pokemon : " + name);
				store.nationalPokedexNumber = rs.getInt("NationalPokedexNumber");
				if (rs.wasNull())
					System.out.println("Error in NationalPokedexNumber" + " For Pokemon : " + name);
				store.spawnLevel = rs.getInt("SpawnLevel");
				if (rs.wasNull())
					System.out.println("Error in SpawnLevel" + " For Pokemon : " + name);
				store.spawnLevelRange = rs.getInt("SpawnLevelRange");
				if (rs.wasNull())
					System.out.println("Error in SpawnLevelRange" + " For Pokemon : " + name);
				store.isRideable = rs.getBoolean("IsRideable");
				store.giScale = rs.getFloat("GIScale");
				rs.getString("Type2");
				if (!rs.wasNull())
					store.type2 = EnumType.parseType(rs.getString("Type2"));
				store.aggression = new Aggression(rs.getString("Aggression"), name);
				if (rs.wasNull())
					System.out.println("Error in Aggression" + " For Pokemon : " + name);
				String type = rs.getString("CreatureType");
				if (type.equalsIgnoreCase("Land"))
					store.creatureType = EnumCreatureType.creature;
				else
					store.creatureType = EnumCreatureType.waterCreature;
				store.droppedItem = rs.getString("DroppedItem");
				store.spawnConditions = SpawnConditions.ParseSpawnConditions(rs.getString("SpawnConditions"));
				store.baseFriendship = rs.getInt("BaseFriendship");

				String sp = rs.getString("SwimmingParameters");
				if (!rs.wasNull())
					store.swimmingParameters = new SwimmingParameters(sp, name);

				store.evGain = new EVsStore();
				store.evGain.HP = rs.getInt("EvGainHP");
				store.evGain.Attack = rs.getInt("EvGainAtk");
				store.evGain.Defence = rs.getInt("EvGainDef");
				store.evGain.SpecialAttack = rs.getInt("EvGainSpAtk");
				store.evGain.SpecialDefence = rs.getInt("EvGainSpDef");
				store.evGain.Speed = rs.getInt("EvGainSpeed");
				float rx = rs.getFloat("RidingOffsetX");
				if (!rs.wasNull())
					store.ridingOffsetX = rx;
				float ry = rs.getFloat("RidingOffsetY");
				if (!rs.wasNull())
					store.ridingOffsetY = ry;
				float rz = rs.getFloat("RidingOffsetZ");
				if (!rs.wasNull())
					store.ridingOffsetZ = rz;
			}
			conn.close();
			return store;
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

	public static String getDescription(String name) {
		return (String) getStat(name, "Description");
	}
}