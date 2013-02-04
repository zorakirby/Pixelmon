package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Bide extends MultiTurnSpecialAttackBase {

	public Bide() {
		super(MultiTurnSpecialAttackType.Bide, 3);
	}

	int initHealth;

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception{
		int endHealth;
		if (!doesPersist(user)) {
			setPersists(user, true);
			initTurnCount(user);
		}
		incrementTurnCount(user);
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " bided its time!");
			initHealth = user.getHealth();
		}
		if (getTurnCount(user) == 2) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is storing energy!");
		}

		if (getTurnCount(user) == 3) {
			endHealth = user.getHealth();
			setPersists(user, false);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " unleashed its energy!");
			target.attackEntityFrom(DamageSource.causeMobDamage(user), ((initHealth - endHealth) * 2));
			System.out.println((initHealth - endHealth) * 2);
			System.out.println("Initial health value is " + initHealth + ". The end health value is " + endHealth);

			if (initHealth - endHealth == 0)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + "'s attack failed!");
		}

		return true;
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		return getTurnCount(user) < 2;
	}

}
