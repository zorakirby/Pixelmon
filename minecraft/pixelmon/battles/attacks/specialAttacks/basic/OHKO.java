package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusType;
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
			target.attackEntityFrom(DamageSource.causeMobDamage(user), target.func_110143_aJ());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
		}
			
		int chance;
		if (target.getLvl().getLevel() > user.getLvl().getLevel())
			chance = 0;
			
		else 
			chance = user.getLvl().getLevel() - target.getLvl().getLevel() + 30;
			
		Random rand = new Random();
		int x = rand.nextInt(100)+1;
		
		if(x < chance){
		target.attackEntityFrom(DamageSource.causeMobDamage(user), target.func_110143_aJ());
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
		}
		
		else if (x > chance)
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The move failed!");
		
		return false;
	}

}
