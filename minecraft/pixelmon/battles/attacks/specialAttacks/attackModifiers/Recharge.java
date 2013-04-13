package pixelmon.battles.attacks.specialAttacks.attackModifiers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.specialAttacks.multiTurn.MultiTurnSpecialAttackBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Recharge extends MultiTurnSpecialAttackBase {

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		
			if(!doesPersist(user)){
				setPersists(user, true);
				
			}
		return false;
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {

		return false;
	}

}
