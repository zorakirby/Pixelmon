package pixelmon.entities.pixelmon.stats;

import net.minecraft.entity.EnumCreatureType;
import pixelmon.database.ExperienceGroup;
import pixelmon.database.SpawnConditions;
import pixelmon.database.SpawnLocation;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumType;

public class BaseStats {
	public String pixelmonName;

	public BaseStats(String name) {
		this.pixelmonName = name;
	}

	public int spDef, spAtt, speed, defence, attack, hp;
	public int evolveLevel, catchRate, malePercent, nationalPokedexNumber;
	public int spawnLevel, spawnLevelRange;
	public int baseExp, baseFriendship;

	public EnumType type1;

	public EnumType type2;

	public float height, width, length;
	public float giScale;

	public boolean canFly, isRideable;

	public EnumPokemon evolveInto;

	public ExperienceGroup experienceGroup;

	public Aggression aggression;

	public String droppedItem;

	public SwimmingParameters swimmingParameters;
	public SpawnConditions[] spawnConditions;

	public EVsStore evGain;
	public boolean canSurf;
	public boolean canSurfSet;
	public float ridingOffsetX = 0, ridingOffsetY = 0, ridingOffsetZ = 0;
	public int maxGroupSize;
	public SpawnLocation[] spawnLocations;
	public int minGroupSize;
	public float hoverHeight = 0;
	public boolean doesHover = false;

	public int get(StatsType stat) {
		if (stat == StatsType.Attack)
			return attack;
		if (stat == StatsType.Defence)
			return defence;
		if (stat == StatsType.HP)
			return hp;
		if (stat == StatsType.SpecialAttack)
			return spAtt;
		if (stat == StatsType.SpecialDefence)
			return spDef;
		if (stat == StatsType.Speed)
			return speed;
		return -1;
	}
}
