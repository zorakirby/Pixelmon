package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class SpecialAttackBase extends EffectBase{
	public int value = -1;
	public int value2 = -1;
	
	public SpecialAttackBase(ApplyStage a, boolean persists){
		super(a, persists);
	}

	public abstract boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception;
	
	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
	}
	
	public boolean cantMiss(EntityPixelmon user) {
		return false;
	}
}
