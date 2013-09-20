package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.Player;

public class SwapMove extends PacketHandlerBase {

	public SwapMove() {
		packetsHandled.add(EnumPackets.SwapMove);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		int pokemonID = dataStream.readInt();
		int selected = dataStream.readInt();
		int clicked = dataStream.readInt();

		EntityPlayerMP player = (EntityPlayerMP) pl;
		try {
			if (PixelmonStorage.PokeballManager.getPlayerStorage(player).contains(pokemonID)) {
				PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
				EntityPixelmon p;
				if (storage.EntityAlreadyExists(pokemonID, player.worldObj))
					p = storage.getAlreadyExists(pokemonID, player.worldObj);
				else
					p = storage.sendOut(pokemonID, player.worldObj);
				p.getMoveset().swap(selected, clicked);
				storage.update(p, EnumUpdateType.Moveset);
			} else if (PixelmonStorage.ComputerManager.getPlayerStorage(player).contains(pokemonID)) {
				PlayerComputerStorage compStore = PixelmonStorage.ComputerManager.getPlayerStorage(player);
				EntityPixelmon p = compStore.getPokemonEntity(pokemonID);
				p.getMoveset().swap(selected, clicked);
				compStore.updatePokemonEntry(p);
			}
		} catch (PlayerNotLoadedException e) {
		}
	}
}
