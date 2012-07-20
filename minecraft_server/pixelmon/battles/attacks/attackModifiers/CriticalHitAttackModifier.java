package pixelmon.battles.attacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class CriticalHitAttackModifier extends AttackModifierBase {

	public CriticalHitAttackModifier(int value) {
		super(AttackModifierType.CriticalHit, ApplyStage.During, false);
		this.value = value;
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		return false;
	}

}
