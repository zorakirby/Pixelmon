package pixelmon.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.Entity6CanBattle;
import pixelmon.entities.pixelmon.stats.Moveset;

public class DatabaseMoves {
	public static Moveset GetInitialMoves(Entity6CanBattle pixelmon, int level) {
		Moveset attacks = new Moveset();
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from PIXELMONLEVELSKILLS where PIXELMONID='" + pixelmon.baseStats.id + "'");
			ArrayList<Move> moves = new ArrayList<Move>();
			while (rs.next()) {
				int learnLevel = rs.getInt("LEARNLEVEL");
				int moveID = rs.getInt("MOVEID");
				attacks.add(getAttack(moveID));
			}
			rs.close();

			while (attacks.size() > 4) {
				int ind = (int) RandomHelper.getRandomNumberBetween(0, attacks.size() - 1);
				attacks.remove(ind);
			}

			if (attacks.size() == 0)
				attacks.add(getAttack("Tackle"));

		} catch (Exception e) {
			System.out.println(pixelmon.getName() + " has corrupted moves at level " + level);
		}

		return attacks;
	}

	public static boolean LearnsAttackAtLevel(int id, int level) {
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from PIXELMONLEVELSKILLS where PIXELMONID='" + id + "' AND LEARNLEVEL='" + level + "'");
			if (rs.next()) {
				rs.close();
				return true;
			}
			rs.close();
		} catch (Exception e) {
		}
		return false;
	}

	public static ArrayList<Attack> getAttacksAtLevel(int id, int level) {
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from PIXELMONLEVELSKILLS where PIXELMONID='" + id + "' AND LEARNLEVEL='" + level + "'");
			while (rs.next()) {
				int moveID = rs.getInt("MOVEID");
				attacks.add(getAttack(moveID));
			}
			rs.close();
		} catch (Exception e) {
		}
		return attacks;
	}

	public static Attack getAttack(String moveName) {

		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from MOVES where NAME='" + moveName + "'");
			while (rs.next()) {
				return new Attack(rs.getInt("MOVEID"), moveName, rs);
			}
			conn.close();
		} catch (Exception e) {
			if (PixelmonConfig.printErrors)
				System.out.println(e.getMessage());
		}
		return null;
	}

	public static Attack getAttack(int moveIndex) {

		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from MOVES where MOVEID='" + moveIndex + "'");
			while (rs.next()) {
				return new Attack(moveIndex, rs.getString("NAME"), rs);
			}
			conn.close();
		} catch (Exception e) {
			if (PixelmonConfig.printErrors)
				System.out.println(e.getMessage());
		}
		return null;
	}

	public static boolean CanLearnAttack(int id, String attackName) {
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select MOVEID from MOVES where NAME='" + attackName + "'");
			if (rs.next()) {
				int moveID = rs.getInt("MOVEID");
				rs.close();
				ResultSet levelSkillsrs = stat.executeQuery("select * from PIXELMONLEVELSKILLS where PIXELMONID='" + id + "' AND MOVEID='" + moveID + "'");
				if (levelSkillsrs.next())
					return true;
				levelSkillsrs.close();
				ResultSet tmSkillsrs = stat.executeQuery("select * from PIXELMONTMHMSKILLS where PIXELMONID='" + id + "' AND MOVEID='" + moveID + "'");
				if (tmSkillsrs.next())
					return true;
			} else
				rs.close();
		} catch (Exception e) {

		}
		return false;
	}

	public static AttackCategory[] getAttackCategories() {
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from MOVECATEGORIES");
			ArrayList<AttackCategory> list = new ArrayList<AttackCategory>();
			while (rs.next()) {
				list.add(new AttackCategory(rs.getInt("MOVECATEGORYID"), rs.getString("NAME")));
			}
			rs.close();
			AttackCategory[] categories = new AttackCategory[list.size()];
			for (int i = 0; i < list.size(); i++)
				categories[i] = list.get(i);
			return categories;
		} catch (Exception e) {
			if (PixelmonConfig.printErrors)
				System.out.println(e.getMessage());
		}
		return null;
	}
}
