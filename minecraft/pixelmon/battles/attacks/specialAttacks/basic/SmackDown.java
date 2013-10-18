package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.SmackedDown;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class SmackDown extends SpecialAttackBase {

	public SmackDown() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		target.status.add(new SmackedDown());
		if (target.hasStatus(StatusType.Flying)) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " got knocked out of the sky!");
			target.removeStatus(StatusType.Flying);
		}
		return false;
	}

}
