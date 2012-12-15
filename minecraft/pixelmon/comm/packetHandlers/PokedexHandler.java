package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonPokedexPacket;
import pixelmon.pokedex.Pokedex;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;


public class PokedexHandler extends PacketHandlerBase
{
	
	public PokedexHandler()
	{
		super();
		packetsHandled.add(EnumPackets.Pokedex);
	}
	
	public boolean handlesPacket(int id)
	{
		return id == EnumPackets.Pokedex.getIndex();
	}

	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException 
	{
		EntityPlayerMP e = (EntityPlayerMP) player;
		Pokedex p = new Pokedex(e);
		PixelmonPokedexPacket packet = new PixelmonPokedexPacket();
		packet.readPacketData(dataStream);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("Pokedex", packet.data);
		p.readFromNBT(nbt);
		PixelmonStorage.PokeballManager.getPlayerStorage(e).pokedex = p;
	}
	
}