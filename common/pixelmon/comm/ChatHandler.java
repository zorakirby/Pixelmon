package pixelmon.comm;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;

public class ChatHandler {

	public static void sendChat(EntityPlayer owner, String string) {
		if (owner!=null)
			owner.sendChatToPlayer(string);
	}

	public static void sendChat(EntityPlayer owner, EntityPlayer owner2,
			String string) {
		if (owner!=null )
			owner.sendChatToPlayer(string);
		if (owner2!=null)
			owner2.sendChatToPlayer(string);
		
	}

}
