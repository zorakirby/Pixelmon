package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;

public class SmackedDown extends StatusEffectBase {

	public SmackedDown() {
		super(StatusEffectType.SmackedDown, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {}
	
	@Override
	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		double stab = 1;
		if (a.STAB)
			stab = 1.5;
		double type = EnumType.getTotalEffectiveness(user.getType(), a.attackType);
		if (a.attackType == EnumType.Ground && user.getType().contains(EnumType.Flying))
			type = 1;
		double critical = crit;
		double rand = ((double) RandomHelper.getRandomNumberBetween(85, 100)) / 100;
		double modifier = stab * type * critical * rand;
		double attack = 0, defence = 0;
		if (a.attackCategory == Attack.ATTACK_PHYSICAL) {
			attack = ((double) target.stats.Attack)
					* ((double) target.battleStats.AttackModifier) / 100;
			defence = ((double) user.stats.Defence)
					* ((double) user.battleStats.DefenceModifier) / 100;
		} else if (a.attackCategory == Attack.ATTACK_SPECIAL) {
			attack = ((double) target.stats.SpecialAttack)
					* ((double) target.battleStats.SpecialAttackModifier)
					/ 100;
			defence = ((double) user.stats.SpecialDefence)
					* ((double) user.battleStats.SpecialDefenceModifier)
					/ 100;
		}
		double Damage = ((2 * ((float)target.getLvl().getLevel()) + 10) / 250
				* (attack / defence) * a.basePower + 2)
				* modifier;

		return Damage;
	}
}
