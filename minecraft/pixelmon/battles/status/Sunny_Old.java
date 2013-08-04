package pixelmon.battles.status;
/*package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Sunny extends StatusBase {

	int effectTurns = 5;

	public Sunny() {
		super(StatusType.Sunny, false, false, true);
	}

	@Override
	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) throws Exception {
		if (a.baseAttack.attackType == EnumType.Fire)
			return damage *= 1.5;
		else if (a.baseAttack.attackType == EnumType.Water)
			return damage *= 0.5;
		else
			return damage;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (effectTurns == 0) {
			user.status.remove(this);
		}
		effectTurns--;
	}
}
*/