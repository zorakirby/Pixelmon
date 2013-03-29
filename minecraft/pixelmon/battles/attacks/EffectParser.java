package pixelmon.battles.attacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.specialAttacks.ChanceModifier;
import pixelmon.battles.attacks.specialAttacks.ModifierType;
import pixelmon.battles.attacks.specialAttacks.RemoveEffect;
import pixelmon.battles.attacks.specialAttacks.StatsEffect;
import pixelmon.battles.status.StatusType;
import pixelmon.entities.pixelmon.stats.StatsType;

public class EffectParser {

	private String effectTypeString;
	private String effectValueString;

	private Value[] values = new Value[2];
	private String ValueString;

	private boolean hasModifier;
	private ArrayList<ModifierStoreClass> modifierStore = new ArrayList<ModifierStoreClass>();

	public EffectBase ParseEffect(String effect) {
		if (effect.equalsIgnoreCase("None")) {
			return null;
		}
		String[] splits = effect.split(":");
		String[] effectSplits = splits[0].split("=");
		effectTypeString = effectSplits[0];
		if (effectSplits.length > 1)
			effectValueString = effectSplits[1];

		if (splits.length > 1) {
			hasModifier = true;
			String[] modifierStrings = splits[1].split(":");
			for (String s : modifierStrings) {
				ModifierStoreClass m = new ModifierStoreClass();
				String[] modifierSplits = s.split("=");
				m.modifierTypeString = modifierSplits[0];
				if (modifierSplits.length > 1)
					m.modifierValueString = modifierSplits[1];
				else
					m.modifierValueType = ValueType.None;
				modifierStore.add(m);
			}

		} else
			hasModifier = false;

		calcEffectType();

		calcValue();

		return getEffect();
	}

	private EffectBase getEffect() {
		EffectBase effect = null;

		if (StatsType.isStatsEffect(effectTypeString))
			effect = new StatsEffect(StatsType.getStatsEffect(effectTypeString), values[0].value, modifierStoreContains(ModifierType.User));
		else if (effectTypeString.equalsIgnoreCase("remove"))
			effect = new RemoveEffect(StatusType.getStatusEffect(ValueString));
		else
			effect = EffectRegistry.getEffect(effectTypeString, values);

		// if (SpecialAttackType.getSpecialAttackType(effectTypeString) ==
		// SpecialAttackType.MilkDrink)
		// effect = new Restore(1);

		if (hasModifier) {
			for (ModifierStoreClass m : modifierStore) {
				if (m.effectModifier == ModifierType.Chance)
					effect.AddModifier(new ChanceModifier(m.modifierValue));
			}
		}
		return effect;
	}

	private boolean modifierStoreContains(ModifierType user) {
		for (ModifierStoreClass m : modifierStore)
			if (m.effectModifier == ModifierType.User)
				return true;
		return false;
	}

	private void calcEffectType() {
		if (hasModifier)
			for (ModifierStoreClass m : modifierStore)
				m.effectModifier = ModifierType.getModifierType(m.modifierTypeString);
	}

	private void calcValue() {
		if (effectValueString != null) {
			ValueType valueType;
			int value1 = 0, value2 = 0;
			if (effectValueString.endsWith("%")) {
				valueType = ValueType.Percent;
				effectValueString = effectValueString.replace("%", "");
			} else if (effectValueString.contains("to")) {
				valueType = ValueType.Range;
				int toInd = effectValueString.indexOf("to");
				value2 = Integer.parseInt(effectValueString.substring(toInd + 2));
				effectValueString = effectValueString.substring(0, toInd);
			} else
				valueType = ValueType.WholeNumber;
			try {
				value1 = Integer.parseInt(effectValueString);
			} catch (Exception e) {
			} finally {
				ValueString = effectValueString;
			}
			values[0] = new Value(value1, valueType);
			values[1] = new Value(value2, null);
		}
		for (ModifierStoreClass m : modifierStore) {
			if (m.modifierValueString != null) {
				if (m.modifierValueString.endsWith("%")) {
					m.modifierValueType = ValueType.Percent;
					m.modifierValueString = m.modifierValueString.replace("%", "");
				} else
					m.modifierValueType = ValueType.WholeNumber;
				m.modifierValue = Integer.parseInt(m.modifierValueString);
			}
		}
	}

	public class ModifierStoreClass {
		public ModifierType effectModifier;
		public String modifierTypeString;
		public String modifierValueString;
		public ValueType modifierValueType;
		public int modifierValue;

	}
}
