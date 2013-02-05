package pixelmon.entities.pixelmon.stats;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.RandomHelper;

public class IVStore {
	public int HP;
	public int Attack;
	public int Defence;
	public int SpAtt;
	public int SpDef;
	public int Speed;

	public static IVStore CreateNewIVs() {
		IVStore iv = new IVStore();
		iv.SpDef = RandomHelper.getRandomNumberBetween(0, 32);
		iv.SpAtt = RandomHelper.getRandomNumberBetween(0, 32);
		iv.Speed = RandomHelper.getRandomNumberBetween(0, 32);
		iv.Defence = RandomHelper.getRandomNumberBetween(0, 32);
		iv.Attack = RandomHelper.getRandomNumberBetween(0, 32);
		iv.HP = RandomHelper.getRandomNumberBetween(0, 32);
		return iv;
	}

	public void writeToNBt(NBTTagCompound var1) {
		var1.setInteger("IVHP", HP);
		var1.setInteger("IVAttack", Attack);
		var1.setInteger("IVDefence", Defence);
		var1.setInteger("IVSpAtt", SpAtt);
		var1.setInteger("IVSpDef", SpDef);
		var1.setInteger("IVSpeed", Speed);
	}

	public void readFromNBT(NBTTagCompound var1) {
		HP = var1.getInteger("IVHP");
		Attack = var1.getInteger("IVAttack");
		Defence = var1.getInteger("IVDefence");
		SpAtt = var1.getInteger("IVSpAtt");
		SpDef = var1.getInteger("IVSpDef");
		Speed = var1.getInteger("IVSpeed");
	}

	public void CopyIVs(IVStore iVs) {
		HP = iVs.HP;
		Attack = iVs.Attack;
		Defence = iVs.Defence;
		SpAtt = iVs.SpAtt;
		SpDef = iVs.SpDef;
		Speed = iVs.Speed;
	}

	public int get(StatsType stat) {
		if (stat == StatsType.Attack)
			return Attack;
		if (stat == StatsType.Defence)
			return Defence;
		if (stat == StatsType.HP)
			return HP;
		if (stat == StatsType.SpecialAttack)
			return SpAtt;
		if (stat == StatsType.SpecialDefence)
			return SpDef;
		if (stat == StatsType.Speed)
			return Speed;
		return -1;
	}
}
