package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
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
		if (bc.participant1.currentPokemon().getPokemonId() == pokemonID)
			bc.setAttack(bc.participant1.currentPokemon(), bc.participant1.currentPokemon().moveset.get(buttonId));
		else
			bc.setAttack(bc.participant2.currentPokemon(), bc.participant2.currentPokemon().moveset.get(buttonId));
}
}
