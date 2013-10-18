package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.comm.packetHandlers.PC.PCData;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.Player;

public class StopStartLevelling extends PacketHandlerBase {

	public StopStartLevelling() {
		packetsHandled.add(EnumPackets.StopStartLevelling);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP) pl;
		int id = dataStream.readInt();
		try {
			if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
				PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
				EntityPixelmon pixelmon = storage.getAlreadyExists(id, player.worldObj);
				pixelmon.doesLevel = !pixelmon.doesLevel;
				storage.update(pixelmon, EnumUpdateType.CanLevel);
			} else if (PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(id) != null) {
				PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
				NBTTagCompound nbt = storage.getNBT(id);
				if (nbt != null) {
					if (nbt.hasKey("DoesLevel")) {
						if (nbt.getBoolean("DoesLevel"))
							nbt.setBoolean("DoesLevel", false);
						else
							nbt.setBoolean("DoesLevel", true);
					} else
						nbt.setBoolean("DoesLevel", true);
				}
				storage.update(nbt, EnumUpdateType.CanLevel);
			} else if (PixelmonStorage.ComputerManager.getPlayerStorage(player).contains(id)) {
				PlayerComputerStorage comp = PixelmonStorage.ComputerManager.getPlayerStorage(player);
				if (!comp.contains(id))
					return;

				NBTTagCompound nbt = comp.getPokemonNBT(id);
				if (nbt != null) {
					if (nbt.hasKey("DoesLevel")) {
						if (nbt.getBoolean("DoesLevel"))
							nbt.setBoolean("DoesLevel", false);
						else
							nbt.setBoolean("DoesLevel", true);
					} else
						nbt.setBoolean("DoesLevel", true);

					comp.updatePokemonNBT(id, nbt);
				}
			} else if (PCData.getMousePokemon(player).nbt.getInteger("pixelmonID") == id) {
				NBTTagCompound nbt = PCData.getMousePokemon(player).nbt;
				if (nbt != null) {
					if (nbt.hasKey("DoesLevel")) {
						if (nbt.getBoolean("DoesLevel"))
							nbt.setBoolean("DoesLevel", false);
						else
							nbt.setBoolean("DoesLevel", true);
					} else
						nbt.setBoolean("DoesLevel", true);
				}
			}
		} catch (PlayerNotLoadedException e) {
		}
	}
}
