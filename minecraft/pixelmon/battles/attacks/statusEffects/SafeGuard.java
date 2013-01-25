package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class SafeGuard extends StatusEffectBase {

	public SafeGuard() {
		super(StatusEffectType.SafeGuard, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : user.status)
				if (e.type == StatusEffectType.SafeGuard) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " already has a safeguard!");
					return;
				}

			user.status.add(this);
			user.battleVariables.set(type, 5);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is looking a bit guarded!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) {
		if (user.battleVariables.get(type) == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + "'s Safeguard wears off!");
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);
	}
}
