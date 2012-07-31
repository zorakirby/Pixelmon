package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.comm.EnumPackets;
import pixelmon.enums.EnumGui;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;

public class RegisterPlayer extends PacketHandlerBase {

	public RegisterPlayer() {
		packetsHandled.add(EnumPackets.RegisterPlayer);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).count() == 0) {
			player.openGui(mod_Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), player.worldObj, 0, 0, 0);
		}
	}

}
