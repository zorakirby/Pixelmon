package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.Confusion;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PetalDance extends MultiTurnSpecialAttackBase {

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (!doesPersist(user)) {
			setPersists(user, true);
			setTurnCount(user, (new Random()).nextInt(2) + 2);
		}
		if (getTurnCount(user) == 0) {
			setPersists(user, false);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " became confused!");
			user.status.add(new Confusion());
		}
		decrementTurnCount(user);
		return false;
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {

		return false;
	}

}
