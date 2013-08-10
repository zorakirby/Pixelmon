package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase.ApplyStage;
import pixelmon.battles.status.Transformed;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Transform extends SpecialAttackBase {

	public Transform() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		for (int i = 0; i < user.status.size(); i++) {
			if (user.status.get(i) instanceof Transformed)
				user.status.remove(i);
		}
		user.status.add(new Transformed(user, target));
		((EntityPlayerMP) user.getOwner()).playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(user, EnumPackets.UpdateStorage).getPacket());
		return true;
	}

}
