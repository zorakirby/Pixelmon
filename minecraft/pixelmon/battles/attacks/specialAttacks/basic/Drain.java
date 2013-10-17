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
			double restoration;
			int damage = a.doDamageCalc(user, target, crit);
			if(damage >= target.func_110143_aJ())
			{
				restoration = (double)target.func_110143_aJ()*((double)(double)drainPercent/100);
			}
			else
				restoration = (double)(damage*((double)(double)drainPercent/100));
				
			
			user.heal((int)Math.ceil(restoration));
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " regained energy!");
			return false;
	}

}
