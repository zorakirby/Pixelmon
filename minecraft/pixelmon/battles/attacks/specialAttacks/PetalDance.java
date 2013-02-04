package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.Confusion;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PetalDance extends MultiTurnSpecialAttackBase {

	public PetalDance() {
		super(MultiTurnSpecialAttackType.PetalDance, (new Random()).nextInt(2) + 2);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (!doesPersist(user)) {
			setPersists(user, true);
			initTurnCount(user);
		}
		incrementTurnCount(user);
		if (getTurnCount(user) == new Random().nextInt(2) + 2) {
			setPersists(user, false);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " became confused!");
			user.status.add(new Confusion());
		}
		return false;
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {

		return false;
	}

}
