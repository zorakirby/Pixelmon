package pixelmon.entities.pixelmon.stats;

import pixelmon.enums.EnumNature;
import net.minecraft.nbt.NBTTagCompound;

public class Stats {
	public int HP;
	public int Attack;
	public int Defence;
	public int SpecialAttack;
	public int SpecialDefence;
	public int Speed;
	public IVStore IVs;
	public EVsStore EVs = new EVsStore();

	public void setLevelStats(EnumNature nature, BaseStats baseStats, int level) {
		HP = calculateHP(baseStats, level);
		Attack = calculateStat(StatsType.Attack, nature, baseStats, level);
		Defence = calculateStat(StatsType.Defence, nature, baseStats, level);
		SpecialAttack = calculateStat(StatsType.SpecialAttack, nature, baseStats, level);
		SpecialDefence = calculateStat(StatsType.SpecialDefence, nature, baseStats, level);
		Speed = calculateStat(StatsType.Speed, nature, baseStats, level);
	}

	public int calculateHP(BaseStats baseStats, int level) {
		return (int) ((((float) IVs.HP + 2 * (float) baseStats.hp + ((float) EVs.HP) / 4f + 100f) * (float) level) / 100f + 10f);
	}

	public int calculateStat(StatsType stat, EnumNature nature, BaseStats baseStats, int level) {
		float val = (float) IVs.get(stat) + 2 * (float) baseStats.get(stat) + ((float) EVs.get(stat) / 4f) * (float) level;
		val /= 100f + 5;
		if (stat == nature.increasedStat)
			val *= 1.1f;
		if (stat == nature.decreasedStat)
			val *= 0.9f;
		return (int) val;
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("StatsHP", HP);
		var1.setInteger("StatsAttack", Attack);
		var1.setInteger("StatsDefence", Defence);
		var1.setInteger("StatsSpecialAttack", SpecialAttack);
		var1.setInteger("StatsSpecialDefence", SpecialDefence);
		var1.setInteger("StatsSpeed", Speed);
		if (IVs != null)
			IVs.writeToNBt(var1);
		EVs.writeToNBT(var1);
	}

	public void readFromNBT(NBTTagCompound var1) {
		HP = var1.getInteger("StatsHP");
		Attack = var1.getInteger("StatsAttack");
		Defence = var1.getInteger("StatsDefence");
		SpecialAttack = var1.getInteger("StatsSpecialAttack");
		SpecialDefence = var1.getInteger("StatsSpecialDefence");
		Speed = var1.getInteger("StatsSpeed");
		if (IVs != null)
			IVs.readFromNBT(var1);
		EVs.readFromNBT(var1);
	}
}
