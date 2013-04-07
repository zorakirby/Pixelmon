package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Sleep;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySleep extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : target.status)
				if (e.type == StatusType.Sleep) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already asleep!");
					return;
				}

			target.status.add(new Sleep());

			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has fallen asleep!");

		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " did not fall asleep!");
	}
}
