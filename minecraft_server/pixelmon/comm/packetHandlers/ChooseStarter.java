package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.PixelmonEntityList;
import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.enums.EnumPokeballs;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;

public class ChooseStarter extends PacketHandlerBase {

	public ChooseStarter() {
		packetsHandled.add(EnumPackets.ChooseStarter);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		IHaveHelper p = (IHaveHelper) PixelmonEntityList.createEntityByName(StarterList.getStarterStringList()[dataStream.readInt()], player.worldObj);
		p.getHelper().getLvl().setLevel(5);
		p.getHelper().loadMoveset();
		p.getHelper().caughtBall = EnumPokeballs.MasterBall;
		mod_Pixelmon.pokeballManager.getPlayerStorage(player).addToParty(p.getHelper());
	}

}
