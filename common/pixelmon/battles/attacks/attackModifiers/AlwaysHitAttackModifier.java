package pixelmon.battles.attacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AlwaysHitAttackModifier extends AttackModifierBase {

	public AlwaysHitAttackModifier() {
		super(AttackModifierType.AlwaysHit, ApplyStage.Start, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) {
		return false;
	}
	
	public boolean hasSpecialAccuracyEffect() {
		return true;
	}

	public double getAccuracy(EntityPixelmon user, EntityPixelmon target) {
		return 100;
	}

}
