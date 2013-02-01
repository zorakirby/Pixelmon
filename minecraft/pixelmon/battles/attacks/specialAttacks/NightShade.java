package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class NightShade extends SpecialAttackBase {

	public NightShade() {
		super(SpecialAttackType.NightShade, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception{
		target.attackEntityFrom(DamageSource.causeMobDamage(user), user.getLvl().getLevel());
		return true;
	}

}
