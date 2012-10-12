package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class BattleMessagePacket extends PixelmonPacket {

	public String message;
	
	public BattleMessagePacket() {
		this.packetType = EnumPackets.BattleMessage;
	}

	public BattleMessagePacket(String message) {
		this.packetType = EnumPackets.BattleMessage;
		this.message = message;
	}
	
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		message = Packet.readString(data, 64);
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		Packet.writeString(message, data);
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
