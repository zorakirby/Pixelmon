package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.helpers.EvolutionQuery;

import cpw.mods.fml.common.network.Player;

public class Evolution extends PacketHandlerBase {

	public Evolution() {
		packetsHandled.add(EnumPackets.Evolution);
		packetsHandled.add(EnumPackets.StopEvolution);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		if (index == EnumPackets.Evolution.getIndex())
			EvolutionQuery.acceptQuery(dataStream.readInt());
		else if (index == EnumPackets.StopEvolution.getIndex()) {
			EvolutionQuery.declineQuery(dataStream.readInt());
		}
	}
}
