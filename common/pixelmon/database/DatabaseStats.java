package pixelmon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pixelmon.database.BaseStats.Aggression;
import pixelmon.database.BaseStats.SwimmingParameters;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumType;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EnumCreatureType;

public class DatabaseStats {

	public static BaseStats GetBaseStats(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");

			BaseStats stats = new BaseStats();
			String error = "";
			boolean hasError = false;
			while (rs.next()) 
			{
				stats.HP = rs.getInt("BaseHP");
				if (rs.wasNull())
				{
					error+="[HP]"; 
					hasError = true;
				}
				stats.Attack = rs.getInt("BaseAttack");
				if (rs.wasNull()) 
				{
					error+="[ATTACK]"; 
					hasError = true;
				}
				stats.Defence = rs.getInt("BaseDefence");
				if (rs.wasNull())
				{
					error+="[DEFENSE]"; 
					hasError = true;
				}
				stats.Speed = rs.getInt("BaseSpeed");
				if (rs.wasNull())
				{
					error+="[SPEED]"; 
					hasError = true;
				}
				stats.SpAtt = rs.getInt("BaseSpAttack");
				if (rs.wasNull()) 
				{
					error+="[SPATTACK]"; 
					hasError = true;
				}
				stats.SpDef = rs.getInt("BaseSpDefence");
				if (rs.wasNull()) 
				{
					error+="[SPDEFENSE]";
					hasError = true;
				}
				if (hasError)
				{
					System.out.println("Error in BaseStats "+ "[" + error + "]" + " For Pokemon : " + pixelmonName);
				}
				stats.CatchRate = rs.getInt("CatchRate");
				if (rs.wasNull()) 
					System.out.println("Error in CatchRate"+ " For Pokemon : " + pixelmonName);
				
				stats.MalePercent = rs.getInt("MalePercent");
				if (rs.wasNull()) 
					System.out.println("Error in MalePercent"+ " For Pokemon : " + pixelmonName);
				
				stats.EvolveLevel = rs.getInt("EvolveLevel");
				if (rs.wasNull()) 
					System.out.println("Error in EvolveLevel"+ " For Pokemon : " + pixelmonName);
				stats.EvolveInto = rs.getString("EvolveInto");
				stats.CanFly = rs.getInt("CanFly") == 1;
				if (rs.wasNull()) 
					System.out.println("Error in CanFly"+ " For Pokemon : " + pixelmonName);
				stats.Height = rs.getFloat("Height");
				if (rs.wasNull()) 
					System.out.println("Error in Height"+ " For Pokemon : " + pixelmonName);
				stats.Width = rs.getFloat("Width");
				if (rs.wasNull()) 
					System.out.println("Error in Width"+ " For Pokemon : " + pixelmonName);
				stats.Length = rs.getFloat("Length");
				if (rs.wasNull()) 
					System.out.println("Error in Length"+ " For Pokemon : " + pixelmonName);
				stats.Type1 = EnumType.parseType(rs.getString("Type1"));
				if (rs.wasNull()) 
					System.out.println("Error in Type"+ " For Pokemon : " + pixelmonName);
				stats.BaseExp = rs.getInt("BaseExp");
				if (rs.wasNull()) 
					System.out.println("Error in BaseExp"+ " For Pokemon : " + pixelmonName);
				stats.ExperienceGroup = ExperienceGroup.getExperienceGroup(rs.getString("ExperienceGroup"));
				if (rs.wasNull()|| stats.ExperienceGroup == null) 
					System.out.println("Error in ExperienceGroup"+ " For Pokemon : " + pixelmonName);
				stats.nationalPokedexNumber = rs.getInt("NationalPokedexNumber");
				if (rs.wasNull()) 
					System.out.println("Error in NationalPokedexNumber"+ " For Pokemon : " + pixelmonName);
				stats.SpawnLevel = rs.getInt("SpawnLevel");
				if (rs.wasNull()) 
					System.out.println("Error in SpawnLevel"+ " For Pokemon : " + pixelmonName);
				stats.SpawnLevelRange= rs.getInt("SpawnLevelRange");
				if (rs.wasNull()) 
					System.out.println("Error in SpawnLevelRange"+ " For Pokemon : " + pixelmonName);
				stats.IsRideable = rs.getBoolean("IsRideable");
				stats.giScale = rs.getFloat("GIScale");
				rs.getString("Type2");
				if (!rs.wasNull())
					stats.Type2 = EnumType.parseType(rs.getString("Type2"));
				stats.aggression = stats.new Aggression(rs.getString("Aggression"), pixelmonName);
				if (rs.wasNull()) 
					System.out.println("Error in Aggression"+ " For Pokemon : " + pixelmonName);
				String type = rs.getString("CreatureType");
				if (type.equalsIgnoreCase("Land")) stats.creatureType = EnumCreatureType.creature;
				else stats.creatureType = EnumCreatureType.waterCreature;
				stats.droppedItem = rs.getString("DroppedItem");
				stats.spawnBlock = rs.getString("SpawnBlock");
				
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
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
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
				if (type.equalsIgnoreCase("Land")) return EnumCreatureType.creature;
				else return EnumCreatureType.waterCreature;
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
				for (String s:strList){
					String[] sSplit = s.split(":");
					EvolutionInfo i = new EvolutionInfo();
					i.evolutionStone = EnumEvolutionStone.getEvolutionStone(sSplit[0]);
					i.pokemonName = sSplit[1];
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
				if (rs.wasNull()){
					biomes = BiomeGenBase.biomeList;
					ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
					for (int i=0; i < biomes.length; i++)
						if (biomes[i]!=null)biomeList.add(biomes[i]);
					
					BiomeGenBase[] biomes2 = new BiomeGenBase[biomeList.size()];
					int j=0;
					for(BiomeGenBase b: biomeList)
						biomes2[j++]=b;
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
}
