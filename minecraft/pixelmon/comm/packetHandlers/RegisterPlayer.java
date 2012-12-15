package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.Pixelmon;
import pixelmon.comm.EnumPackets;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class RegisterPlayer extends PacketHandlerBase {

	public RegisterPlayer() {
		packetsHandled.add(EnumPackets.RegisterPlayer);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP)pl;
		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).count() == 0) {
			player.openGui(Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), player.worldObj, 0, 0, 0);
		}
	}

}
