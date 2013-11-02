package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;

import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;

import cpw.mods.fml.common.network.Player;

public class GuiOpenClose extends PacketHandlerBase {

	public GuiOpenClose() {
		packetsHandled.add(EnumPackets.GuiOpen);
		packetsHandled.add(EnumPackets.GuiClose);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		try {
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).guiOpened = (index == EnumPackets.GuiOpen.getIndex());
			if (index == EnumPackets.GuiClose.getIndex()){
					}
		} catch (Exception e) {
		}
	}
}
