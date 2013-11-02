package pixelmon.enums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import pixelmon.battles.attacks.Effectiveness;
import pixelmon.database.DatabaseHelper;

public enum EnumType {
	Normal(0, "Normal", 0xDDDDDD, 2, 6), 
	Fire(1, "Fire", 0xFF8800, 2, 30), 
	Water(2, "Water", 0x5765FF, 2, 54), 
	Electric(3, "Electric", 0xF5F24C, 2, 78), 
	Grass(4, "Grass", 0x00D420, 2, 102),
	Ice(5, "Ice", 0xB0FFFF, 43, 6), 
	Fighting(6, "Fighting", 0xBA0038, 43, 30), 
	Poison(7, "Poison", 0xC905F0, 43, 54), 
	Ground(8, "Ground", 0x996640, 43, 78), 
	Flying(9, "Flying", 0xCCD5FF, 43, 102), 
	Psychic(10, "Psychic", 0xFF54EB, 84, 6), 
	Bug(11, "Bug", 0xA8E053, 84, 30), 
	Rock(12, "Rock", 0xA37755, 84, 54), 
	Ghost(13, "Ghost", 0x6C2B8A, 84, 78), 
	Dragon(14, "Dragon", 0x2B30CC, 84, 102), 
	Dark(15, "Dark", 0x404040, 123, 6), 
	Steel(16, "Steel", 0xBCBCC2, 123, 30),
	
	//MissingNo.
	Mystery(17, "???", 0x309680, 0, 0);

	private int index;
	private String name;
	private int color;
	public float textureX;
	public float textureY;

	private EnumType(int i, String s, int c, float texX, float texY) {
		index = i;
		name = s;
		color = c;
		textureX = texX;
		textureY = texY;
	}

	public int getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public static EnumType parseTypeFromDBID(int id) {
		Connection con = DatabaseHelper.getConnection();
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from TYPES where TYPEID=" + id);
			while (rs.next()) {
				return EnumType.parseType(rs.getString("NAME"));
			}
		} catch (Exception e) {

		}
		return EnumType.Mystery;
	}

	public static EnumType parseType(int i) {
		ArrayList<EnumType> list = getAllTypes();
		Iterator<EnumType> it = list.iterator();
		while (it.hasNext()) {
			EnumType t = it.next();
			if (t.index == i)
				return t;
		}
		return Mystery;
	}

	public static EnumType parseType(String s) {
		ArrayList<EnumType> list = getAllTypes();
		Iterator<EnumType> it = list.iterator();
		while (it.hasNext()) {
			EnumType t = it.next();
			if (t.name.equalsIgnoreCase(s))
				return t;
		}
		return Mystery;
	}

	public static ArrayList<EnumType> getAllTypes() {
		ArrayList<EnumType> list = new ArrayList<EnumType>();
		EnumType[] t = values();
		for (int i = 0; i < t.length; i++)
			list.add(t[i]);
		return list;
	}

	public static float getTotalEffectiveness(ArrayList<EnumType> targetTypeList, EnumType attackType) {
		float f = getEffectiveness(attackType, targetTypeList.get(0));
		if (targetTypeList.size() == 1) {
			return f;
		} else {
			float f1 = getEffectiveness(attackType, targetTypeList.get(1));
			return f * f1;
		}
	}

	public static float getEffectiveness(EnumType t, EnumType t1) {
		if (t == Mystery || t1 == Mystery)
			return Effectiveness.None.value;
		float e = 1;
		if (t == Normal) {
			if (t1 == Rock) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ghost) {
				e = Effectiveness.None.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Fire) {
			if (t1 == Fire) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Water) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ice) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Bug) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Dragon) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Super.value;
			}

		}
		if (t == Water) {
			if (t1 == Fire) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Water) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ground) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Dragon) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Electric) {
			if (t1 == Water) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Electric) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ground) {
				e = Effectiveness.None.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Dragon) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Grass) {
			if (t1 == Fire) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Water) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Poison) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ground) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Bug) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Dragon) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Ice) {
			if (t1 == Fire) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Water) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ice) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ground) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Dragon) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Fighting) {
			if (t1 == Normal) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ice) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Poison) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Psychic) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Bug) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ghost) {
				e = Effectiveness.None.value;
			}
			if (t1 == Dark) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Super.value;
			}

		}
		if (t == Poison) {
			if (t1 == Grass) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Poison) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ground) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ghost) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.None.value;
			}

		}
		if (t == Ground) {
			if (t1 == Fire) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Electric) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Poison) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.None.value;
			}
			if (t1 == Bug) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Super.value;
			}

		}
		if (t == Flying) {
			if (t1 == Electric) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Fighting) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Bug) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Psychic) {
			if (t1 == Fighting) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Poison) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Psychic) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Dark) {
				e = Effectiveness.None.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Bug) {
			if (t1 == Fire) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Grass) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Fighting) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Poison) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Psychic) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ghost) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Dark) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Rock) {
			if (t1 == Fire) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ice) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Fighting) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ground) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Flying) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Bug) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Ghost) {
			if (t1 == Normal) {
				e = Effectiveness.None.value;
			}
			if (t1 == Psychic) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ghost) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Dark) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Dragon) {
			if (t1 == Dragon) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Dark) {
			if (t1 == Fighting) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Psychic) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Ghost) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Dark) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}
		if (t == Steel) {
			if (t1 == Fire) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Water) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Electric) {
				e = Effectiveness.Not.value;
			}
			if (t1 == Ice) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Rock) {
				e = Effectiveness.Super.value;
			}
			if (t1 == Steel) {
				e = Effectiveness.Not.value;
			}

		}

		return e;
	}

	public int getIndex() {
		return index;
	}

}
