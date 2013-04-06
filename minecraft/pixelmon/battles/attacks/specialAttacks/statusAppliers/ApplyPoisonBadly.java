package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.PoisonBadly;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class ApplyPoisonBadly extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (checkChance()) {
			if (target.type.contains(EnumType.Poison)) {
				return;
			}
			for (StatusBase e : target.status)
				if (e.type == StatusType.Poison || e.type == StatusType.PoisonBadly) {
					return;
				}
			target.status.add(new PoisonBadly());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has been badly poisoned!");
		}
	}
}
