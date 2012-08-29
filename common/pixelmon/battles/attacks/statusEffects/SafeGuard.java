package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;



public class SafeGuard extends StatusEffectBase {

	private int effectTurns;
	public SafeGuard() {
		super(StatusEffectType.SafeGuard, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : user.status)
				if (e.type == StatusEffectType.SafeGuard) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " already has a safeguard!");
					return;
				}

			target.status.add(this);
			effectTurns = 5;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is looking a bit guarded!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + "'s Safeguard wears off!");
			user.status.remove(this);
		}
		effectTurns--;
	}
}
