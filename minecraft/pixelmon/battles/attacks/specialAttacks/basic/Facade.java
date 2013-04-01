package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Facade extends SpecialAttackBase {

	public Facade(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		a.baseAttack.basePower = 70;
		for (StatusBase e : target.status) {
			if (e.type == StatusType.Burn || e.type == StatusType.Poison || e.type == StatusType.PoisonBadly || e.type == StatusType.Paralysis)
				a.baseAttack.basePower = 140;
		}
		return false;
	}
}
