package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Rollout extends MultiTurnSpecialAttackBase {
	boolean hitsTarget;
	int timesHit = 0;
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			System.out.println("Runs class");
			if(getTurnCount(user) == 5)
			{
				setPersists(user, false);
				return true;
			}
			if(!doesPersist(user))
			{
					setTurnCount(user, 5);
					setPersists(user, true);
			}
			else
			{
				if(timesHit == getTurnCount(user) && 
						(a.baseAttack.accuracy/100)* (user.battleStats.getAccuracy()/target.battleStats.getEvasion())
						< RandomHelper.getRandomNumberBetween(1, 100))
				{
					a.baseAttack.basePower = (int) (30 * Math.pow(2, getTurnCount(user)));
					timesHit++;
					System.out.println(a.baseAttack.basePower);
					hitsTarget = true;
				}
				else
				{
					setPersists(user, false);
					return true;
				}
					
			}
			
		
		
		return false;
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		if(hitsTarget)
			return true;
		return false;
	}

}
