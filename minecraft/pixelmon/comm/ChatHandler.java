package pixelmon.comm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatMessageComponent;

public class ChatHandler {

	public static void sendChat(Entity entityLiving, String string) {
		if (entityLiving != null)
			((EntityPlayerMP) entityLiving).sendChatToPlayer(ChatMessageComponent.createFromText(string));
	}

	public static void sendChat(Entity owner, Entity owner2, String string) {
		if (owner != null)
			((EntityPlayerMP) owner).sendChatToPlayer(ChatMessageComponent.createFromText(string));
		if (owner2 != null)
			((EntityPlayerMP) owner2).sendChatToPlayer(ChatMessageComponent.createFromText(string));

	}

	public static void sendBattleMessage(Entity entity, Entity entity2, String string) {
		sendBattleMessage(entity, string);
		sendBattleMessage(entity2, string);
	}

	public static void sendBattleMessage(Entity user, String string) {
		if (user != null) {
			((EntityPlayerMP) user).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createStringPacket(EnumPackets.BattleMessage, string));
		}
	}

}
