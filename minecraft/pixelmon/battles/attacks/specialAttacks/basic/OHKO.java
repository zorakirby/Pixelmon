package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class OHKO extends SpecialAttackBase {

	public OHKO() {
		super(ApplyStage.During, false);
	}
	
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		
		int chance;
		if (target.getLvl().getLevel() > user.getLvl().getLevel())
			chance = 0;
			
		else 
			chance = user.getLvl().getLevel() - target.getLvl().getLevel() + 30;
			
		Random rand = new Random();
		int x = rand.nextInt(100)+1;
		
		//System.out.println(x);
		//System.out.println(chance);
		
		if(x < chance){
		target.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
		}
		
		else if (x > chance)
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The move failed!");
		
		return false;
	}

}
