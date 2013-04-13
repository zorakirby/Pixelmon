package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Struggle extends SpecialAttackBase {

	public Struggle() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {			
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " struggled!");
			target.attackEntityFrom(DamageSource.causeMobDamage(user), a.doDamageCalc(user, target, crit));
			user.attackEntityFrom(DamageSource.causeMobDamage(user), user.getMaxHealth()/4);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " took recoil damage!");
		return true;
	}

}
