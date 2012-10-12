package pixelmon.comm;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

public class ChatHandler {

	public static void sendChat(EntityLiving entityLiving, String string) {
		if (entityLiving != null)
			((EntityPlayerMP) entityLiving).sendChatToPlayer(string);
	}

	public static void sendChat(EntityLiving owner, EntityLiving owner2, String string) {
		if (owner != null)
			((EntityPlayerMP) owner).sendChatToPlayer(string);
		if (owner2 != null)
			((EntityPlayerMP) owner2).sendChatToPlayer(string);

	}

	public static void sendBattleMessage(EntityLiving owner, EntityLiving owner2, String string) {
		sendBattleMessage(owner, string);
		sendBattleMessage(owner2, string);
	}

	public static void sendBattleMessage(EntityLiving user, String string) {
		if (user!=null){
			((EntityPlayerMP)user).serverForThisPlayer.sendPacketToPlayer(new BattleMessagePacket(string));
		}		
	}

}
