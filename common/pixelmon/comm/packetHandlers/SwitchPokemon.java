package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetServerHandler;

public class SwitchPokemon extends PacketHandlerBase {

	public SwitchPokemon() {
		packetsHandled.add(EnumPackets.SwitchPokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP)pl;
		int pos = dataStream.readInt();
		BattleController bc = BattleRegistry.getBattle(dataStream.readInt());
		if (bc.participant1 instanceof PlayerParticipant) {
			if (((PlayerParticipant) bc.participant1).player == player) {
				bc.SwitchPokemon(bc.participant1.currentPokemon(), PixelmonStorage.PokeballManager.getPlayerStorage(player).getIDFromPosition(pos));
			}
		}
		if (bc.participant2 instanceof PlayerParticipant) {
			if (((PlayerParticipant) bc.participant2).player == player) {
				bc.SwitchPokemon(bc.participant2.currentPokemon(), PixelmonStorage.PokeballManager.getPlayerStorage(player).getIDFromPosition(pos));
			}

		}
	}

}
