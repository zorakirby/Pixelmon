package pixelmon.battles.attacks.specialAttacks.attackModifiers;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.attacks.ValueType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Damage extends AttackModifierBase {

	ValueType type;

	public Damage(Value... values) {
		super(ApplyStage.During, false);
		this.type = values[0].type;
		this.value = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		double dmg;
		if (type == ValueType.Percent)
			dmg = ((double) target.getMaxHealth()) * ((double) value) / 100;
		else
			dmg = value;
		target.doBattleDamage(user, (int) dmg);
		a.doMove(user, target);
		return true;
	}

}
