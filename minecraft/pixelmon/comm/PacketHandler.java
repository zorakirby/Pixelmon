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
import pixelmon.comm.packetHandlers.BagPacket;
import pixelmon.comm.packetHandlers.ChooseAttack;
import pixelmon.comm.packetHandlers.ChooseStarter;
import pixelmon.comm.packetHandlers.DeleteMove;
import pixelmon.comm.packetHandlers.Flee;
import pixelmon.comm.packetHandlers.HealPokemon;
import pixelmon.comm.packetHandlers.Movement;
import pixelmon.comm.packetHandlers.PCClick;
import pixelmon.comm.packetHandlers.PacketHandlerBase;
import pixelmon.comm.packetHandlers.RegisterPlayer;
import pixelmon.comm.packetHandlers.RenamePokemon;
import pixelmon.comm.packetHandlers.ReplaceMove;
import pixelmon.comm.packetHandlers.SendPixelmon;
import pixelmon.comm.packetHandlers.SetHeldItem;
import pixelmon.comm.packetHandlers.StopStartLevelling;
import pixelmon.comm.packetHandlers.SwapMove;
import pixelmon.comm.packetHandlers.SwitchPokemon;
import pixelmon.comm.packetHandlers.TradingPacket;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IConnectionHandler, IPacketHandler {
	private static ArrayList<PacketHandlerBase> handlers = new ArrayList<PacketHandlerBase>();
	static {
		handlers.add(new BagPacket());
		handlers.add(new ChooseAttack());
		handlers.add(new ChooseStarter());
		handlers.add(new DeleteMove());
		handlers.add(new Flee());
		handlers.add(new HealPokemon());
		handlers.add(new Movement());
		handlers.add(new PCClick());
		handlers.add(new RegisterPlayer());
		handlers.add(new RenamePokemon());
		handlers.add(new ReplaceMove());
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
