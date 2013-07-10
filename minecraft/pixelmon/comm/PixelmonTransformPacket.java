package pixelmon.comm;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.network.packet.Packet;

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
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(pixelmonID);
		Packet.writeString(transformedModel, data);
	}

	@Override
	public void readPacketData(DataInput data) throws IOException {
		pixelmonID = data.readInt();
		transformedModel = Packet.readString(data, 64);
	}
	
	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
