package pixelmon.entities.pixelmon.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.BattleQueryPacket;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.packetHandlers.ReplaceMove;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;

public class EvolutionQuery {
	public static List<EvolutionQuery> queryList = new ArrayList<EvolutionQuery>();

	EntityPixelmon pixelmon;
	String newPokemonName;
	public int pokemonID;
	boolean fromLevelUp;
	int level;

	public EvolutionQuery(EntityPixelmon pixelmon, String newPokemonName, boolean fromLevelUp) {
		this.pixelmon = pixelmon;
		this.newPokemonName = newPokemonName;
		pokemonID = pixelmon.getPokemonId();
		this.fromLevelUp = fromLevelUp;
		sendQuery();
		pixelmon.evolving = 1;
		this.level = pixelmon.getLvl().getLevel();
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
				queryList.get(i).pixelmon.evolving = 0;
				queryList.remove(i);
				break;
			}
		}
	}

	public static void acceptQuery(int pokemonID) {
		for (int i = 0; i < queryList.size(); i++) {
			if (queryList.get(i).pokemonID == pokemonID) {
				queryList.get(i).pixelmon.evolving = 0;
				queryList.get(i).accept();
				queryList.remove(i);
				return;
			}
		}
	}

	private void accept() {
		pixelmon.evolve(newPokemonName);
		checkForLearnMoves();
	}

	private void decline() {
		checkForLearnMoves();
	}

	private void checkForLearnMoves() {
		if (fromLevelUp) {
			String name = pixelmon.getName();
			for (int i = level; i <= pixelmon.getLvl().getLevel(); i++) {
				if (DatabaseMoves.LearnsAttackAtLevel(name, i)) {
					ArrayList<Attack> newAttacks = DatabaseMoves.getAttacksAtLevel(name, i);
					for (Attack a : newAttacks) {
						if (pixelmon.getMoveset().size() >= 4) {
							ReplaceMove.tmID = -1;
							((EntityPlayerMP) pixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(
									EnumPackets.ChooseMoveToReplace, pixelmon.getPokemonId(), a.baseAttack.attackIndex, i));
						} else {
							pixelmon.getMoveset().add(a);
							pixelmon.update(EnumUpdateType.Moveset);
							ChatHandler.sendChat(pixelmon.getOwner(), pixelmon.getNickname() + " just learnt " + a.baseAttack.attackName + "!");
						}
					}
				}
			}
		}
	}

	public static void declineQuery(int pokemonID) {
		for (int i = 0; i < queryList.size(); i++) {
			if (queryList.get(i).pokemonID == pokemonID) {
				queryList.get(i).pixelmon.evolving = 0;
				queryList.get(i).decline();
				queryList.remove(i);
				return;
			}
		}
	}
}
