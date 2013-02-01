package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;

import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PokeballManager;

import cpw.mods.fml.common.network.Player;

public class RequestUpdatedPokemonList extends PacketHandlerBase {

	public RequestUpdatedPokemonList() {
		packetsHandled.add(EnumPackets.RequestUpdatedPokemonList);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).sendUpdatedList();
	}

}
