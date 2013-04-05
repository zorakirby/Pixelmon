package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EchoedVoice extends SpecialAttackBase {

	public EchoedVoice() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		int power = 40;
		for (int i = attackList.size() - 2; i >= 0; i--) {
			if (attackList.get(i).equalsIgnoreCase(a.baseAttack.attackName))
				power += 40;
			else
				break;
		}
		a.baseAttack.basePower = power;
		return false;
	}

}
