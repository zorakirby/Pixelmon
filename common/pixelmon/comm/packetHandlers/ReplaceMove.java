package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetworkManager;

public class ReplaceMove extends PacketHandlerBase {

	public ReplaceMove() {
		packetsHandled.add(EnumPackets.ReplaceMove);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		int pokemonID = dataStream.readInt();
		int moveToLearnIndex = dataStream.readInt();
		int replaceIndex = dataStream.readInt();
		
		EntityPlayerMP player =(EntityPlayerMP)pl;
		Attack a = DatabaseMoves.getAttack(moveToLearnIndex);
		
		IHaveHelper p = PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(pokemonID, player.worldObj);
		ChatHandler.sendChat(player, "Your " + p.getHelper().getName() + " forgot " + p.getHelper().moveset.get(replaceIndex).attackName + ", and learned " + a.attackName);
		p.getHelper().moveset.set(replaceIndex, a);
	}
}
