package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Reversal extends SpecialAttackBase {

	public Reversal() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		int percentage = (int) (user.getMaxHealth() / user.func_110143_aJ() * 100);

		if (percentage >= 71)
			a.movePower = 20;

		else if (percentage < 71 && percentage >= 36)
			a.movePower = 40;

		else if (percentage < 36 && percentage >= 21)
			a.movePower = 80;

		else if (percentage < 21 && percentage >= 11)
			a.movePower = 100;

		else if (percentage < 11 && percentage >= 5)
			a.movePower = 150;

		else if (percentage < 5 && percentage >= 0)
			a.movePower = 200;

		return false;
	}

}
