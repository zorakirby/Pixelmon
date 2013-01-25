package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class RazorWind extends MultiTurnSpecialAttackBase {

	public RazorWind() {
		super(MultiTurnSpecialAttackType.RazorWind, 2);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		if (!doesPersist(user)) {
			setPersists(user, true);
			initTurnCount(user);
		}
		incrementTurnCount(user);
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " whips up a strong wind!");
			return true;
		} else {
			setPersists(user, false);
			return false;
		}

	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		if (getTurnCount(user) == 0)
			return true;
		return false;
	}

}
