package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Rainy;
import pixelmon.battles.status.Sunny;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Thunder extends SpecialAttackBase {

	public Thunder() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		
			if (user.battleController.globalStatusController.getWeather() instanceof Rainy)
				a.cantMiss(user);
			else if (user.battleController.globalStatusController.getWeather() instanceof Sunny)
				a.moveAccuracy = 50;
//			if (target.hasStatus(StatusType.Bouncing))
//				a.baseAttack.basePower *= 2;
			return false;
	}

	
}
