package pixelmon.database;

import pixelmon.enums.EnumType;

public class BaseStats {
	public int SpDef;
	public int SpAtt;
	public int Speed;
	public int Defence;
	public int Attack;
	public int HP;
	public int EvolveLevel = -1;
	public EnumType Type1;
	public EnumType Type2 = EnumType.Mystery;
	public float Height = 1f;
	public int CatchRate;
	public int MalePercent;
	public boolean CanFly;
	public String EvolveInto;
	public int BaseExp;
	public ExperienceGroup ExperienceGroup;
	public int nationalPokedexNumber;
	public int SpawnLevel;
	public int SpawnLevelRange;
	public boolean IsRideable;
	public float Width;
	public float Length;

	public String getNationalPokedexNumberString() {
		if (nationalPokedexNumber < 10)
			return "00" + nationalPokedexNumber +".png";
		else if (nationalPokedexNumber < 100)
			return "0" + nationalPokedexNumber +".png";
		else
			return "" + nationalPokedexNumber +".png";
	}
}
