package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.status.SafeGuard;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySafeGuard extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : user.status)
				if (e.type == StatusType.SafeGuard) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " already has a safeguard!");
					return;
				}

			user.status.add(new SafeGuard());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is looking a bit guarded!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}
}
