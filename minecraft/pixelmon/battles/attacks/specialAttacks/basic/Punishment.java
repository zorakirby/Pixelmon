package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Punishment extends SpecialAttackBase {

	public Punishment(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		int DamageMultiplier = (int) (user.battleStats.getSum());

		a.baseAttack.basePower = 60 + (20 * DamageMultiplier);
		if (a.baseAttack.basePower >= 200) {
			a.baseAttack.basePower = 200;
		}

		return false;
	}

}
