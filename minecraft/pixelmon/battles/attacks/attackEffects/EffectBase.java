package pixelmon.battles.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackModifiers.ChanceModifier;
import pixelmon.battles.attacks.attackModifiers.ModifierBase;
import pixelmon.battles.attacks.attackModifiers.ModifierType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class EffectBase {

	public EffectType effectType;

	public ApplyStage applyStage;
	public ArrayList<ModifierBase> modifiers = new ArrayList<ModifierBase>();
	private boolean persists;
	public int value = -1, value2 = -1;

	public EffectBase(EffectType effectType, ApplyStage applyStage,
			boolean persists) {
		this.applyStage = applyStage;
		this.persists = persists;
		this.effectType = effectType;
	}

	public void AddModifier(ModifierBase modifier) {
		modifiers.add(modifier);
	}

	public abstract void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList);

	public enum ApplyStage {
		Start, During, End, Priority
	}

	public boolean checkChance() {
		for (ModifierBase m : modifiers) {
			if (m.type == ModifierType.Chance) {
				return ((ChanceModifier) m).RollChance();
			}
		}
		return true;
	}

	public abstract boolean cantMiss(EntityPixelmon user);
	
	public static EffectBase getEffect(String e) {
		EffectParser p = new EffectParser();
		return p.ParseEffect(e);
	}
	
	public boolean doesPersist(EntityPixelmon user){
		return persists;
	}

	public boolean hasSpecialAccuracyEffect() {
		return false;
	}

	public double getAccuracy(EntityPixelmon user, EntityPixelmon target) {
		return 100;
	}

	public void ApplyMissEffect(EntityPixelmon user, EntityPixelmon target) {		
	}
}
