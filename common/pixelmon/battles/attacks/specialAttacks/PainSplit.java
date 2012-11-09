package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import net.minecraft.src.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PainSplit extends SpecialAttackBase {

	public PainSplit() {
		super(SpecialAttackType.PainSplit, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) {
		
		
		int newHPBase = (user.getHealth() + target.getHealth()) / 2;
		if (user.getHealth() < newHPBase) {
			user.heal((newHPBase - user.getHealth()));
			user.updateHealth();
		}
		else if (user.getHealth() > newHPBase) {
			user.attackEntityFrom(DamageSource.causeMobDamage(user), (user.getHealth()-newHPBase));
			user.updateHealth();
		}

		if (newHPBase > target.getHealth())  {
			target.attackEntityFrom(DamageSource.causeMobDamage(user), (user.getMaxHealth()-user.getHealth()));
			target.updateHealth();		
		}
		else if (target.getHealth() > newHPBase){
			target.heal(target.getHealth() - newHPBase);
			target.updateHealth();
		}
		
		ChatHandler.sendBattleMessage(user.getOwner(),target.getOwner(),
				"The battlers shared their pain!");
		
		return false;
	}

}
