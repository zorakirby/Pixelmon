package pixelmon.entities.pixelmon.stats;

import net.minecraft.src.EnumCreatureType;
import pixelmon.database.DatabaseStats;
import pixelmon.database.ExperienceGroup;
import pixelmon.database.SpawnConditions;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.enums.EnumType;

public class BaseStats {
	private Entity3HasStats entity;

	public BaseStats(Entity3HasStats entity) {
		this.entity = entity;
	}

	public int getSpDef() {
		return getIntStat("BaseSpDefence", 0);
	}

	public int getSpAtt() {
		return getIntStat("BaseSpAttack", 0);
	}

	public int getSpeed() {
		return getIntStat("BaseSpeed", 0);
	}

	public int getDefence() {
		return getIntStat("BaseDefence", 0);
	}

	public int getAttack() {
		return getIntStat("BaseAttack", 0);
	}

	public int getHP() {
		return getIntStat("BaseHP", 0);
	}

	public int getEvolveLevel() {
		return getIntStat("EvolveLevel", -1);
	}

	public EnumType getType1() {
		String s = getStringStat("Type1", "");
		return EnumType.parseType(s);
	}

	public EnumType getType2() {
		String s = getStringStat("Type2", null);
		if (s != null)
			return EnumType.parseType(s);
		else
			return EnumType.Mystery;
	}

	public float height;

	public float width;

	public float length;

	public int getCatchRate() {
		return getIntStat("CatchRate", 0);
	}

	public int getMalePercent() {
		return getIntStat("MalePercent", 0);
	}

	public boolean canFly;

	public String getEvolveInto() {
		return getStringStat("EvolveInto", null);
	}

	public int getBaseExp() {
		return getIntStat("BaseExp", 0);
	}

	public ExperienceGroup getExperienceGroup() {
		String s = getStringStat("ExperienceGroup", null);
		return ExperienceGroup.getExperienceGroup(s);
	}

	public int getNationalPokedexNumber() {
		return getIntStat("NationalPokedexNumber", -1);
	}

	public int getSpawnLevel() {
		return getIntStat("SpawnLevel", -1);
	}

	public int getSpawnLevelRange() {
		return getIntStat("SpawnLevelRange", 0);
	}

	public boolean getIsRideable() {
		return getBooleanStat("IsRideable", false);
	}

	public float giScale;

	public Aggression getAggression() {
		String aggString = getStringStat("Aggression", null);
		return new Aggression(aggString, entity.getName());
	}

	public EnumCreatureType creatureType;

	public String getDroppedItem() {
		return getStringStat("DroppedItem", "");
	}

	public SwimmingParameters swimmingParameters;
	public SpawnConditions[] spawnConditions;

	public int[] getEvGain() {
		int[] evs = new int[6];
		evs[0] = getIntStat("EvGainHP", 0);
		evs[1] = getIntStat("EvGainAtk", 0);
		evs[2] = getIntStat("EvGainDef", 0);
		evs[3] = getIntStat("EvGainSpAtk", 0);
		evs[4] = getIntStat("EvGainSpDef", 0);
		evs[5] = getIntStat("EvGainSpeed", 0);
		return evs;
	}

	public int getBaseFriendship() {
		return getIntStat("BaseFriendship", 0);
	}

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
		int nationalPokedexNumber = getNationalPokedexNumber();
		if (nationalPokedexNumber < 10)
			return "00" + nationalPokedexNumber + ".png";
		else if (nationalPokedexNumber < 100)
			return "0" + nationalPokedexNumber + ".png";
		else
			return "" + nationalPokedexNumber + ".png";
	}

	private String getStringStat(String string, String alternate) {
		Object ret = DatabaseStats.getStat(entity.getName(), string);
		if (ret != null)
			return (String) ret;
		else
			return alternate;
	}

	private int getIntStat(String string, int i) {
		Object ret = DatabaseStats.getStat(entity.getName(), string);
		if (ret != null)
			return (Integer) ret;
		else
			return i;

	}

	private float getFloatStat(String string, float f) {
		Object ret = DatabaseStats.getStat(entity.getName(), string);
		if (ret != null)
			return (Float) ret;
		else
			return f;

	}

	private boolean getBooleanStat(String string, boolean v) {
		Object ret = DatabaseStats.getStat(entity.getName(), string);
		if (ret != null)
			return Boolean.valueOf(ret.toString()).booleanValue();
		else
			return v;

	}

}
