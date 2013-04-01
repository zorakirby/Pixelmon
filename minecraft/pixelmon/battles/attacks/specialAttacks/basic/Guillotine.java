package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Guillotine extends SpecialAttackBase {

	public Guillotine(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
		target.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
		return true;
	}

	public boolean hasSpecialAccuracyEffect() throws Exception {
		return true;
	}

	public double getAccuracy(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (target.getLvl().getLevel() > user.getLvl().getLevel())
			return 0;

		return (user.getLvl().getLevel() - target.getLvl().getLevel()) + 30;
	}
}
