package pixelmon.battles.attacks.attackModifiers;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectParser;
import pixelmon.battles.attacks.attackEffects.EffectParser.ValueType;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class DamageAttackModifier extends AttackModifierBase {

	EffectParser.ValueType type;
	public DamageAttackModifier(int value, EffectParser.ValueType type) {
		super(AttackModifierType.Damage, ApplyStage.During , false);
		this.type=type;
		this.value = value;
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		double dmg;
		if (type == ValueType.Percent)
			dmg = ((double)target.getMaxHealth())*((double)value)/100;
		else
			dmg = value;
		target.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), (int)dmg);
		a.doMove((EntityLiving)user.getEntity(), (EntityLiving)target.getEntity());
		return true;
	}

}
