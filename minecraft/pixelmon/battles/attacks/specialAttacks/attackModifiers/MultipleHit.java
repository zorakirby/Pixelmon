package pixelmon.battles.attacks.specialAttacks.attackModifiers;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class MultipleHit extends AttackModifierBase {

	private int count = 0;

	public MultipleHit(Value... values) {
		super(ApplyStage.During, false);
		value = values[0].value;
		this.value2 = values[1].value;
	}

	public boolean RepeatsAttack() {
		if (value2 == 0) {
			if (count >= value)
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
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		count = 0;
		while (RepeatsAttack()) {
			double crit = a.calcCriticalHit(null);
			for (EffectBase e : a.baseAttack.effects)
				crit = a.calcCriticalHit(e);

			int power = a.doDamageCalc(user, target, crit);
			if (a.baseAttack.attackCategory == Attack.ATTACK_STATUS)
				power = 0;
			target.doBattleDamage(user, power);
			a.doMove(user, target);
			if (crit > 1)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Critical Hit!");
		}
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " attacked " + count + " times!");

		return true;
	}

}
