package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class SwitchPokemon extends PacketHandlerBase {

	public SwitchPokemon() {
		packetsHandled.add(EnumPackets.SwitchPokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP) pl;
		int pos = dataStream.readInt();
		BattleController bc = BattleRegistry.getBattle(dataStream.readInt());
		for (BattleParticipant p : bc.participants)
			if (p instanceof PlayerParticipant) {
				if (((PlayerParticipant) p).player == player) {
					bc.SwitchPokemon(p.currentPokemon(), PixelmonStorage.PokeballManager.getPlayerStorage(player).getIDFromPosition(pos));
				}
			}
	}

}
