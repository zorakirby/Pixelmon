package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Flee extends StatusEffectBase {

	public Flee() {
		super(StatusEffectType.Flee, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		

		target.aggression = Aggression.passive;
		user.aggression = Aggression.passive;

		target.battleController.endBattleWithoutXP();
		

	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		return false;
	}
}
