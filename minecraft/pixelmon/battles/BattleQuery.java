package pixelmon.battles;

import java.util.ArrayList;

import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.BattleQueryPacket;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import net.minecraft.entity.player.EntityPlayerMP;

public class BattleQuery {
	private static ArrayList<BattleQuery> queryList = new ArrayList<BattleQuery>();

	static int index = 0;
	EntityPlayerMP player1, player2;
	public int queryIndex;
	public EntityPixelmon pokemon1, pokemon2;

	boolean player1Accepted = false, player2Accepted = false;

	public BattleQuery(EntityPlayerMP player1, EntityPixelmon pokemon1, EntityPlayerMP player2, EntityPixelmon pokemon2) {
		this.player1 = player1;
		this.player2 = player2;
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
		queryIndex = index++;

		sendQuery();
	}

	private void sendQuery() {
		try {
			BattleQueryPacket packet1 = new BattleQueryPacket(queryIndex, player2.username, PixelmonStorage.PokeballManager.getPlayerStorage(player2));
			player1.playerNetServerHandler.sendPacketToPlayer(packet1);

			BattleQueryPacket packet2 = new BattleQueryPacket(queryIndex, player1.username, PixelmonStorage.PokeballManager.getPlayerStorage(player1));
			player2.playerNetServerHandler.sendPacketToPlayer(packet2);

			queryList.add(this);
		} catch (PlayerNotLoadedException e) {
		}
	}

	public void acceptQuery(EntityPlayerMP player) {
		if (player == player1)
			player1Accepted = true;
		else if (player == player2)
			player2Accepted = true;
		if (player1Accepted != player2Accepted) {
			if (player1Accepted)
				player2.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.AcceptBattle));
			if (player2Accepted)
				player1.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.AcceptBattle));
		}
		if (player1Accepted && player2Accepted) {
			try {
				if (pokemon1 == null)
					pokemon1 = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);
				if (pokemon2 == null)
					pokemon2 = PixelmonStorage.PokeballManager.getPlayerStorage(player2).getFirstAblePokemon(player2.worldObj);
				pokemon1.StartBattle(new PlayerParticipant(player1, pokemon1), new PlayerParticipant(player2, pokemon2));
				queryList.remove(this);
			} catch (PlayerNotLoadedException e) {
			}
		}
	}

	public void declineQuery(EntityPlayerMP player) {
		if (player == player1)
			player2.closeScreen();
		if (player == player2)
			player1.closeScreen();

		ChatHandler.sendChat(player1, player2, player.username + " declined the battle!");
		queryList.remove(this);
	}

	public static BattleQuery getQuery(int index) {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < queryList.size(); i++) {
				if (queryList.get(i).queryIndex == index)
					return queryList.get(i);
			}
		}
		return null;
	}

	public static BattleQuery getQuery(EntityPlayerMP player) {
		for (int i = 0; i < queryList.size(); i++) {
			if (queryList.get(i).player1 == player || queryList.get(i).player2 == player)
				return queryList.get(i);
		}
		return null;
	}
}
