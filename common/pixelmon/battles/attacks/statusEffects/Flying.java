package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Flying extends StatusEffectBase {

	int turnCount;
	public Flying() {
		super(StatusEffectType.Flying, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		turnCount=0;
	}

	@Override
	public boolean stopsSwitching() {
		return true;
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		if (!a.attackName.equalsIgnoreCase("Smack Down"))
			return true;
		return false;
	}
	
	@Override
	public boolean stopsStatusChange() {
		return true;
	}
}
