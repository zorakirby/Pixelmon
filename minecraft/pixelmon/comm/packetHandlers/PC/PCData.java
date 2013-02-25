package pixelmon.comm.packetHandlers.PC;

import java.util.HashMap;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PCData {
	private static HashMap<EntityPlayer, MapEntry> mousePokemon = new HashMap<EntityPlayer, MapEntry>();
	public static HashMap<EntityPlayer, Boolean> guiOpen = new HashMap<EntityPlayer, Boolean>();

	public static MapEntry getMousePokemon(EntityPlayer player) {
		return mousePokemon.get(player);
	}

	public static void setMousePokemon(EntityPlayer player, MapEntry entry){
		mousePokemon.put(player, entry);
		if (entry==null)
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearMousePokemon));
		else{
			PixelmonDataPacket p = new PixelmonDataPacket(entry.nbt, EnumPackets.SetMousePokemon);
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(p.getPacket());
		}
	}
}
