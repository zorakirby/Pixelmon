package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Return extends SpecialAttackBase {

	public Return() {
		super(SpecialAttackType.Return, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		a.baseAttack.basePower = (int) (user.friendship.friendship / 2.5);
		return false;
	}

}
