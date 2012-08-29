package pixelmon.battles.attacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class PriorityAttackModifier extends AttackModifierBase {

	public PriorityAttackModifier(int value) {
		super(AttackModifierType.Priority, ApplyStage.Priority, false);
		this.value = value;
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		return false;
	}

}
