package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FoulPlay extends SpecialAttackBase {

	public FoulPlay() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			
			double oldAttackModifier = user.battleStats.getAttackModifier();
			int oldAttack = user.stats.Attack;
			user.battleStats.setAttackModifier((int)target.battleStats.getAttackModifier());
			user.stats.Attack = target.stats.Attack;
			System.out.println(oldAttack + "  " + user.stats.Attack);
			int damage = a.doDamageCalc(user, target, crit);
			user.battleStats.setAttackModifier((int)oldAttackModifier);
			user.stats.Attack = oldAttack;
			target.attackEntityFrom(DamageSource.causeMobDamage(user), damage);
			
		return true;
	}

}
