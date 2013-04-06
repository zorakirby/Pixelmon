package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Infatuated;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyInfatuated extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : target.status)
				if (e.type == StatusType.Infatuated) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already in love!");
					return;
				}
			target.status.add(new Infatuated(user));
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has fallen in love!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " failed!");
	}
}
