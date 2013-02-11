package pixelmon.battles.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackModifiers.AlwaysHitAttackModifier;
import pixelmon.battles.attacks.attackModifiers.AttackModifierType;
import pixelmon.battles.attacks.attackModifiers.ChanceModifier;
import pixelmon.battles.attacks.attackModifiers.CriticalHitAttackModifier;
import pixelmon.battles.attacks.attackModifiers.DamageAttackModifier;
import pixelmon.battles.attacks.attackModifiers.FlinchAttackModifier;
import pixelmon.battles.attacks.attackModifiers.ModifierType;
import pixelmon.battles.attacks.attackModifiers.MultipleHitAttackModifier;
import pixelmon.battles.attacks.attackModifiers.PriorityAttackModifier;
import pixelmon.battles.attacks.attackModifiers.RecoilAttackModifier;
import pixelmon.battles.attacks.specialAttacks.Acrobatics;
import pixelmon.battles.attacks.specialAttacks.Acupressure;
import pixelmon.battles.attacks.specialAttacks.Bide;
import pixelmon.battles.attacks.specialAttacks.Curse;
import pixelmon.battles.attacks.specialAttacks.EchoedVoice;
import pixelmon.battles.attacks.specialAttacks.Eruption;
import pixelmon.battles.attacks.specialAttacks.Facade;
import pixelmon.battles.attacks.specialAttacks.Frustration;
import pixelmon.battles.attacks.specialAttacks.Guillotine;
import pixelmon.battles.attacks.specialAttacks.GyroBall;
import pixelmon.battles.attacks.specialAttacks.HeavySlam;
import pixelmon.battles.attacks.specialAttacks.HiddenPower;
import pixelmon.battles.attacks.specialAttacks.JumpKick;
import pixelmon.battles.attacks.specialAttacks.Magnitude;
import pixelmon.battles.attacks.specialAttacks.MultiTurnSpecialAttackType;
import pixelmon.battles.attacks.specialAttacks.NightShade;
import pixelmon.battles.attacks.specialAttacks.PainSplit;
import pixelmon.battles.attacks.specialAttacks.PetalDance;
import pixelmon.battles.attacks.specialAttacks.PsychUp;
import pixelmon.battles.attacks.specialAttacks.Psywave;
import pixelmon.battles.attacks.specialAttacks.Punishment;
import pixelmon.battles.attacks.specialAttacks.RaiseStats;
import pixelmon.battles.attacks.specialAttacks.RazorWind;
import pixelmon.battles.attacks.specialAttacks.Restore;
import pixelmon.battles.attacks.specialAttacks.Return;
import pixelmon.battles.attacks.specialAttacks.Reversal;
import pixelmon.battles.attacks.specialAttacks.SeismicToss;
import pixelmon.battles.attacks.specialAttacks.SolarBeam;
import pixelmon.battles.attacks.specialAttacks.SpecialAttackType;
import pixelmon.battles.attacks.specialAttacks.Venoshock;
import pixelmon.battles.attacks.statusEffects.Burn;
import pixelmon.battles.attacks.statusEffects.Confusion;
import pixelmon.battles.attacks.statusEffects.Cursed;
import pixelmon.battles.attacks.statusEffects.FireSpin;
import pixelmon.battles.attacks.statusEffects.Flee;
import pixelmon.battles.attacks.statusEffects.Freeze;
import pixelmon.battles.attacks.statusEffects.Infatuated;
import pixelmon.battles.attacks.statusEffects.Leech;
import pixelmon.battles.attacks.statusEffects.LightScreen;
import pixelmon.battles.attacks.statusEffects.LockedInBattle;
import pixelmon.battles.attacks.statusEffects.Mist;
import pixelmon.battles.attacks.statusEffects.Paralysis;
import pixelmon.battles.attacks.statusEffects.Perish;
import pixelmon.battles.attacks.statusEffects.Poison;
import pixelmon.battles.attacks.statusEffects.PoisonBadly;
import pixelmon.battles.attacks.statusEffects.Protect;
import pixelmon.battles.attacks.statusEffects.SafeGuard;
import pixelmon.battles.attacks.statusEffects.Sleep;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.battles.attacks.statusEffects.Sunny;
import pixelmon.battles.attacks.statusEffects.TrickRoom;
import pixelmon.battles.attacks.statusEffects.WaitAfter;
import pixelmon.battles.attacks.statusEffects.Yawn;


public class EffectParser {

	private String effectTypeString;
	private String effectValueString;

	private EffectType effectType;
	private ValueType valueType;

	private int Value = 0;
	private int Value2 = 0;
	private String ValueString;

	private boolean hasModifier;
	private ArrayList<ModifierStoreClass> modifierStore = new ArrayList<ModifierStoreClass>();

