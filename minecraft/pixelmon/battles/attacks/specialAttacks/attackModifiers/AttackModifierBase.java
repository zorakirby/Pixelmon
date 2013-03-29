package pixelmon.battles.attacks.specialAttacks.attackModifiers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class AttackModifierBase extends EffectBase {
	public int value = -1;
	public int value2 = -1;

	public AttackModifierBase(ApplyStage a, boolean persists, Value... values) {
		super(a, persists);
	}

	public abstract boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception;

	@Override
	public void ApplyEffect(Attack attack, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
	}

	public boolean cantMiss(EntityPixelmon user) throws Exception {
		return false;
	}
}
