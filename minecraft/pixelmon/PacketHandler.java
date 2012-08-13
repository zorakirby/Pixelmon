package pixelmon;

import java.io.*;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import pixelmon.Pixelmon;
import pixelmon.PixelmonEntityList;
import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;


public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(NetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int packetID = dataStream.readInt();
			if (packetID == EnumPackets.AddToStorage.getIndex()) {
				ServerStorageDisplay.add(dataStream);
			} else if (packetID == EnumPackets.RemoveFromStorage.getIndex()) {
				ServerStorageDisplay.remove(dataStream.readInt());
			} else if (packetID == EnumPackets.UpdateStorage.getIndex()) {
				ServerStorageDisplay.update(dataStream);
			} else if (packetID == EnumPackets.AddToTempStore.getIndex()) {
				PixelmonServerStore.addToList(dataStream);
			} else if (packetID == EnumPackets.BattleFinished.getIndex()) {
				PixelmonServerStore.clearList();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
