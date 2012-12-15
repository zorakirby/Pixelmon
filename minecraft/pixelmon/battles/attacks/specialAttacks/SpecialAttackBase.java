package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class SpecialAttackBase extends EffectBase{
	public SpecialAttackType type;
	public int value = -1;
	public int value2 = -1;
	
	public SpecialAttackBase(SpecialAttackType type, ApplyStage a, boolean persists){
		super(EffectType.SpecialAttack, a, persists);
		this.type = type;
	}

	public abstract boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList);
	
	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
	}
	
	public boolean cantMiss() {
		return false;
	}
}
