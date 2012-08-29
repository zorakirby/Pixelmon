package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
public class Facade extends SpecialAttackBase {

	public Facade() {
		super(SpecialAttackType.Facade, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		a.basePower = 70;
		for (StatusEffectBase e :target.status){
			if (e.type == StatusEffectType.Burn || e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly || e.type == StatusEffectType.Paralysis)
				a.basePower = 140;
		}
		return false;
	}
}
