package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import net.minecraft.src.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Bide extends MultiTurnSpecialAttackBase {

	public Bide() {
		super(MultiTurnSpecialAttackType.Bide, 3);

	}
	int initHealth;

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {

		int endHealth;
		if (!persists) {
			persists = true;
			turnCounter = 0;
			
		}
		turnCounter++;
		if (turnCounter == 1) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " bided its time!");
			initHealth = user.getHealth();
		}
		if (turnCounter == 2) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is storing energy!");
		}

		if (turnCounter == 3) {
			endHealth = user.getHealth();
			persists = false;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " unleashed its energy!");
			target.attackEntityFrom(DamageSource.causeMobDamage(user), ((initHealth - endHealth) * 2));
			System.out.println((initHealth - endHealth) * 2);
			System.out.println("Initial health value is " + initHealth + ". The end health value is " + endHealth);
			
			if (initHealth - endHealth == 0)
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + "'s attack failed!");
		}

		return true;
	}

	@Override
	public boolean cantMiss() {
		return turnCounter < 2;
	}

}