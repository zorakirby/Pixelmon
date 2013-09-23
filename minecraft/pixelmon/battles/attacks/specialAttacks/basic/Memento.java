package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Memento extends SpecialAttackBase {

	public Memento() {
		super(ApplyStage.During, false);
		
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			user.doBattleDamage(user, (int)(Math.floor(user.func_110143_aJ())));
			target.battleStats.DecreaseAttack(2);
			target.battleStats.DecreaseSpecialAttack(2);
		
			return true;
	}

}
