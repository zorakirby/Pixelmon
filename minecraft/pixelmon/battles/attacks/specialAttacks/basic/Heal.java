package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Heal extends SpecialAttackBase {
	int increment = 0;
	public Heal(Value... values) {
		super(ApplyStage.During, false);
		increment = values[0].value;

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {

		double healAmount = (double)increment/100*(double)user.getMaxHealth();
		user.heal((int)Math.ceil(healAmount));
	
		return false;
	}

}
