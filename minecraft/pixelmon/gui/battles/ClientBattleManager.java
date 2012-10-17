package pixelmon.gui.battles;

import java.util.ArrayList;

import pixelmon.PixelmonServerStore;
import pixelmon.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.EntityCamera;

public class ClientBattleManager {
	private static ArrayList<String> messageList = new ArrayList<String>();

	public static PixelmonDataPacket targetPokemon;

	public static int pokemonId = -1;

	public static EntityCamera camera;

	public static int opponentId = -1;

	public static String getOpponentName(){
		if (opponentId==-1) return null;
		return PixelmonServerStore.getPixelmonDataFromID(opponentId).name;
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
}
