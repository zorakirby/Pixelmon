package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Punishment extends SpecialAttackBase {

	public Punishment() {
		super(SpecialAttackType.Punishment, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		int DamageMultiplier = user.battleStats.AttackModifier + user.battleStats.DefenceModifier + user.battleStats.SpecialAttackModifier
				+ user.battleStats.SpecialDefenceModifier + user.battleStats.SpeedModifier;

		a.baseAttack.basePower = 60 + (20 * DamageMultiplier);
		if (a.baseAttack.basePower >= 200) {
			a.baseAttack.basePower = 200;
		}

		return false;
	}

}
