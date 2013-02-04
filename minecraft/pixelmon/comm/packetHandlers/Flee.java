package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.EnumPackets;
import cpw.mods.fml.common.network.Player;

public class Flee extends PacketHandlerBase {

	public Flee() {
		packetsHandled.add(EnumPackets.Flee);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayer player = (EntityPlayer) pl;
		BattleController bc = BattleRegistry.getBattle(player);
		for (BattleParticipant p : bc.participants)
			if (p instanceof PlayerParticipant)
				if (((PlayerParticipant) p).player == player)
					bc.setFlee(p.currentPokemon());
	}

}
