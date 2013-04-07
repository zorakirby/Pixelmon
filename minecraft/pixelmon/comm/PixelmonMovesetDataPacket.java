package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import pixelmon.entities.pixelmon.stats.Moveset;
import pixelmon.enums.EnumType;

public class PixelmonMovesetDataPacket {
	public String attackName;
	public EnumType type;
	public int pp;
	public int ppBase;
	public boolean disabled = false;

	public static PixelmonMovesetDataPacket createPacket(Moveset moveset, int i) {
		if (moveset.size() <= i)
			return null;
		PixelmonMovesetDataPacket p = new PixelmonMovesetDataPacket();
		p.attackName = moveset.get(i).baseAttack.attackName;
		p.pp = moveset.get(i).pp;
		p.ppBase = moveset.get(i).ppBase;
		p.type = moveset.get(i).baseAttack.attackType;
		p.disabled = moveset.get(i).disabled;
		return p;
	}

	public static PixelmonMovesetDataPacket createPacket(NBTTagCompound nbt, int i) {
		if (nbt.getInteger("PixelmonNumberMoves") <= i)
			return null;
		PixelmonMovesetDataPacket p = new PixelmonMovesetDataPacket();
		p.attackName = nbt.getString("PixelmonMoveName" + i);
		p.type = EnumType.parseType(nbt.getInteger("PixelmonMoveType" + i));
		p.pp = nbt.getInteger("PixelmonMovePP" + i);
		p.ppBase = nbt.getInteger("PixelmonMovePPBase" + i);
		return p;
	}

	public void writeData(DataOutputStream data) throws IOException {
		Packet.writeString(attackName, data);
		data.writeShort(type.getIndex());
		data.writeShort(pp);
		data.writeShort(ppBase);
		data.writeBoolean(disabled);
	}
	
	public void readData(DataInputStream data) throws IOException {
		attackName = Packet.readString(data, 64);
		type = EnumType.parseType(data.readShort());
		pp = data.readShort();
		ppBase = data.readShort();
		disabled = data.readBoolean();
	}

}
