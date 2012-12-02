package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class Venoshock extends SpecialAttackBase {

	public Venoshock() {
		super(SpecialAttackType.Venoshock, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		a.baseAttack.basePower = 65;
		boolean isPoisoned = false;
		for (StatusEffectBase e : target.status)
			if (e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly)
				isPoisoned = true;

		if (isPoisoned)
			a.baseAttack.basePower = 130;
		return false;
	}

}
