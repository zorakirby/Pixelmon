package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class RazorWind extends MultiTurnSpecialAttackBase {

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (!doesPersist(user)) {
			setPersists(user, true);
			setTurnCount(user, 2);
		}
		decrementTurnCount(user);
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " whips up a strong wind!");
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
