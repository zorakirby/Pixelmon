package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import cpw.mods.fml.common.network.Player;

public class HealPokemon extends PacketHandlerBase {

	public HealPokemon() {
		packetsHandled.add(EnumPackets.HealPokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayer player = (EntityPlayer) pl;
		int ind = dataStream.readInt();
		try {
			if (ind == -1)
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).healAllPokemon();
			else
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).heal(ind);
		} catch (PlayerNotLoadedException e) {
		}
	}
}
