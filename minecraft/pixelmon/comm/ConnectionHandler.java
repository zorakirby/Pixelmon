package pixelmon.comm;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import pixelmon.Pixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		if (!PixelmonStorage.PokeballManager.hasPlayerFile(player) || PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).count() == 0)
			((EntityPlayerMP) player).openGui(Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), ((EntityPlayerMP) player).worldObj, 0, 0, 0);
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed(INetworkManager manager) {
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		// TODO Auto-generated method stub

	}

}
