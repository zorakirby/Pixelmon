package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

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
		PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
		EntityPixelmon p;
		Attack a = DatabaseMoves.getAttack(selected);
		Attack a1 = DatabaseMoves.getAttack(clicked);
		if (storage.EntityAlreadyExists(pokemonID, player.worldObj))
			p = storage.getAlreadyExists(pokemonID, player.worldObj);
		else
			p = storage.sendOut(pokemonID, player.worldObj);
		p.moveset.swap(selected, clicked, a, a1);
		storage.updateNBT(p);
	}
}
