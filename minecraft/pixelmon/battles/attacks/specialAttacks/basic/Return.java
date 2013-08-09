package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Return extends SpecialAttackBase {

	public Return() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		a.baseAttack.basePower = (int) (user.friendship.getFriendship() / 2.5);
		if (a.baseAttack.basePower < 1){
			a.baseAttack.basePower = 1;
		} else if (a.baseAttack.basePower > 102){
			a.baseAttack.basePower = 102;
		}
		return false;
	}

}
