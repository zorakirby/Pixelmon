package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Acrobatics extends SpecialAttackBase {

	public Acrobatics(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception{

		if (user.getHeldItem() == null)
			a.baseAttack.basePower = 110;

		a.baseAttack.basePower = 55;

		return false;
	}

}
