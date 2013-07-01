package pixelmon.battles.attacks;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import pixelmon.battles.attacks.specialAttacks.attackModifiers.AlwaysHit;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.CriticalHit;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.Damage;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.Flinch;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.MultipleHit;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.Priority;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.Recoil;
import pixelmon.battles.attacks.specialAttacks.basic.Acrobatics;
import pixelmon.battles.attacks.specialAttacks.basic.Acupressure;
import pixelmon.battles.attacks.specialAttacks.basic.BatonPass;
import pixelmon.battles.attacks.specialAttacks.basic.BeatUp;
import pixelmon.battles.attacks.specialAttacks.basic.Curse;
import pixelmon.battles.attacks.specialAttacks.basic.DoSetDamage;
import pixelmon.battles.attacks.specialAttacks.basic.Drain;
import pixelmon.battles.attacks.specialAttacks.basic.DreamEater;
import pixelmon.battles.attacks.specialAttacks.basic.EchoedVoice;
import pixelmon.battles.attacks.specialAttacks.basic.Eruption;
import pixelmon.battles.attacks.specialAttacks.basic.FalseSwipe;
import pixelmon.battles.attacks.specialAttacks.basic.Flail;
import pixelmon.battles.attacks.specialAttacks.basic.Frustration;
import pixelmon.battles.attacks.specialAttacks.basic.GyroBall;
import pixelmon.battles.attacks.specialAttacks.basic.Heal;
import pixelmon.battles.attacks.specialAttacks.basic.HealOther;
import pixelmon.battles.attacks.specialAttacks.basic.HeavySlam;
import pixelmon.battles.attacks.specialAttacks.basic.Hex;
import pixelmon.battles.attacks.specialAttacks.basic.HiddenPower;
import pixelmon.battles.attacks.specialAttacks.basic.JumpKick;
import pixelmon.battles.attacks.specialAttacks.basic.Magnitude;
import pixelmon.battles.attacks.specialAttacks.basic.Memento;
import pixelmon.battles.attacks.specialAttacks.basic.NightShade;
import pixelmon.battles.attacks.specialAttacks.basic.OHKO;
import pixelmon.battles.attacks.specialAttacks.basic.PainSplit;
import pixelmon.battles.attacks.specialAttacks.basic.PayDay;
import pixelmon.battles.attacks.specialAttacks.basic.PsychUp;
import pixelmon.battles.attacks.specialAttacks.basic.Psywave;
import pixelmon.battles.attacks.specialAttacks.basic.Punishment;
import pixelmon.battles.attacks.specialAttacks.basic.RaiseStats;
import pixelmon.battles.attacks.specialAttacks.basic.Return;
import pixelmon.battles.attacks.specialAttacks.basic.Reversal;
import pixelmon.battles.attacks.specialAttacks.basic.SeismicToss;
import pixelmon.battles.attacks.specialAttacks.basic.SmackDown;
import pixelmon.battles.attacks.specialAttacks.basic.Struggle;
import pixelmon.battles.attacks.specialAttacks.basic.Suicide;
import pixelmon.battles.attacks.specialAttacks.basic.Teleport;
import pixelmon.battles.attacks.specialAttacks.basic.Venoshock;
import pixelmon.battles.attacks.specialAttacks.multiTurn.Bide;
import pixelmon.battles.attacks.specialAttacks.multiTurn.Dig;
import pixelmon.battles.attacks.specialAttacks.multiTurn.Fly;
import pixelmon.battles.attacks.specialAttacks.multiTurn.PetalDance;
import pixelmon.battles.attacks.specialAttacks.multiTurn.RazorWind;
import pixelmon.battles.attacks.specialAttacks.multiTurn.SolarBeam;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyAquaRing;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyBurn;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyConfusion;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyDisable;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyFireSpin;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyFlee;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyFreeze;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyImmobilize;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyInfatuated;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyLeech;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyLightScreen;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyMist;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyParalysis;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyPerish;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyPoison;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyPoisonBadly;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyProtect;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyRecharge;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyRest;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplySafeGuard;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplySleep;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplySunny;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyTrickRoom;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.ApplyYawn;

public class EffectRegistry {
	public static HashMap<String, Class> effectMap = new HashMap<String, Class>();