	public EffectBase ParseEffect(String effect) {
		if (effect.equalsIgnoreCase("None")) {
			effectType = EffectType.None;
			return null;
		}
		String[] splits = effect.split(":");
		String[] effectSplits = splits[0].split("=");
		effectTypeString = effectSplits[0];
		if (effectSplits.length > 1)
			effectValueString = effectSplits[1];
		else
			valueType = ValueType.None;

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
		if (effectType == EffectType.Status){
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Burn)
				effect = new Burn();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Confusion)
				effect = new Confusion();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Cursed)
				effect = new Cursed();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.FireSpin)
				effect = new FireSpin();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Flee)
				effect = new Flee();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Freeze)
				effect = new Freeze();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Infatuated)
				effect = new Infatuated();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Leech)
				effect = new Leech();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.LightScreen)
				effect = new LightScreen();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.LockedInBattle)
				effect = new LockedInBattle();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Mist)
				effect = new Mist();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Paralysis)
				effect = new Paralysis();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Perish)
				effect = new Perish();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Poison)
				effect = new Poison();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.PoisonBadly)
				effect = new PoisonBadly();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Protect)
				effect = new Protect();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.SafeGuard)
				effect = new SafeGuard();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Sleep)
				effect = new Sleep();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Sunny)
				effect = new Sunny();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.TrickRoom)
				effect = new TrickRoom();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.WaitAfter)
				effect = new WaitAfter(Value);
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Yawn)
				effect = new Yawn();
		}
		if (effectType == EffectType.Stats)
			effect = new StatsEffect(
					StatsEffectType.getStatsEffect(effectTypeString), Value,
					modifierStoreContains(ModifierType.User));
		if (effectType == EffectType.Remove)
			effect = new RemoveEffect(
					StatusEffectType.getStatusEffect(ValueString));
		if (effectType == EffectType.AttackModifier) {
			if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.AlwaysHit)
				effect = new AlwaysHitAttackModifier();
			if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.CriticalHit)
				effect = new CriticalHitAttackModifier(Value);
			if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Damage)
				effect = new DamageAttackModifier(Value, valueType);
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Flinch)
				effect = new FlinchAttackModifier();
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.MultipleHit)
				effect = new MultipleHitAttackModifier(Value, Value2);
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Priority)
				effect = new PriorityAttackModifier(Value);
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Recoil)
				effect = new RecoilAttackModifier(Value);
		}else if (effectType == EffectType.MultiTurnSpecialAttack){
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.PetalDance)
				effect = new PetalDance();
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.RazorWind)
				effect = new RazorWind();
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.SolarBeam)
				effect = new SolarBeam();
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.Bide)
				effect = new Bide();

			
		}else if (effectType == EffectType.SpecialAttack){
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Acrobatics)
				effect = new Acrobatics();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Acupressure)
				effect = new Acupressure();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Curse)
				effect = new Curse();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.EchoedVoice)
				effect = new EchoedVoice();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Eruption)
				effect = new Eruption();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Facade)
				effect = new Facade();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Guillotine)
				effect = new Guillotine();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.GyroBall)
				effect = new GyroBall();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.HeavySlam)
				effect = new HeavySlam();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Frustration)
				effect = new Frustration();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.HiddenPower)
				effect = new HiddenPower();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Magnitude)
				effect = new Magnitude();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.NightShade)
				effect = new NightShade();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.HiddenPower)
				effect = new HiddenPower();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.PainSplit)
				effect = new PainSplit();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Punishment)
				effect = new Punishment();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Psywave)
				effect = new Psywave();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.PsychUp)
				effect = new PsychUp();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.JumpKick)
				effect = new JumpKick();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.RaiseStats)
				effect = new RaiseStats();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Return)
				effect = new Return();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Reversal)
				effect = new Reversal();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.SeismicToss)
				effect = new SeismicToss();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Venoshock)
				effect = new Venoshock();
			
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.MilkDrink)
				effect = new Restore(1);
		}

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
		if (StatusEffectType.isStatusEffect(effectTypeString))
			effectType = EffectType.Status;
		else if (AttackModifierType.isAttackModifierType(effectTypeString))
			effectType = EffectType.AttackModifier;
		else if (StatsEffectType.isStatsEffect(effectTypeString))
			effectType = EffectType.Stats;
		else if (SpecialAttackType.isSpecialAttackType(effectTypeString))
			effectType = EffectType.SpecialAttack;
		else if (MultiTurnSpecialAttackType.isMultiTurnSpecialAttackType(effectTypeString))
			effectType = EffectType.MultiTurnSpecialAttack;

		if (hasModifier)
			for (ModifierStoreClass m : modifierStore)
				m.effectModifier = ModifierType
						.getModifierType(m.modifierTypeString);
	}

	private void calcValue() {
		if (effectValueString != null) {
			if (effectValueString.endsWith("%")) {
				valueType = ValueType.Percent;
				effectValueString = effectValueString.replace("%", "");
			} else if (effectValueString.contains("to")) {
				valueType = ValueType.Range;
				int toInd = effectValueString.indexOf("to");
				Value2 = Integer.parseInt(effectValueString
						.substring(toInd + 2));
				effectValueString = effectValueString.substring(0, toInd);
			} else
				valueType = ValueType.WholeNumber;
			try {
				Value = Integer.parseInt(effectValueString);
			} catch (Exception e) {
			} finally {
				ValueString = effectValueString;
			}
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

	public enum ValueType {
		None, WholeNumber, Percent, Range
	}
	
	public class ModifierStoreClass {

		public ModifierType effectModifier;
		public String modifierTypeString;
		public String modifierValueString;
		public ValueType modifierValueType;
		public int modifierValue;

	}
}


