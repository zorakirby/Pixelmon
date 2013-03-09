package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.status.Leech;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyLeech extends StatusApplierBase {
	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : target.status)
				if (e.type == StatusType.Leech) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already seeded!");
					return;
				}
			target.status.add(new Leech());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " has planted a seed!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}
}
