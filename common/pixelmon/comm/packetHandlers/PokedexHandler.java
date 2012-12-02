package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NBTTagCompound;

import cpw.mods.fml.common.network.Player;
import pixelmon.comm.*;
import pixelmon.pokedex.Pokedex;
import pixelmon.storage.PixelmonStorage;


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