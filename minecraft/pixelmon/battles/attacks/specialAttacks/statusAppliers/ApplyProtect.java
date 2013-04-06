package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Protect;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyProtect extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (user.hasStatus(StatusType.Protect))
			return;
		float chance = 1f;
		for (int i = attackList.size() - 2; i >= 0; i--) {
			if (attackList.get(i) == "Protect")
				chance *= 0.5f;
			else
				break;
		}
		if (chance < 0.125f)
			chance = 0.125f;

		if ((new Random()).nextInt(100) <= chance * 100) {
			user.status.add(new Protect());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is readying itself!");

		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " failed!");
	}
}
