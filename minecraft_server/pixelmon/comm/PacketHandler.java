package pixelmon.comm;

import java.io.*;
import java.util.ArrayList;

import pixelmon.comm.packetHandlers.*;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.forge.IConnectionHandler;
import net.minecraft.src.forge.IPacketHandler;
import net.minecraft.src.forge.MessageManager;


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
	public void onPacketData(NetworkManager network, String channel, byte[] data) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(data));
		try {
			int packetID = dataStream.readInt();
			for (PacketHandlerBase p : handlers) {
				if (p.handlesPacket(packetID)) {
					p.handlePacket(packetID, network, dataStream);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onConnect(NetworkManager network) {	}

	@Override
	public void onLogin(NetworkManager network, Packet1Login login) {
		MessageManager.getInstance().registerChannel(network, this, "Pixelmon");
	}

	@Override
	public void onDisconnect(NetworkManager network, String message, Object[] args) {	}

}
