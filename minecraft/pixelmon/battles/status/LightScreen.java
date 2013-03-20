package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class LightScreen extends StatusBase {
	int effectTurns = 0;

	public LightScreen() {
		super(StatusType.LightScreen, false, false, true);
		effectTurns = 5;
	}

	@Override
	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) throws Exception {
		if (a.baseAttack.attackCategory == Attack.ATTACK_SPECIAL)
			return damage / 2;
		return damage;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (user.battleVariables.get(type) == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + "'s Lightscreen wears off!");
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);
	}
}
