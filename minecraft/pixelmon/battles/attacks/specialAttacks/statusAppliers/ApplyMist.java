package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.status.Mist;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyMist extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : user.status)
				if (e.type == StatusType.Paralysis) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is already surrounded by mist!");
					return;
				}

			user.status.add(new Mist());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " creates a cloud of mist!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " failed!");
	}
}
