package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.comm.EnumPackets;

import cpw.mods.fml.common.network.Player;

public class BagPackets extends PacketHandlerBase {

	public BagPackets() {
		packetsHandled.add(EnumPackets.BagPacket);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		
	}

}
