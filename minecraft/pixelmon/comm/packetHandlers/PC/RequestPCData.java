package pixelmon.comm.packetHandlers.PC;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.packetHandlers.PacketHandlerBase;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;

import cpw.mods.fml.common.network.Player;

public class RequestPCData extends PacketHandlerBase {

	public RequestPCData() {
		packetsHandled.add(EnumPackets.RequestPCData);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		PCData.guiOpen.put((EntityPlayer) player, true);
		PlayerComputerStorage s = PixelmonStorage.ComputerManager.getPlayerStorage((EntityPlayerMP) player);
		for (ComputerBox b : s.getBoxList()) {
			for (NBTTagCompound n : b.getStoredPokemon()) {
				if (n != null) {
					PixelmonDataPacket p = new PixelmonDataPacket(n, EnumPackets.AddToTempStore);
					((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(p.getPacket());
				}
			}
		}
	}

}
