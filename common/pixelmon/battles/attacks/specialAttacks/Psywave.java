package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import net.minecraft.src.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Psywave extends SpecialAttackBase {

	public Psywave(SpecialAttackType type, ApplyStage a, boolean persists) {
		super(SpecialAttackType.Psywave, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		RandomHelper generator = new RandomHelper();
		target.attackEntityFrom(DamageSource.causeMobDamage(user), (generator.getRandomNumberBetween(1, 10)+ 5) 
				* (user.getLvl().getLevel() / 10));
		
		
				
		return true;
	}

}
