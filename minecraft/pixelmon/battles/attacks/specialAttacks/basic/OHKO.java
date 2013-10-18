package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.battles.status.Substitute;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class OHKO extends SpecialAttackBase {

	public OHKO() {
		super(ApplyStage.During, false);
	}
	
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (a.baseAttack.attackName.equals("Fissure") && target.hasStatus(StatusType.UnderGround))
		{
			if (!target.hasStatus(StatusType.Substitute)) {
			target.doBattleDamage(user, (int)target.getHealth());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
			}
			else
				for (StatusBase e : target.status)
					if (e instanceof Substitute)
						((Substitute)e).attackSubstitute(((Substitute)e).health, user);
		}
			
		int chance;
		if (target.getLvl().getLevel() > user.getLvl().getLevel())
			chance = 0;
			
		else 
			chance = user.getLvl().getLevel() - target.getLvl().getLevel() + 30;
			
		Random rand = new Random();
		int x = rand.nextInt(100)+1;
		
		if(x < chance)
		{
			if (!target.hasStatus(StatusType.Substitute)) {
				target.doBattleDamage(user, (int)target.getHealth());
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
			}
			else
				for (StatusBase e : target.status)
					if (e instanceof Substitute)
						((Substitute)e).attackSubstitute(((Substitute)e).health, user);
		}
		
		else if (x > chance)
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The move failed!");
		
		return false;
	}

}
