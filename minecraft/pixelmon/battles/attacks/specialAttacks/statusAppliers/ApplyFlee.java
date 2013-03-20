package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyFlee extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		target.aggression = Aggression.passive;
		user.aggression = Aggression.passive;

		target.battleController.endBattleWithoutXP();
	}
}
