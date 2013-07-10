package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PainSplit extends SpecialAttackBase {

	public PainSplit() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {

		float newHPBase = (user.func_110143_aJ() + target.func_110143_aJ()) / 2;
		if (user.func_110143_aJ() < newHPBase) {
			user.heal((newHPBase - user.func_110143_aJ()));
			user.updateHealth();
		} else if (user.func_110143_aJ() > newHPBase) {
			user.attackEntityFrom(DamageSource.causeMobDamage(user), (user.func_110143_aJ() - newHPBase));
			user.updateHealth();
		}

		if (newHPBase > target.func_110143_aJ()) {
			target.attackEntityFrom(DamageSource.causeMobDamage(user), (user.getMaxHealth() - user.func_110143_aJ()));
			target.updateHealth();
		} else if (target.func_110143_aJ() > newHPBase) {
			target.heal(target.func_110143_aJ() - newHPBase);
			target.updateHealth();
		}

		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The battlers shared their pain!");

		return false;
	}

}
