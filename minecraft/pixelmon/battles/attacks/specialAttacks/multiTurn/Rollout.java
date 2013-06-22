package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Rollout extends MultiTurnSpecialAttackBase {
	boolean hitsTarget;
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			System.out.println("Runs class");
			if(!doesPersist(user))
			{								// Applies the beginning of the attack
					setTurnCount(user, 5);
					setPersists(user, true);
					setHitCount(user);
			}
			if(getTurnCount(user) == 0)
			{
				System.out.println("Turncount is 0");
				setPersists(user, false);
				return true;
			}
			else
			{
				int randomNumber = RandomHelper.getRandomNumberBetween(1, 100);
				double ratioShit = a.baseAttack.accuracy * (user.battleStats.getAccuracy()/target.battleStats.getEvasion());
				if((getHitCount(user) == 5 - getTurnCount(user)) && 
						ratioShit
						>= randomNumber)
				{
					a.baseAttack.basePower = (int) (30 * Math.pow(2, 5 - getTurnCount(user)));
					//if(attackList.contains(DatabaseMoves.getAttack("Defense Curl")))
						
					
					hitsTarget = true;
					System.out.println((double)(a.baseAttack.accuracy/100)*(user.battleStats.getAccuracy()/target.battleStats.getEvasion()));
					System.out.println(randomNumber);
					System.out.println(ratioShit);
					decrementTurnCount(user);
					incrementHitCount(user);
					return false;
				}
				else
				{
					System.out.println(getHitCount(user) + " " + getTurnCount(user) + " " + a.baseAttack.accuracy);
					setPersists(user, false);
					return true;
				}
					
			}
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		if(hitsTarget)
			return true;
		return false;
	}

}
