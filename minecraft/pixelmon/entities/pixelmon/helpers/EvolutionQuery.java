package pixelmon.entities.pixelmon.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;

import pixelmon.comm.BattleQueryPacket;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;

public class EvolutionQuery {
	public static List<EvolutionQuery> queryList = new ArrayList<EvolutionQuery>();

	EntityPixelmon pixelmon;
	String newPokemonName;
	public int pokemonID;

	public EvolutionQuery(EntityPixelmon pixelmon, String newPokemonName) {
		this.pixelmon = pixelmon;
		this.newPokemonName = newPokemonName;
		pokemonID = pixelmon.getPokemonId();
		sendQuery();
	}

	private void sendQuery() {
		if (pixelmon.hasOwner()) {
			EntityPlayerMP player = (EntityPlayerMP) pixelmon.getOwner();
			Packet packet = PacketCreator.createStringPacket(EnumPackets.Evolution, pokemonID, newPokemonName);
			player.playerNetServerHandler.sendPacketToPlayer(packet);
			removeExisting();
			queryList.add(this);
		}
	}

	private void removeExisting() {
		for (int i = 0; i < queryList.size(); i++) {
			if (queryList.get(i).pokemonID == pokemonID) {
				queryList.remove(i);
				break;
			}
		}
	}

	public static void acceptQuery(int pokemonID) {
		for (int i = 0; i < queryList.size(); i++) {
			if (queryList.get(i).pokemonID == pokemonID) {
				queryList.get(i).accept();
				queryList.remove(i);
				return;
			}
		}
	}

	private void accept() {
		pixelmon.evolve(newPokemonName);
	}
}
