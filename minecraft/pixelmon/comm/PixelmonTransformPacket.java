package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet;

import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonTransformPacket extends PixelmonPacket {
	public int pixelmonID;
	public String transformedModel;
	public PixelmonTransformPacket() {
	}

	public PixelmonTransformPacket(int pixelmonID, String newName){
		packetType = EnumPackets.Transform;
		transformedModel = newName;
		this.pixelmonID = pixelmonID;
	}
	
	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(pixelmonID);
		Packet.writeString(transformedModel, data);
	}

	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		pixelmonID = data.readInt();
		transformedModel = Packet.readString(data, 64);
	}
	
	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
