package pixelmon.comm.packetHandlers.PC;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.packetHandlers.PacketHandlerBase;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class PCClosed extends PacketHandlerBase {

	public PCClosed() {
		packetsHandled.add(EnumPackets.PCClosed);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayer player = (EntityPlayer) pl;
		PCData.guiOpen.put(player, false);
		if (PCData.getMousePokemon(player) == null || PCData.getMousePokemon(player).nbt==null)
			return;
		MapEntry e = PCData.getMousePokemon(player);
		if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).EntityAlreadyExists(e.nbt.getInteger("pixelmonID"), player.worldObj)) {
			EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getAlreadyExists(e.nbt.getInteger("pixelmonID"), player.worldObj);
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).retrieve(pixelmon);
			pixelmon.catchInPokeball();
		}
		PCData.setMousePokemon(player, null);
		if (e.originalBox == -1)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).addToFirstEmptySpace(e.nbt);
		else
			PixelmonStorage.ComputerManager.getPlayerStorage(player).addToBox(e.originalBox, e.nbt);
		return;
	}

}
