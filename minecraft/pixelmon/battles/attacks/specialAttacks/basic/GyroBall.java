package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class GyroBall extends SpecialAttackBase {

	public GyroBall() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		a.baseAttack.basePower = (25 * (target.stats.Speed / user.stats.Speed));
		if(a.baseAttack.basePower > 150){
			a.baseAttack.basePower = 150;
		}
		return false;
	}

}
