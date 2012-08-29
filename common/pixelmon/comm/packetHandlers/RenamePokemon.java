package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;

public class RenamePokemon extends PacketHandlerBase {

	public RenamePokemon() {
		packetsHandled.add(EnumPackets.RenamePokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP)pl;
		int id = dataStream.readInt();
		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
			PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(id, player.worldObj).getHelper().nickname = Packet.readString(dataStream, 64);
		} else {
			NBTTagCompound nbt = PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(id);
			if (nbt != null) {
				nbt.setString("Nickname", Packet.readString(dataStream, 64));
			}
		}
		PixelmonStorage.PokeballManager.save();
	}

}
