package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class BellyDrum extends SpecialAttackBase {

	public BellyDrum() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			if (user.func_110143_aJ() > user.getMaxHealth())
			{
			user.attackEntityFrom(DamageSource.causeMobDamage(target), user.getMaxHealth());
			while (user.battleStats.getAttackModifier() < 6)
				user.battleStats.IncreaseAttack(1);
			}
			return true;
	}

}
