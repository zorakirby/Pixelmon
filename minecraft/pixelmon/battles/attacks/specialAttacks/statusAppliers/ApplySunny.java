package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.battles.status.Sunny;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class ApplySunny extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			if (!user.getOwner().worldObj.isDaytime()) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "There's no sun at night!");
				return;
			}
			for (StatusBase e : user.status)
				if (e.type == StatusType.Sunny) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's already sunny!");
					return;
				}

			target.status.add(new Sunny());
			user.status.add(new Sunny());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " makes the sun shine more brightly!");

		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}
}
