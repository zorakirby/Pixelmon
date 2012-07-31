package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.comm.EnumPackets;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.mod_Pixelmon;

public class RenamePokemon extends PacketHandlerBase {

	public RenamePokemon() {
		packetsHandled.add(EnumPackets.RenamePokemon);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		int id = dataStream.readInt();
		if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
			mod_Pixelmon.pokeballManager.getPlayerStorage(player).getAlreadyExists(id, player.worldObj).getHelper().nickname = Packet.readString(dataStream, 64);
		} else {
			NBTTagCompound nbt = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getNBT(id);
			if (nbt != null) {
				nbt.setString("Nickname", Packet.readString(dataStream, 64));
			}
		}
		mod_Pixelmon.pokeballManager.save();
	}

}
