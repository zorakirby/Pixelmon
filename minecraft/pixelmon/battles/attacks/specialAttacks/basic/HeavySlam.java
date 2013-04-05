package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class HeavySlam extends SpecialAttackBase {

	public HeavySlam() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {

		int i = (int) (DatabaseStats.getWeight(target.getNickname()) / DatabaseStats.getWeight(user.getNickname()));

		if (i <= 20)
			a.baseAttack.basePower = 120;
		else if (i > 20 && i <= 25)
			a.baseAttack.basePower = 100;
		else if (i > 25 && i <= (100 / 3))
			a.baseAttack.basePower = 80;
		else if (i > (100 / 3) && i >= 50)
			a.baseAttack.basePower = 60;
		else if (i > 50)
			a.baseAttack.basePower = 40;

		return false;
	}

}
