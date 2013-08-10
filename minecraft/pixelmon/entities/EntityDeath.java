package pixelmon.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;

public class EntityDeath {
	@ForgeSubscribe
	public void onPlayerDeath(LivingDeathEvent event) {
		if(event.entity instanceof EntityPlayerMP) {
			((EntityPlayerMP) event.entity).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.PlayerDeath));
		}
	}
}
