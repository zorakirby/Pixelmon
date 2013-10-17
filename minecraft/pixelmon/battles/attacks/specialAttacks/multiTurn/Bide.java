package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Bide extends MultiTurnSpecialAttackBase {

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		float endHealth;
		if (!doesPersist(user)) {
			setPersists(user, true);
			setTurnCount(user, 3);
		}
		decrementTurnCount(user);
		if (getTurnCount(user) == 2) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " bided its time!");
			user.battleVariables.set("bidehealth", (int)user.func_110143_aJ());
		}
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is storing energy!");
		}

		if (getTurnCount(user) == 0) {
			endHealth = user.func_110143_aJ();
			setPersists(user, false);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " unleashed its energy!");
			target.attackEntityFrom(DamageSource.causeMobDamage(user), ((user.battleVariables.get("bidehealth") - endHealth) * 2));

			if (user.battleVariables.get("bidehealth") - endHealth == 0)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + "'s attack failed!");
		}

		return true;
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		return getTurnCount(user) < 2;
	}

}
