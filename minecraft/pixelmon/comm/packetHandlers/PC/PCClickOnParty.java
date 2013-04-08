package pixelmon.comm.packetHandlers.PC;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.Player;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.packetHandlers.PacketHandlerBase;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;

public class PCClickOnParty extends PacketHandlerBase {

	public PCClickOnParty() {
		packetsHandled.add(EnumPackets.PCClickOnParty);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream data) throws IOException {
		EntityPlayer player = (EntityPlayer) pl;
		int pos = data.readInt();
		int id = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getIDFromPosition(pos);
		NBTTagCompound n = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getNBT(id);
		NBTTagCompound n1 = null;
		if (PCData.getMousePokemon(player) != null)
			n1 = PCData.getMousePokemon(player).nbt;
		PCData.setMousePokemon(player, new MapEntry(n, -1, pos));
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).changePokemon(pos, n1);
		
		if (PCData.getMousePokemon(player) != null) {
			n1 = PCData.getMousePokemon(player).nbt;
			if (n1 != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).EntityAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj)) {
				EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj);
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
			}
		}
	}

}
