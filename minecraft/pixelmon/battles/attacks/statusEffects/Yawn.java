package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Yawn extends StatusEffectBase {

	public Yawn() {
		super(StatusEffectType.Yawn, true, false, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (!target.hasStatus(type) && !target.hasStatus(StatusEffectType.Sleep)) {
			target.status.add(this);
			target.battleVariables.set(type, 0);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " became drowsy!");
		} else if (target.hasStatus(StatusEffectType.Sleep)) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already asleep!");
		} else {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already drowsy!");
		}
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		user.battleVariables.increment(type);

		if (user.battleVariables.get(type) == 2) {
			user.status.add(new Sleep());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " fell asleep!");
			user.status.remove(this);
		}
	}
}
