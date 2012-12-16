package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonStatsPacket extends PixelmonPacket {
	public int HP;
	public int Speed;
	public int Attack;
	public int Defence;
	public int SpecialAttack;
	public int SpecialDefence;

	public PixelmonStatsPacket() {
	}

	public PixelmonStatsPacket(NBTTagCompound nbt, EnumPackets packetType) {
		this.packetType = packetType;
		HP = nbt.getInteger("StatsHP");
		Speed = nbt.getInteger("StatsSpeed");
		Attack = nbt.getInteger("StatsAttack");
		Defence = nbt.getInteger("StatsDefence");
		SpecialAttack = nbt.getInteger("StatsSpecialAttack");
		SpecialDefence = nbt.getInteger("StatsSpecialDefence");
	}

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
		PixelmonStatsPacket p = new PixelmonStatsPacket(nbt, null);
		return p;
	}

	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeShort((short) HP);
		data.writeShort((short) Speed);
		data.writeShort((short) Attack);
		data.writeShort((short) Defence);
		data.writeShort((short) SpecialAttack);
		data.writeShort((short) SpecialDefence);
	}

	public void readPacketData(DataInputStream data) throws IOException {
		HP = data.readShort();
		Speed = data.readShort();
		Attack = data.readShort();
		Defence = data.readShort();
		SpecialAttack = data.readShort();
		SpecialDefence = data.readShort();
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
