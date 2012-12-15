package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class StopStartLevelling extends PacketHandlerBase {

	public StopStartLevelling() {
		packetsHandled.add(EnumPackets.StopStartLevelling);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP)pl;
		int id = dataStream.readInt();
		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
			EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(id, player.worldObj);
			pixelmon.doesLevel = !pixelmon.doesLevel;
			PixelmonStorage.PokeballManager.getPlayerStorage(player).updateNBT(pixelmon);
		} else {
			NBTTagCompound nbt = PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(id);
			if (nbt != null) {
				if (nbt.hasKey("DoesLevel")){
					if (nbt.getBoolean("DoesLevel"))
						nbt.setBoolean("DoesLevel", false);
					else
						nbt.setBoolean("DoesLevel", true);
				}
				else
					nbt.setBoolean("DoesLevel", true);
			}
		}
		PixelmonStorage.PokeballManager.save();
	}

}
