package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;


public class Drain extends SpecialAttackBase {
	int drainPercent;
	public Drain(Value... values) {
		
		super(ApplyStage.During, false);
		drainPercent = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			int restoration;
			int damage = a.doDamageCalc(user, target, crit);
			if(damage >= target.getHealth())
			{
				//restoration = target.getHealth()*(drainPercent/100);
				restoration = target.getHealth()/2;
			}
			else
				//restoration = damage*(drainPercent/100);
				restoration = damage/2;
			
			user.heal(restoration);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " regained energy!");
			return false;
	}

}
