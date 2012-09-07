package pixelmon.database;

import net.minecraft.src.EnumCreatureType;
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
	public float giScale;
	public Aggression aggression;
	public EnumCreatureType creatureType;
	public String droppedItem;
	public SwimmingParameters swimmingParameters;
	public SpawnConditions[] spawnConditions;

	public class SwimmingParameters {
		public int depthRangeStart;
		public int depthRangeEnd;
		public float swimSpeed;
		public float decayRate;
		public int refreshRate;

		public SwimmingParameters(String parameterString, String pokemonName) {
			String[] splits = parameterString.split(";");
			if (splits.length != 5)
				System.out.println("[ERROR] SwimmingParameter Error for " + pokemonName);
			try {
				depthRangeStart = Integer.parseInt(splits[0]);
				depthRangeEnd = Integer.parseInt(splits[1]);
				swimSpeed = Float.parseFloat(splits[2]);
				decayRate = Float.parseFloat(splits[3]);
				refreshRate = Integer.parseInt(splits[4]);
			} catch (Exception e) {
				System.out.println("[ERROR] SwimmingParameter Error2 for " + pokemonName);
			}
		}
	}

	public class Aggression {
		public int timid;
		public int passive;
		public int aggressive;

		public Aggression(String aggressionString, String pixelmonName) {
			if (aggressionString == null) {
				return;
			}
			String[] splits = aggressionString.split(";");
			if (splits.length != 3) {
				System.out.println("Error in Aggression" + " For Pokemon : " + pixelmonName);
				return;
			}
			timid = Integer.parseInt(splits[0]);
			passive = Integer.parseInt(splits[1]);
			aggressive = Integer.parseInt(splits[2]);
		}
	}

	public String getNationalPokedexNumberString() {
		if (nationalPokedexNumber < 10)
			return "00" + nationalPokedexNumber + ".png";
		else if (nationalPokedexNumber < 100)
			return "0" + nationalPokedexNumber + ".png";
		else
			return "" + nationalPokedexNumber + ".png";
	}
}
