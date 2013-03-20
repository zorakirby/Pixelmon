package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Venoshock extends SpecialAttackBase {

	public Venoshock(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		a.baseAttack.basePower = 65;
		boolean isPoisoned = false;
		for (StatusBase e : target.status)
			if (e.type == StatusType.Poison || e.type == StatusType.PoisonBadly)
				isPoisoned = true;

		if (isPoisoned)
			a.baseAttack.basePower = 130;
		return false;
	}

}
