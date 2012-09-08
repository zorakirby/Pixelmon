package pixelmon.entities.pixelmon.stats;

import net.minecraft.src.NBTTagCompound;

public class EVsStore {
	public int HP = 0;
	public int Attack = 0;
	public int Defence = 0;
	public int SpecialAttack = 0;
	public int SpecialDefence = 0;
	public int Speed = 0;

	public void gainEV(int[] evGain) {
		HP += evGain[0];
		if (HP > 255)
			HP = 255;
		Attack += evGain[1];
		if (Attack > 255)
			Attack = 255;
		Defence += evGain[2];
		if (Defence > 255)
			Defence = 255;
		SpecialAttack += evGain[3];
		if (SpecialAttack > 255)
			SpecialAttack = 255;
		SpecialDefence += evGain[4];
		if (SpecialDefence > 255)
			SpecialDefence = 255;
		Speed += evGain[5];
		if (Speed > 255)
			Speed = 255;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("EVHP", HP);
		nbt.setInteger("EVAttack", Attack);
		nbt.setInteger("EVDefence", Defence);
		nbt.setInteger("EVSpecialAttack", SpecialAttack);
		nbt.setInteger("EVSpecialDefence", SpecialDefence);
		nbt.setInteger("EVSpeed", Speed);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		HP = nbt.getInteger("EVHP");
		Attack = nbt.getInteger("EVAttack");
		Defence = nbt.getInteger("EVDefence");
		SpecialAttack = nbt.getInteger("EVSpecialAttack");
		SpecialDefence = nbt.getInteger("EVSpecialDefence");
		Speed = nbt.getInteger("EVSpeed");
	}
	
	
}
