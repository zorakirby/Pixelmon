package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.battles.BattleController;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.EnumPackets;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;

public class Flee extends PacketHandlerBase {

	public Flee() {
		packetsHandled.add(EnumPackets.Flee);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		BattleController bc = mod_Pixelmon.battleRegistry.getBattle(player);
		if (bc.participant1 instanceof PlayerParticipant)
			if (((PlayerParticipant) bc.participant1).player == player)
				bc.setFlee(bc.participant1.currentPokemon());

		if (bc.participant2 instanceof PlayerParticipant)
			if (((PlayerParticipant) bc.participant2).player == player)
				bc.setFlee(bc.participant2.currentPokemon());

	}

}
