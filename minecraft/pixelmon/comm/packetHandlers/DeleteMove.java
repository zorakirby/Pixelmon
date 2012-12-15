package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.Player;

public class DeleteMove extends PacketHandlerBase {

	public DeleteMove() {
		packetsHandled.add(EnumPackets.DeleteMove);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		int pokemonID = dataStream.readInt();
		int removeIndex = dataStream.readInt();

		EntityPlayerMP player = (EntityPlayerMP) pl;
		PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
		EntityPixelmon p;
		if (storage.EntityAlreadyExists(pokemonID, player.worldObj))
			p = storage.getAlreadyExists(pokemonID, player.worldObj);
		else
			p = storage.sendOut(pokemonID, player.worldObj);
		ChatHandler.sendChat(player, "Your " + p.getName() + " forgot " + p.moveset.get(removeIndex).baseAttack.attackName + "!");
		p.moveset.remove(removeIndex);
		storage.updateNBT(p);
	}
}
