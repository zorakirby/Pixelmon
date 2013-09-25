package pixelmon.client.gui.battles;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.participants.ParticipantType;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemData;

public class ClientBattleManager {
	public boolean mustUseLastMove = false;
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

	public static PixelmonDataPacket opponent;

	public static ArrayList<ItemData> bagStore = new ArrayList<ItemData>();

	public static ArrayList<PixelmonLevelUpPacket> levelUpList = new ArrayList<PixelmonLevelUpPacket>();

	public static ArrayList<AttackData> newAttackList = new ArrayList<ClientBattleManager.AttackData>();

	public static ParticipantType opponentType;

	public static boolean goBackToMainMenu;

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

	public static PixelmonDataPacket getUserPokemonPacket() {
		if (pokemonId != -1) {
			return ServerStorageDisplay.get(pokemonId);
		}
		return null;
	}

	static int oldId = -1;
	static EntityPixelmon oldPixelmon;

	public static boolean isTM = false;

	public static boolean canSwitch = true;

	public static EntityPixelmon getUserPokemon() {
		if (pokemonId != -1) {
			if (oldId == pokemonId)
				return oldPixelmon;
			for (Object e : Minecraft.getMinecraft().theWorld.loadedEntityList) {
				if (e instanceof EntityPixelmon) {
					if (((EntityPixelmon) e).getPokemonId() == pokemonId) {
						oldPixelmon = (EntityPixelmon) e;
						return (EntityPixelmon) e;
					}
				}
			}
			oldId = pokemonId;
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
	
	public void setMustUseLastMove() {
		mustUseLastMove = true;
	}
}
