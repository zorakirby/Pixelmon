package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class SeismicToss extends SpecialAttackBase {

	public SeismicToss(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		target.attackEntityFrom(DamageSource.causeMobDamage(user), user.getLvl().getLevel());
		return true;
	}

}
