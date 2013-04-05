package pixelmon.battles.attacks.specialAttacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AlwaysHit extends AttackModifierBase {

	public AlwaysHit() {
		super(ApplyStage.Start, false);
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
