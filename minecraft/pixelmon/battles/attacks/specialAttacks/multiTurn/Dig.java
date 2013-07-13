package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.Flying;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.battles.status.UnderGround;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Dig extends MultiTurnSpecialAttackBase {

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		if (!doesPersist(user)) {
			setPersists(user, true);
			setTurnCount(user, 2);
		}
		decrementTurnCount(user);
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " digs a hole!");
			user.status.add(new UnderGround());
			return true;
		} else {
			user.removeStatus(StatusType.UnderGround);
			setPersists(user, false);
			return false;
		}
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		if (getTurnCount(user) == 0)
			return true;
		return false;
	}

}
