package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Punishment extends SpecialAttackBase {

	public Punishment() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		int DamageMultiplier = (int) (user.battleStats.getSum());

		a.movePower = 60 + (20 * DamageMultiplier);
		if (a.movePower >= 200) {
			a.movePower = 200;
		}

		return false;
	}

}
