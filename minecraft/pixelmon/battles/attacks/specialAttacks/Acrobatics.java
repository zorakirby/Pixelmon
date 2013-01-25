package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Acrobatics extends SpecialAttackBase {

	public Acrobatics() {
		super(SpecialAttackType.Acrobatics, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {

		if (user.getHeldItem() == null)
			a.baseAttack.basePower = 110;

		a.baseAttack.basePower = 55;

		return false;
	}

}
