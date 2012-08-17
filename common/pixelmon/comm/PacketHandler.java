package pixelmon.comm;

import java.io.*;
import java.util.ArrayList;

import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import pixelmon.comm.packetHandlers.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;


public class PacketHandler implements IConnectionHandler, IPacketHandler {
	private static ArrayList<PacketHandlerBase> handlers = new ArrayList<PacketHandlerBase>();
	static {
		handlers.add(new ChooseAttack());
		handlers.add(new ChooseStarter());
		handlers.add(new Flee());
		handlers.add(new HealPokemon());
		handlers.add(new PCClick());
		handlers.add(new RegisterPlayer());
		handlers.add(new RenamePokemon());
		handlers.add(new SendPixelmon());
		handlers.add(new SwitchPokemon());
		handlers.add(new ReplaceMove());
	}

	@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int packetID = dataStream.readByte();
			for (PacketHandlerBase p : handlers) {
				if (p.handlesPacket(packetID)) {
					p.handlePacket(packetID, player, dataStream);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, NetworkManager manager) {
		
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, NetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, NetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, NetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionClosed(NetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, NetworkManager manager, Packet1Login login) {
		// TODO Auto-generated method stub
		
	}

}
