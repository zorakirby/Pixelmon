package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class GyroBall extends SpecialAttackBase {

	public GyroBall() {
		super(SpecialAttackType.GyroBall, ApplyStage.During, false);

	}

	

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
	a.basePower = (25*(target.stats.Speed / user.stats.Speed));
	return true;
	}

	
	
}
