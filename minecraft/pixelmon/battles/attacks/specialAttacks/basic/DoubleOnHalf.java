package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class DoubleOnHalf extends SpecialAttackBase {

	public DoubleOnHalf() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			if (target.func_110143_aJ() < target.getMaxHealth()/2)
				a.movePower *= 2;
		return false;
	}

}
