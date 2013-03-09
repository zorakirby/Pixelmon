package pixelmon.battles.attacks;

import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import pixelmon.battles.attacks.specialAttacks.attackModifiers.*;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.*;

public class EffectRegistry {
	public static HashMap<String, Class> effectMap = new HashMap<String, Class>();

	static {
		// Status Appliers
		effectMap.put("burn", ApplyBurn.class);
		effectMap.put("confusion", ApplyConfusion.class);
		effectMap.put("firespin", ApplyFireSpin.class);
		effectMap.put("flee", ApplyFlee.class);
		effectMap.put("freeze", ApplyFreeze.class);
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
		effectMap.put("safeguard", ApplySafeGuard.class);
		effectMap.put("sleep", ApplySleep.class);
		effectMap.put("sunny", ApplySunny.class);
		effectMap.put("trickroom", ApplyTrickRoom.class);
		effectMap.put("waitafter", ApplyWaitAfter.class);
		effectMap.put("yawn", ApplyYawn.class);

		// Attack Modifiers
		effectMap.put("alwayshit", AlwaysHit.class);
		effectMap.put("criticalhit", CriticalHit.class);
		effectMap.put("damage", Damage.class);
		effectMap.put("flinch", Flinch.class);
		effectMap.put("multiplehit", MultipleHit.class);
		effectMap.put("priority", Priority.class);
		effectMap.put("recoil", Recoil.class);
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
					effect = (EffectBase) effectClass.getConstructors()[0].newInstance(new Object[] { });
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return effect;
	}
}
