package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;

public class ReplaceMove extends PacketHandlerBase {

	public ReplaceMove() {
		packetsHandled.add(EnumPackets.ReplaceMove);
	}

	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		int pokemonID = dataStream.readInt();
		int moveToLearnIndex = dataStream.readInt();
		int replaceIndex = dataStream.readInt();
		
		EntityPlayer player =getPlayer(network);
		Attack a = DatabaseMoves.getAttack(moveToLearnIndex);
		
		IHaveHelper p = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getAlreadyExists(pokemonID, player.worldObj);
		ChatHandler.sendChat(player, "Your " + p.getHelper().getName() + " forgot " + p.getHelper().moveset.get(replaceIndex).attackName + ", and learned " + a.attackName);
		p.getHelper().moveset.set(replaceIndex, a);
	}
}
