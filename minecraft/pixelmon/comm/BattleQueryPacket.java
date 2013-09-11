package pixelmon.comm;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.network.packet.Packet;

import pixelmon.storage.PlayerStorage;

public class BattleQueryPacket extends PixelmonPacket {
	public String opponentName;
	public int queryIndex;
	public int[] pokeballs = { -1, -1, -1, -1, -1, -1 };

	public BattleQueryPacket() {
	}

	public BattleQueryPacket(int queryIndex, String opponentName, PlayerStorage opponentStorage) {
		this.packetType = EnumPackets.BattleQuery;
		this.queryIndex = queryIndex;
		this.opponentName = opponentName;
		for (int i = 0; i < 6; i++) {
			if (opponentStorage.partyPokemon[i] != null) {
				pokeballs[i] = opponentStorage.partyPokemon[i].getInteger("CaughtBall");
				if (opponentStorage.partyPokemon[i].getBoolean("IsFainted"))
					pokeballs[i] = pokeballs[i] * -1 - 1;
			}
		}
	}

	@Override
	public void writePacketData(DataOutput dataStream) throws IOException {
		dataStream.writeInt(queryIndex);
		Packet.writeString(opponentName, dataStream);
		for (int i = 0; i < 6; i++) {
			dataStream.writeShort(pokeballs[i]);
		}
	}

	@Override
	public void readPacketData(DataInput dataStream) throws IOException {
		queryIndex = dataStream.readInt();
		opponentName = Packet.readString(dataStream, 64);
		for (int i = 0; i < 6; i++) {
			pokeballs[i] = dataStream.readShort();
		}
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
