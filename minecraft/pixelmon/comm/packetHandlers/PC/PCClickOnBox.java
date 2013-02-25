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

public class PCClickOnBox extends PacketHandlerBase {

	public PCClickOnBox() {
		packetsHandled.add(EnumPackets.PCClickOnBox);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream data) throws IOException {
		EntityPlayer player = (EntityPlayer) pl;
		int box = data.readInt();
		int boxPos = data.readInt();
		NBTTagCompound n1 = null;
		if (PCData.getMousePokemon(player) != null) {
			n1 = PCData.getMousePokemon(player).nbt;
			if (n1 != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).EntityAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj)) {
				EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj);
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
			}
		}
		NBTTagCompound n = PixelmonStorage.ComputerManager.getPlayerStorage(player).getBox(box).getNBTByPosition(boxPos);
		PCData.setMousePokemon(player, new MapEntry(n, box, boxPos));
		PixelmonStorage.ComputerManager.getPlayerStorage(player).changePokemon(box, boxPos, n1);
		return;
	}

}
