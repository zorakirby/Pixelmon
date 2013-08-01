package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class DamageIfMultiTurn extends SpecialAttackBase {
	String requiredStatus;
	public DamageIfMultiTurn(Value... values) {
		super(ApplyStage.During, false);
		requiredStatus = values[0].toString();
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		
//		if ()
		return false;
	}

}
