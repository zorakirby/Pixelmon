package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import cpw.mods.fml.common.network.Player;

import pixelmon.Pixelmon;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPokeballs;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;

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
