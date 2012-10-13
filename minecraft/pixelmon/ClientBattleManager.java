package pixelmon;

import java.util.ArrayList;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.EntityCamera;

public class ClientBattleManager {
	private static ArrayList<String> messageList = new ArrayList<String>();

	public static PixelmonDataPacket targetPokemon;

	public static String opponentName;

	public static EntityCamera camera;
	
	public static void addMessage(String s){
		messageList.add(s);
	}
	
	public static String getNextMessage() {
		String message = messageList.get(0);
		messageList.remove(0);
		return message;
	}

	public static boolean hasMoreMessages() {
		return messageList.size() > 0;
	}
}