	static {
		// Status Appliers
		effectMap.put("aquaring", ApplyAquaRing.class);
		effectMap.put("burn", ApplyBurn.class);
		effectMap.put("confusion", ApplyConfusion.class);
		effectMap.put("disable", ApplyDisable.class);
		effectMap.put("firespin", ApplyFireSpin.class);
		effectMap.put("flee", ApplyFlee.class);
		effectMap.put("freeze", ApplyFreeze.class);
		effectMap.put("immobilize", ApplyImmobilize.class);
		effectMap.put("infatuated", ApplyInfatuated.class);
		effectMap.put("leech", ApplyLeech.class);
		effectMap.put("lightscreen", ApplyLightScreen.class);
		effectMap.put("mist", ApplyMist.class);
		effectMap.put("paralysis", ApplyParalysis.class);
		effectMap.put("perish", ApplyPerish.class);
		effectMap.put("poison", ApplyPoison.class);
		effectMap.put("poisonbadly", ApplyPoisonBadly.class);
		effectMap.put("protect", ApplyProtect.class);
		effectMap.put("rest", ApplyRest.class);
		effectMap.put("recharge", ApplyRecharge.class);
		effectMap.put("safeguard", ApplySafeGuard.class);
		effectMap.put("sleep", ApplySleep.class);
		effectMap.put("sunny", ApplySunny.class);
		effectMap.put("trickroom", ApplyTrickRoom.class);
		effectMap.put("yawn", ApplyYawn.class);

		// Attack Modifiers
		effectMap.put("alwayshit", AlwaysHit.class);
		effectMap.put("criticalhit", CriticalHit.class);
		effectMap.put("damage", Damage.class);
		effectMap.put("flinch", Flinch.class);
		effectMap.put("multiplehit", MultipleHit.class);
		effectMap.put("priority", Priority.class);
		effectMap.put("recoil", Recoil.class);

		// Basic Attacks
		effectMap.put("acrobatics", Acrobatics.class);
		effectMap.put("acupressure", Acupressure.class);
		effectMap.put("batonpass", BatonPass.class);
		effectMap.put("beatup", BeatUp.class);
		effectMap.put("curse", Curse.class);
		effectMap.put("dosetdamage", DoSetDamage.class);
		effectMap.put("drain", Drain.class);
		effectMap.put("dreameater", DreamEater.class);
		effectMap.put("echoedvoice", EchoedVoice.class);
		effectMap.put("eruption", Eruption.class);
		effectMap.put("facade", Acrobatics.class);
		effectMap.put("falseswipe", FalseSwipe.class);
		effectMap.put("flail", Flail.class);
		effectMap.put("frustration", Frustration.class);
		effectMap.put("gyroball", GyroBall.class);
		effectMap.put("heal", Heal.class);
		effectMap.put("healother", HealOther.class);
		effectMap.put("heavyslam", HeavySlam.class);
		effectMap.put("hex", Hex.class);
		effectMap.put("hiddenpower", HiddenPower.class);
		effectMap.put("jumpkick", JumpKick.class);
		effectMap.put("magnitude", Magnitude.class);
		effectMap.put("memento", Memento.class);
		effectMap.put("nightshade", NightShade.class);
		effectMap.put("ohko", OHKO.class);
		effectMap.put("painsplit", PainSplit.class);
		effectMap.put("payday", PayDay.class);
		effectMap.put("psychup", PsychUp.class);
		effectMap.put("psywave", Psywave.class);
		effectMap.put("punishment", Punishment.class);
		effectMap.put("raisestats", RaiseStats.class);
		effectMap.put("return", Return.class);
		effectMap.put("reversal", Reversal.class);
		effectMap.put("seismictoss", SeismicToss.class);
		effectMap.put("smackdown", SmackDown.class);
		effectMap.put("struggle", Struggle.class);
		effectMap.put("suicide", Suicide.class);
		effectMap.put("teleport", Teleport.class);
		effectMap.put("venoshock", Venoshock.class);

		// Multi-Turn Attacks
		effectMap.put("bide", Bide.class);
		effectMap.put("dig", Dig.class);
		effectMap.put("fly", Fly.class);
		effectMap.put("petaldance", PetalDance.class);
		effectMap.put("razorwind", RazorWind.class);
		effectMap.put("solarbeam", SolarBeam.class);
	}

	public static EffectBase getEffect(String effectTypeString, Value[] values) {

		EffectBase effect = null;

		try {
			Class effectClass = (Class) effectMap.get(effectTypeString.toLowerCase());

			if (effectClass != null) {
				Constructor c = effectClass.getConstructors()[0];
				if (c.isVarArgs())
					effect = (EffectBase) effectClass.getConstructors()[0].newInstance(new Object[] { values });
				else
					effect = (EffectBase) effectClass.getConstructors()[0].newInstance(new Object[] {});
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return effect;
	}
}
