package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import pixelmon.battles.BattleController;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPokeballs;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;

public class ChooseAttack extends PacketHandlerBase {

	public ChooseAttack() {
		packetsHandled.add(EnumPackets.ChooseAttack);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		int buttonId = dataStream.readInt();
		int battleIndex = dataStream.readInt();
		int pokemonID = dataStream.readInt();
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		if (buttonId < 4) {
			BattleController bc = mod_Pixelmon.battleRegistry.getBattle(battleIndex);
			if (bc.participant1.currentPokemon().getPokemonId() == pokemonID)
				bc.setAttack(bc.participant1.currentPokemon(), bc.participant1.currentPokemon().moveset.get(buttonId));
			else
				bc.setAttack(bc.participant2.currentPokemon(), bc.participant2.currentPokemon().moveset.get(buttonId));
		} else if (buttonId == 11) {
			player.openGui(mod_Pixelmon.instance, EnumGui.ChoosePokemon.getIndex(), player.worldObj, 0, 0, 0);
		} else if (buttonId == 10) {
			BattleController bc = mod_Pixelmon.battleRegistry.getBattle(battleIndex);
			if (bc.participant1.currentPokemon().getPokemonId() == pokemonID)
				bc.setFlee(bc.participant1.currentPokemon());
			else
				bc.setFlee(bc.participant2.currentPokemon());
		}
	}
}
