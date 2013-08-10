package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.comm.EnumPackets;
import cpw.mods.fml.common.network.Player;

public class ChooseAttack extends PacketHandlerBase {

	public ChooseAttack() {
		packetsHandled.add(EnumPackets.ChooseAttack);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		int buttonId = dataStream.readInt();
		int battleIndex = dataStream.readInt();
		int pokemonID = dataStream.readInt();
		BattleController bc = BattleRegistry.getBattle(battleIndex);
		for (BattleParticipant p : bc.participants)
			if (p.currentPokemon().getPokemonId() == pokemonID)
				bc.setAttack(p.currentPokemon(), p.currentPokemon().getMoveset().get(buttonId));
	}
}
