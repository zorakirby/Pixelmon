package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.attacks.EffectBase.ApplyStage;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FalseSwipe extends SpecialAttackBase {
	
	public FalseSwipe() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		int check = a.doDamageCalc(user, target, a.calcCriticalHit(getEffect("FalseSwipe")));
		if(check >= target.getHealth())
		{
			target.attackEntityFrom(DamageSource.causeMobDamage(user), (target.getHealth()-1));
		}
		else
			target.attackEntityFrom(DamageSource.causeMobDamage(user), check);	
		return true;
	}

}
