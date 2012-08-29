package pixelmon.battles.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackModifiers.ChanceModifier;
import pixelmon.battles.attacks.attackModifiers.ModifierBase;
import pixelmon.battles.attacks.attackModifiers.ModifierType;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;


public abstract class EffectBase {

	public EffectType effectType;

	public ApplyStage applyStage;
	public ArrayList<ModifierBase> modifiers = new ArrayList<ModifierBase>();
	public boolean persists;
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

	public abstract void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList);

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

	public abstract boolean cantMiss();
	
	public static EffectBase getEffect(String e) {
		EffectParser p = new EffectParser();
		return p.ParseEffect(e);
	}
	
	public boolean doesPersist(){
		return persists;
	}

	public boolean hasSpecialAccuracyEffect() {
		return false;
	}

	public double getAccuracy(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		return 100;
	}

	public void ApplyMissEffect(PixelmonEntityHelper user, PixelmonEntityHelper target) {		
	}
}
