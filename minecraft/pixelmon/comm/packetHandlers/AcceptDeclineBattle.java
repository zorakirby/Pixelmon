package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;

import pixelmon.battles.BattleQuery;
import pixelmon.comm.EnumPackets;

import cpw.mods.fml.common.network.Player;

public class AcceptDeclineBattle extends PacketHandlerBase {

	public AcceptDeclineBattle() {
		packetsHandled.add(EnumPackets.AcceptBattle);
		packetsHandled.add(EnumPackets.DeclineBattle);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		BattleQuery query = BattleQuery.getQuery(dataStream.readInt());
		if (index == EnumPackets.AcceptBattle.getIndex())
			query.acceptQuery((EntityPlayerMP) player);
		else if (index == EnumPackets.DeclineBattle.getIndex())
			query.declineQuery((EntityPlayerMP) player);
	}
}
