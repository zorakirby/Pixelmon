package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Psywave extends SpecialAttackBase {

	public Psywave(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		RandomHelper generator = new RandomHelper();
		target.attackEntityFrom(DamageSource.causeMobDamage(user), (generator.getRandomNumberBetween(1, 10) + 5) * (user.getLvl().getLevel() / 10));

		return true;
	}

}
