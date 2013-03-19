package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class SolarBeam extends MultiTurnSpecialAttackBase {
	
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (!doesPersist(user)) {
			setPersists(user, true);
			setTurnCount(user, 2);
		}
		decrementTurnCount(user);
		for (StatusBase e : user.status)
			if (e.type == StatusType.Sunny)
				decrementTurnCount(user);
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is storing energy!");
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
