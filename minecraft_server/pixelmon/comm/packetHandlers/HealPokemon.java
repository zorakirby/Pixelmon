package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.comm.EnumPackets;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;

public class HealPokemon extends PacketHandlerBase {

	public HealPokemon() {
		packetsHandled.add(EnumPackets.HealPokemon);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		int ind = dataStream.readInt();
		if (ind == -1)
			mod_Pixelmon.pokeballManager.getPlayerStorage(player).healAllPokemon();
		else
			mod_Pixelmon.pokeballManager.getPlayerStorage(player).heal(ind);

	}

}
