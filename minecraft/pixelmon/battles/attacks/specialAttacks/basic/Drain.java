package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;


public class Drain extends SpecialAttackBase {
	int drainPercent;
	public Drain(Value... values) {
		
		super(ApplyStage.During, false);
		drainPercent = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
