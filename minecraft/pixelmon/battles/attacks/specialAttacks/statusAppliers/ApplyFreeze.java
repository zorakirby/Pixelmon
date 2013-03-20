package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Freeze;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class ApplyFreeze extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : target.status)
				if (e.type == StatusType.Freeze) {
					return;
				}
			if (target.type.contains(EnumType.Ice)) {
				return;
			}
			target.status.add(new Freeze());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has been frozen solid");
		}
	}
}
