package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class Venoshock extends SpecialAttackBase {

	public Venoshock() {
		super(SpecialAttackType.Venoshock, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		a.basePower = 65;
		boolean isPoisoned=false;
		for(StatusEffectBase e:target.getStatus())
			if (e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly) isPoisoned = true;

		if (isPoisoned) a.basePower=130;
		return false;
	}

}
