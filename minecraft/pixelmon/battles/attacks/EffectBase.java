package pixelmon.battles.attacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.specialAttacks.ChanceModifier;
import pixelmon.battles.attacks.specialAttacks.ModifierBase;
import pixelmon.battles.attacks.specialAttacks.ModifierType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class EffectBase {

	public ApplyStage applyStage;
	public ArrayList<ModifierBase> modifiers = new ArrayList<ModifierBase>();
	private boolean persists;
	public int value = -1, value2 = -1;
	public Attack parent;

	public EffectBase(ApplyStage applyStage, boolean persists, int... values) {
		this.applyStage = applyStage;
		this.persists = persists;
	}

	public void AddModifier(ModifierBase modifier) {
		modifiers.add(modifier);
	}

	public abstract void ApplyEffect(Attack a, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception;

	public enum ApplyStage {
		Start, During, End, Priority
	}

	public boolean checkChance() throws Exception {
		for (ModifierBase m : modifiers) {
			if (m.type == ModifierType.Chance) {
				return ((ChanceModifier) m).RollChance();
			}
		}
		return true;
	}

	public abstract boolean cantMiss(EntityPixelmon user) throws Exception;

	public static EffectBase getEffect(String e) {
		EffectParser p = new EffectParser();
		return p.ParseEffect(e);
	}

	public boolean doesPersist(EntityPixelmon user) throws Exception {
		return persists;
	}

	public boolean hasSpecialAccuracyEffect() throws Exception {
		return false;
	}

	public double getAccuracy(EntityPixelmon user, EntityPixelmon target) throws Exception {
		return 100;
	}

	public void ApplyMissEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
	}
}
