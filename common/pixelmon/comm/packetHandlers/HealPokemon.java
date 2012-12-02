package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetServerHandler;

public class HealPokemon extends PacketHandlerBase {

	public HealPokemon() {
		packetsHandled.add(EnumPackets.HealPokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayer player = (EntityPlayer)pl;
		int ind = dataStream.readInt();
		if (ind == -1)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).healAllPokemon();
		else
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).heal(ind);
	}

}
