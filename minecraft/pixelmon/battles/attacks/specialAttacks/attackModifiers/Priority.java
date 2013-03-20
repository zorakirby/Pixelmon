package pixelmon.battles.attacks.specialAttacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Priority extends AttackModifierBase {

	public Priority(Value... values) {
		super(ApplyStage.Priority, false);
		this.value = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) {
		return false;
	}

}
