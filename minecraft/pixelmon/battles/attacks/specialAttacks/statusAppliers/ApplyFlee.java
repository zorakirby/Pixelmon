package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyFlee extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		
		if(!target.hasOwner() || !user.hasOwner()){
			target.aggression = Aggression.passive;
			user.aggression = Aggression.passive;
			target.battleController.endBattleWithoutXP();
		}
	}
}
