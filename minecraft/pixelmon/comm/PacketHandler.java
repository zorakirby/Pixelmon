package pixelmon.comm;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import pixelmon.comm.packetHandlers.*;
import pixelmon.comm.packetHandlers.PC.*;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IConnectionHandler, IPacketHandler {
	private static ArrayList<PacketHandlerBase> handlers = new ArrayList<PacketHandlerBase>();
	static {
		handlers.add(new AcceptDeclineBattle());
		handlers.add(new BagPacket());
		handlers.add(new ChooseAttack());
		handlers.add(new ChooseStarter());
		handlers.add(new DeleteMove());
		handlers.add(new Flee());
		handlers.add(new HealPokemon());
		handlers.add(new Movement());
		handlers.add(new PCClickOnBox());
		handlers.add(new PCClickOnParty());
		handlers.add(new PCClosed());
		handlers.add(new PCTrashPokemon());
		handlers.add(new RegisterPlayer());
		handlers.add(new RenamePokemon());
		handlers.add(new ReplaceMove());
		handlers.add(new RequestPCData());
		handlers.add(new RequestUpdatedPokemonList());
		handlers.add(new SendPixelmon());
		handlers.add(new SetHeldItem());
		handlers.add(new StopStartLevelling());
		handlers.add(new SwapMove());
		handlers.add(new SwitchPokemon());
		handlers.add(new TradingPacket());

	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int packetID = dataStream.readInt();
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
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
	}

}
