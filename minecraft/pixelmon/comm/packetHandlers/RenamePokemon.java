package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class RenamePokemon extends PacketHandlerBase {

	public RenamePokemon() {
		packetsHandled.add(EnumPackets.RenamePokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP)pl;
		int id = dataStream.readInt();
		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
			PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(id, player.worldObj).setNickname(Packet.readString(dataStream, 64));
		} else {
			NBTTagCompound nbt = PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(id);
			if (nbt != null) {
				nbt.setString("Nickname", Packet.readString(dataStream, 64));
			}
		}
		PixelmonStorage.PokeballManager.save();
	}

}
