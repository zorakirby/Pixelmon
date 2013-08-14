package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Endeavor extends SpecialAttackBase{

	public Endeavor() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		
		float userHealth = user.func_110143_aJ();
		float targetHealth = target.func_110143_aJ();
		
		if(userHealth >= targetHealth){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "But it failed!");
		}
		
		if(userHealth < targetHealth){
			target.attackEntityFrom(DamageSource.causeMobDamage(user), target.func_110143_aJ() - user.func_110143_aJ());
		}
		
		return true;
	}

}
