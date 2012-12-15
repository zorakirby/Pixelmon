package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonStatsPacket {
	public int HP;
	public int Speed;
	public int Attack;
	public int Defence;
	public int SpecialAttack;
	public int SpecialDefence;

	public static PixelmonStatsPacket createPacket(EntityPixelmon pixelmon) {
		PixelmonStatsPacket p = new PixelmonStatsPacket();
		p.HP = pixelmon.stats.HP;
		p.Speed = pixelmon.stats.Speed;
		p.Attack = pixelmon.stats.Attack;
		p.Defence = pixelmon.stats.Defence;
		p.SpecialAttack = pixelmon.stats.SpecialAttack;
		p.SpecialDefence = pixelmon.stats.SpecialDefence;
		return p;
	}

	public static PixelmonStatsPacket createPacket(NBTTagCompound nbt) {
		PixelmonStatsPacket p = new PixelmonStatsPacket();
		p.HP = nbt.getInteger("StatsHP");
		p.Speed = nbt.getInteger("StatsSpeed");
		p.Attack = nbt.getInteger("StatsAttack");
		p.Defence = nbt.getInteger("StatsDefence");
		p.SpecialAttack = nbt.getInteger("StatsSpecialAttack");
		p.SpecialDefence = nbt.getInteger("StatsSpecialDefence");
		return p;
	}

	public void writeData(DataOutputStream data) throws IOException {
		data.writeShort((short)HP);
		data.writeShort((short)Speed);
		data.writeShort((short)Attack);
		data.writeShort((short)Defence);
		data.writeShort((short)SpecialAttack);
		data.writeShort((short)SpecialDefence);
	}
	
	public void readData(DataInputStream data) throws IOException {
		HP = data.readShort();
		Speed = data.readShort();
		Attack = data.readShort();
		Defence = data.readShort();
		SpecialAttack = data.readShort();
		SpecialDefence = data.readShort();
	}

}
