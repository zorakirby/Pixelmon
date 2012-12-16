package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonLevelUpPacket extends PixelmonPacket {
	public int pokemonID;
	public int level;
	public PixelmonStatsPacket statsLevel1;
	public PixelmonStatsPacket statsLevel2;

	public PixelmonMovesetDataPacket[] moveset = new PixelmonMovesetDataPacket[4];

	public PixelmonLevelUpPacket() {

	}

	public PixelmonLevelUpPacket(EntityPixelmon p, int level, PixelmonStatsPacket statsLevel1, PixelmonStatsPacket statsLevel2 , EnumPackets packetType) {
		this.packetType = packetType;
		pokemonID = p.getPokemonId();
		this.level = level;
		this.statsLevel1 = statsLevel1;
		this.statsLevel2 = statsLevel2;
	}

	public PixelmonLevelUpPacket(NBTTagCompound p, int level, PixelmonStatsPacket statsLevel1, PixelmonStatsPacket statsLevel2 , EnumPackets packetType) {
		this.packetType = packetType;
		pokemonID = p.getInteger("pixelmonID");
		this.level = level;
		this.statsLevel1 = statsLevel1;
		this.statsLevel2 = statsLevel2;
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(pokemonID);
		data.writeInt(level);
		statsLevel1.writePacketData(data);
		statsLevel2.writePacketData(data);
	}

	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		pokemonID = data.readInt();
		level = data.readInt();
		statsLevel1 = new PixelmonStatsPacket();
		statsLevel1.readPacketData(data);
		statsLevel2 = new PixelmonStatsPacket();
		statsLevel2.readPacketData(data);
	}
}
