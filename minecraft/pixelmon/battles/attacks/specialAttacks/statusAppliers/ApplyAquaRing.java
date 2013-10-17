package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.AquaRing;
import pixelmon.battles.status.Leech;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyAquaRing extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList)
			throws Exception {
		if (user.hasStatus(StatusType.AquaRing)) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already surrounded by water!");
			return;
		}
		user.status.add(new AquaRing());
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " surrounds itself in a veil of water!");

	}

}
