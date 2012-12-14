package pixelmon.battles.attacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class MultipleHitAttackModifier extends AttackModifierBase {

	private int count = 0;

	public MultipleHitAttackModifier(int value1, int value2) {
		super(AttackModifierType.MultipleHit, ApplyStage.During, false);
		value = value1;
		this.value2 = value2;
	}

	public boolean RepeatsAttack() {
		if (value2 == -1) {
			if (count > value)
				return false;
			count++;
			return true;
		} else {
			if (count > value2 - value)
				return false;
			count++;
			return true;
		}
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) {
		while (RepeatsAttack()) {
			double crit = a.calcCriticalHit(null);
			for (EffectBase e : a.baseAttack.effects)
				crit = a.calcCriticalHit(e);

			int power = a.doDamageCalc(user, target, crit);
			if (a.baseAttack.attackCategory == Attack.ATTACK_STATUS)
				power = 0;
			target.attackEntityFrom(DamageSource.causeMobDamage(user), power);
			a.doMove(user, target);
			if (crit > 1)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Critical Hit!");
		}
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " attacked " + count + " times!");
		return true;
	}

}
