package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.packetHandlers.PC.PCData;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;
import cpw.mods.fml.common.network.Player;

public class RenamePokemon extends PacketHandlerBase {

	public RenamePokemon() {
		packetsHandled.add(EnumPackets.RenamePokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP) pl;
		int id = dataStream.readInt();
		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
			PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(id, player.worldObj).setNickname(Packet.readString(dataStream, 64));
			PixelmonStorage.PokeballManager.save();
		} else if (PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(id) != null) {
			NBTTagCompound nbt = PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(id);
			if (nbt != null) {
				nbt.setString("Nickname", Packet.readString(dataStream, 64));
			}
			PixelmonStorage.PokeballManager.save();
		} else if (PixelmonStorage.ComputerManager.getPlayerStorage(player).contains(id)){
			PlayerComputerStorage comp = PixelmonStorage.ComputerManager.getPlayerStorage(player);

			NBTTagCompound nbt = comp.getPokemonNBT(id);
			if (nbt != null) {
				nbt.setString("Nickname", Packet.readString(dataStream, 64));
				comp.updatePokemonNBT(id, nbt);
			}
		} else if (PCData.getMousePokemon(player).nbt.getInteger("pixelmonID") == id) {
			NBTTagCompound nbt = PCData.getMousePokemon(player).nbt;
			if (nbt != null) {
				nbt.setString("Nickname", Packet.readString(dataStream, 64));
			}
		}
	}

}
