package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import pixelmon.comm.EnumPackets;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;

public abstract class PacketHandlerBase {
	ArrayList<EnumPackets> packetsHandled;

	public PacketHandlerBase() {
		packetsHandled = new ArrayList<EnumPackets>();
	}

	public boolean handlesPacket(int index) {
		for (EnumPackets e : packetsHandled)
			if (e.getIndex() == index)
				return true;
		
		return false;
	}

	public EntityPlayer getPlayer(NetworkManager network){
		return ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
	}
	
	public abstract void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException;
}
