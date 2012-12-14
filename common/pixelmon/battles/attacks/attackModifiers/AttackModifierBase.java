package pixelmon.battles.attacks.attackModifiers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class AttackModifierBase extends EffectBase{
	public AttackModifierType type;
	public int value = -1;
	public int value2 = -1;
	
	public AttackModifierBase(AttackModifierType type, ApplyStage a, boolean persists){
		super(EffectType.AttackModifier, a, persists);
		this.type = type;
	}

	public abstract boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a);
	
	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
	}
	
	public boolean cantMiss() {
		return false;
	}
}
