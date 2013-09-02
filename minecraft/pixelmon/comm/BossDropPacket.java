package pixelmon.comm;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class BossDropPacket extends PixelmonPacket {
	public int[] itemIds;

	public BossDropPacket() {
	}

	public BossDropPacket(int... itemIds) {
		packetType = EnumPackets.BossDrop;
		this.itemIds = itemIds;
	}

	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeShort(itemIds.length);
		for (int i = 0; i < itemIds.length; i++)
			data.writeInt(itemIds[i]);
	}

	@Override
	public void readPacketData(DataInput data) throws IOException {
		itemIds = new int[data.readShort()];
		for (int i = 0; i < itemIds.length; i++)
			itemIds[i] = data.readInt();
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
