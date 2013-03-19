package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.status.StatusType;
import pixelmon.battles.status.Yawn;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyYawn extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (!target.hasStatus(StatusType.Yawn) && !target.hasStatus(StatusType.Sleep)) {
			target.status.add(new Yawn());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " became drowsy!");
		} else if (target.hasStatus(StatusType.Sleep)) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already asleep!");
		} else {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already drowsy!");
		}
	}
}
