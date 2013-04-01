package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Eruption extends SpecialAttackBase {

	public Eruption(Value... values) {
		super(ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		a.baseAttack.basePower = 150 * (user.getHealth() / user.getMaxHealth());
		return false;
	}

}
