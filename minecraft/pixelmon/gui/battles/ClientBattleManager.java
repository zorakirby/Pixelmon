package pixelmon.gui.battles;

import java.util.ArrayList;

import pixelmon.PixelmonServerStore;
import pixelmon.ServerStorageDisplay;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.comm.PixelmonStatsPacket;
import pixelmon.entities.EntityCamera;
import pixelmon.items.ItemData;

public class ClientBattleManager {
	public static class AttackData {
		public int pokemonID;
		public Attack attack;
		public int level;

		public AttackData(int pokemonId, Attack attack, int level) {
			this.pokemonID = pokemonId;
			this.attack = attack;
			this.level = level;
		}
	}

	private static ArrayList<String> messageList = new ArrayList<String>();

	public static int pokemonId = -1;

	public static EntityCamera camera;
	
	public static PixelmonDataPacket opponent;

	public static ArrayList<ItemData> bagStore = new ArrayList<ItemData>();

	public static ArrayList<PixelmonLevelUpPacket> levelUpList = new ArrayList<PixelmonLevelUpPacket>();

	public static ArrayList<AttackData> newAttackList = new ArrayList<ClientBattleManager.AttackData>();

	public static PixelmonDataPacket getOpponent() {
		return opponent;
	}

	public static void addMessage(String s) {
		messageList.add(s);
	}

	public static String getNextMessage() {
		return messageList.get(0);
	}

	public static void removeMessage() {
		messageList.remove(0);
	}

	public static boolean hasMoreMessages() {
		return messageList.size() > 0;
	}

	public static PixelmonDataPacket getUserPokemon() {
		if (pokemonId != -1) {
			return ServerStorageDisplay.get(pokemonId);
		}
		return null;
	}

	public static void clearMessages() {
		messageList.clear();
	}

	public static boolean hasLevelUps() {
		return levelUpList.size() > 0;
	}

	public static boolean hasNewAttacks() {
		return newAttackList.size() > 0;
	}
}
