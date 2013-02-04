package

pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Rest extends StatusEffectBase {
	public Rest() {
		super(StatusEffectType.Rest, true, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {

		user.status.clear();
		user.heal(user.getMaxHealth());
		user.status.add(new Sleep(3));
	}

	public boolean ApplyRepeatedEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		user.status.remove(this);
		return false;
	}
}
