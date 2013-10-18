package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.Player;

public class ReplaceMove extends PacketHandlerBase {
	public static int tmID = -1;

	public ReplaceMove() {
		packetsHandled.add(EnumPackets.ReplaceMove);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		int pokemonID = dataStream.readInt();
		int moveToLearnIndex = dataStream.readInt();
		int replaceIndex = dataStream.readInt();

		EntityPlayerMP player = (EntityPlayerMP) pl;
		Attack a = DatabaseMoves.getAttack(moveToLearnIndex);
		try {
			PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
			EntityPixelmon p;
			if (storage.EntityAlreadyExists(pokemonID, player.worldObj))
				p = storage.getAlreadyExists(pokemonID, player.worldObj);
			else
				p = storage.sendOut(pokemonID, player.worldObj);
			ChatHandler.sendChat(player, "Your " + p.getName() + " forgot " + p.getMoveset().get(replaceIndex).baseAttack.attackName + ", and learned "
					+ a.baseAttack.attackName);
			p.getMoveset().set(replaceIndex, a);
			storage.update(p, EnumUpdateType.Moveset);
			if (tmID != -1) {
				if (!player.capabilities.isCreativeMode)
					player.inventory.consumeInventoryItem(tmID);
			}
		} catch (PlayerNotLoadedException e) {
		}
	}
}
