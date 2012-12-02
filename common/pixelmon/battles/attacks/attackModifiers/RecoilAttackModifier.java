package pixelmon.battles.attacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class RecoilAttackModifier extends AttackModifierBase {

	public RecoilAttackModifier(int percent) {
		super(AttackModifierType.Recoil, ApplyStage.During, false);
		this.value = percent;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) {
		double crit = a.calcCriticalHit(null);
		for (EffectBase e : a.baseAttack.effects)
			crit = a.calcCriticalHit(e);

		int power = a.doDamageCalc(user, target, crit);
		if (a.baseAttack.attackCategory == Attack.ATTACK_STATUS)
			power = 0;
		double factor = ((double) value) / 100;
		double dmg = ((double) power) * factor;
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) dmg);
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " took recoil damage!");
		return false;
	}

}
