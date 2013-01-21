package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class MultiTurnSpecialAttackBase extends EffectBase {
	public int turnCount = 2;

	public int getTurnCount(EntityPixelmon user) {
		return user.battleVariables.get(mtsatype);
	}

	public void incrementTurnCount(EntityPixelmon user) {
		user.battleVariables.increment(mtsatype);
	}

	public void initTurnCount(EntityPixelmon user) {
		user.battleVariables.set(mtsatype, 0);
	}

	@Override
	public boolean doesPersist(EntityPixelmon user) {
		return user.battleVariables.getBoolean(mtsatype);
	}

	protected void setPersists(EntityPixelmon user, boolean value) {
		user.battleVariables.setBoolean(mtsatype, value);
	}

	private MultiTurnSpecialAttackType mtsatype;

	public MultiTurnSpecialAttackBase(MultiTurnSpecialAttackType type, int turnCount) {
		super(EffectType.MultiTurnSpecialAttack, ApplyStage.During, true);
		this.turnCount = turnCount;
		this.mtsatype = type;
	}

	public abstract boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList);

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
	}

	public abstract boolean cantMiss(EntityPixelmon user);
}
