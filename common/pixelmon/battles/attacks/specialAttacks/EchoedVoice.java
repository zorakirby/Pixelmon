package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EchoedVoice extends SpecialAttackBase {

	public EchoedVoice() {
		super(SpecialAttackType.EchoedVoice, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		int power = 40;
		for (int i = attackList.size() - 2; i >= 0; i--) {
			if (attackList.get(i) == a.baseAttack.attackName)
				power += 40;
			else
				break;
		}
		a.baseAttack.basePower = power;
		return false;
	}

}
